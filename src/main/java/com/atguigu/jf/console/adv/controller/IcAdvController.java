package com.atguigu.jf.console.adv.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.adv.bean.bo.IcAdvBean;
import com.atguigu.jf.console.adv.bean.pojo.IcAdv;
import com.atguigu.jf.console.adv.service.IcAdvService;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.common.utils.GlobalName;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/icAdv")
public class IcAdvController {
	
	@Autowired
	private IcAdvService icAdvService;
	
	/**
	 * @methodName: getIcAdv  
	 * @function: 获取广告列表
	 * @param icAdvBean
	 * @param map
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/getIcAdv")
	public String getIcAdv(Integer page, Integer limit, IcAdvBean icAdvBean) throws Exception{
		Map<String,Object> map = new HashMap<>();
		if(icAdvBean != null){
			map.put("advState", icAdvBean.getAdvState());
			map.put("advPos", icAdvBean.getAdvPos());
			map.put("advAreaId", icAdvBean.getAdvAreaId());
			if(!"".equals(icAdvBean.getAdvName())){
				map.put("advName", icAdvBean.getAdvName());
			}
		}
		
		// 设置分页信息
		PageHelper.startPage(page, limit);
		
		List<IcAdvBean> list = icAdvService.getIcAdvList(map);
		
		// 用分页类包装集合
		PageInfo<IcAdvBean> pageInfo = new PageInfo<>(list);
		
		// 设置返回的JSON数据的日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy:MM:dd";
		
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * @methodName: upReOrDownRe  
	 * @function: 更新操作 
	 * @param icAdvBean
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/update")
	public ResultBean upReOrDownRe(IcAdv icAdv) throws Exception{
		ResultBean resultBean = new ResultBean();
		
		int i = icAdvService.updateIcAdv(icAdv);
		
		if(i == 0){
			resultBean.setMsg("操作失败，请联系管理员");
			resultBean.setResult(ResultBean.ERROR_RESULT);
		}else{
			resultBean.setMsg("操作成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: delete  
	 * @function: 逻辑删除
	 * @param icAdvBean
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultBean delete(IcAdv icAdv) throws Exception{
		ResultBean resultBean = new ResultBean();
		// 设置数据状态为删除状态
		icAdv.setDataState(new Short("2"));
		
		int i = icAdvService.updateIcAdv(icAdv);
		
		if(i == 0){
			resultBean.setMsg("操作失败，请联系管理员");
			resultBean.setResult(ResultBean.ERROR_RESULT);
		}else{
			resultBean.setMsg("操作成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: toAdd  
	 * @function: 前往添加/修改页面
	 * @param icAdvBean
	 * @param map
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@RequestMapping("/toAdd")
	public String toAdd(String type, IcAdv icAdv, Map<String,Object> map) throws Exception{
		// 若执行的是修改，则需要查询到修改对象，并执行回显
		if("modify".equals(type)){
			if(icAdv != null){
				IcAdv modifyIcAdv = icAdvService.getIcAdvById(icAdv.getAdvId());
				
				map.put(GlobalName.IC_ADV, modifyIcAdv);

				// 格式化时间并放入域中
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String advStartTime = sdf.format(modifyIcAdv.getAdvStartTime());
				String advEndTime = sdf.format(modifyIcAdv.getAdvEndTime());
				map.put("advStartTime", advStartTime);
				map.put("advEndTime", advEndTime);
			}
		}else if("detail".equals(type)){
			if(icAdv != null){
				IcAdv modifyIcAdv = icAdvService.getIcAdvById(icAdv.getAdvId());
				
				map.put(GlobalName.IC_ADV, modifyIcAdv);

				// 格式化时间并放入域中
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String advStartTime = sdf.format(modifyIcAdv.getAdvStartTime());
				String advEndTime = sdf.format(modifyIcAdv.getAdvEndTime());
				map.put("advStartTime", advStartTime);
				map.put("advEndTime", advEndTime);
				
				// 放入查看标识
				map.put("detail", type);
			}
		}
		
		map.put("type", type);
		return "pageManagement/addAdv";
	}
	
	/**
	 * @methodName: uploadFile  
	 * @function: 广告图片上传 
	 * @param uploadFile
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws Exception
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFile(MultipartFile uploadFile, HttpSession session) throws IllegalStateException, Exception{
		JSONObject obj = new JSONObject();
		// 获取保存图片服务器的真实路径
		String virtualPath = "/pomp_console/jpg/20161111";
		ServletContext servletContext = session.getServletContext();
		String realPath = servletContext.getRealPath(virtualPath);
		
		// 获取图片原始名称并做处理
		String fileName = System.nanoTime() + uploadFile.getOriginalFilename();
		
		// 创建文件路径
		File file = new File(realPath + "/" + fileName);
		
		// 保存图片到服务器
		try {
			uploadFile.transferTo(file);
			obj.put("message", "上传成功");
			obj.put("fileName", "/pomp_console/jpg/20161111/" + fileName);
		} catch (Exception e) {
			obj.put("message", "上传失败");
		}
		
		return obj;
	}
	
	/**
	 * @methodName: save  
	 * @function: 保存操作  
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/add")
	public ResultBean save(IcAdv icAdv, HttpSession session) throws Exception{
		ResultBean resultBean = new ResultBean();
		
		// 获取操作人信息
		SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
		if(sysOp != null){
			icAdv.setCreator(sysOp.getOpId());
		}
		icAdv.setCreateTime(new Date());
		
		// 获取当前最大的advOrder
		Long maxOrder = icAdvService.getMaxOrder();
		
		icAdv.setAdvOrder(maxOrder + new Long("1"));
		
		// 设置广告位默认状态
		icAdv.setAdvState(new Short("3"));
		
		int i = icAdvService.save(icAdv);
		
		if(i == 0){
			resultBean.setMsg("操作失败，请练习管理员！");
			resultBean.setResult(ResultBean.ERROR_RESULT);
		}else{
			resultBean.setMsg("保存成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: orderUp  
	 * @function: 调换上架广告顺序
	 * @param advId
	 * @return
	 * @author shyboy@fei 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/orderUp")
	public ResultBean orderUp(Long advId, String type) throws Exception{
		ResultBean resultBean = new ResultBean();
		
		// 获取所有上架广告集合
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("advState", 1);
		
		// 获取当前对象和上一位的上架对象
		IcAdv currentIcAdv = null;
		IcAdv pIcAdv = null;
		int currentIndex = 0;
		List<IcAdv> list = icAdvService.getIcAdvListForOrder(map);
		for (int i = 0, size = list.size(); i < size ; i++){
			IcAdv ia = list.get(i);
			if(ia.getAdvId().equals(advId)){
				currentIcAdv = list.get(i);
				currentIndex = i;
			}
		}
		
		if("up".equals(type)){
			pIcAdv = list.get(currentIndex - 1);
		}else{
			// 最后一个无法下移
			if(currentIndex == (list.size()-1)){
				resultBean.setMsg("已是最后一个，无法下移!");
				return resultBean;
			}
			
			pIcAdv = list.get(currentIndex + 1);
		}
		
		// 进行顺序调换操作
		Long temp = currentIcAdv.getAdvOrder();
		currentIcAdv.setAdvOrder(pIcAdv.getAdvOrder());
		pIcAdv.setAdvOrder(temp);
		
		// 更新顺序
		icAdvService.updateOrder(currentIcAdv, pIcAdv);
		
		resultBean.setMsg("调整成功!");
		resultBean.setResult(ResultBean.SUCCESS_RESULT);
		return resultBean;
	}
	
}
