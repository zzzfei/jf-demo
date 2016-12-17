package com.atguigu.jf.console.merchant.controller;

import java.io.File;
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
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.common.utils.GlobalName;
import com.atguigu.jf.console.merchant.bean.pojo.PcProvider;
import com.atguigu.jf.console.merchant.service.PcProviderService;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/pcProvider")
public class PcProviderController {
	
	@Autowired
	private PcProviderService pcProviderService;
	
	/**
	 * @methodName: getPcProvider  
	 * @function: 查询积分供应商信息，利用PageHelper进行分页
	 * @param page
	 * @param limit
	 * @param pcProvider
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/getPcProvider")
	public String getPcProvider(Integer page, Integer limit, PcProvider pcProvider){
		Map<String, Object> map = new HashMap<>();
		
		// 设置查询条件
		if(pcProvider != null){
			if(pcProvider.getProviderName() == ""){
				pcProvider.setProviderName(null);
			}
			if(pcProvider.getProviderShortName() == ""){
				pcProvider.setProviderShortName(null);
			}
			
			map.put("providerShortName", pcProvider.getProviderShortName());
			map.put("providerName", pcProvider.getProviderName());
		}
		
		// 设置分页信息
		PageHelper.startPage(page, limit);
		
		// 查询
		List<PcProvider> list = pcProviderService.getPcProviderList(map);
		
		// 用PageInfo 包装分页集合
		PageInfo<PcProvider> pageInfo = new PageInfo<>(list);
		
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * @methodName: getPcProviderForCombo  
	 * @function: 获取供应商类型（别名）
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping("/getPcProviderForCombo")
	public List<PcProvider> getPcProviderForCombo(){
		// 查询
		List<PcProvider> list = pcProviderService.getPcProviderList(new HashMap());
		
		return list;
	}
	
	/**
	 * @methodName: uploadFile  
	 * @function: 头像上传 
	 * @param uploadFile
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFile(MultipartFile uploadFile, HttpSession session) throws IllegalStateException, Exception{
		JSONObject obj = new JSONObject();
		
		if(uploadFile.getSize() > 1024000){
			obj.put("message", "文件太大，请重新选择");
			return obj;
		}
		
		// 获取保存图片服务器的真实路径
		String virtualPath = "/pomp_app/jpg/content";
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
			obj.put("fileName", "/pomp_app/jpg/content/" + fileName);
		} catch (Exception e) {
			obj.put("message", "上传失败");
		}
		
		return obj;
	}
	
	/**
	 * @methodName: toAdd  
	 * @function: 前往保存/修改/查看详情页面
	 * @param type
	 * @param pcProvider
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@RequestMapping("/toAdd")
	public String toAdd(String type, PcProvider pcProvider, Map<String,Object> map){
		// 执行修改或查看，则处理回显
		if("modify".equals(type) || "detail".equals(type)){
			PcProvider modifyPcProvider = pcProviderService.selectByPrimaryKey(pcProvider.getProviderId());
			map.put("pcProvider", modifyPcProvider);
			
			if("detail".equals(type)){
				map.put("detail", "detail");
			}
		}
		
		map.put("type", type);
		return "merchant/addMerchant";
	}
	
	/**
	 * @methodName: savePcProvider  
	 * @function: 保存积分供应商到数据库 
	 * @param pcProvider
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/savePcProvider")
	public ResultBean savePcProvider(PcProvider pcProvider, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 获取登录信息
		SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
		if(sysOp != null){
			pcProvider.setCreator(sysOp.getOpId());
		}
		pcProvider.setCreateTime(new Date());
		pcProvider.setProviderStatus(new Short("1"));
		
		// 保存数据到数据库
		int i = pcProviderService.savePcProvider(pcProvider);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("保存失败");
		}else{
			resultBean.setMsg("保存成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: updatePcProvider  
	 * @function: 更新对象
	 * @param pcProvider
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/updatePcProvider")
	public ResultBean updatePcProvider(PcProvider pcProvider, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 获取登录信息
		SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
		if(sysOp != null){
			pcProvider.setModifyer(sysOp.getOpId());
		}
		pcProvider.setModifyTime(new Date());
		
		int i = pcProviderService.updatePcProvider(pcProvider);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("修改失败");
		}else{
			resultBean.setMsg("修改成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: updatePcProvider  
	 * @function: 逻辑删除对象
	 * @param pcProvider
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/deletePcProvider")
	public ResultBean deletePcProvider(PcProvider pcProvider, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 获取登录信息
		SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
		if(sysOp != null){
			pcProvider.setModifyer(sysOp.getOpId());
		}
		pcProvider.setModifyTime(new Date());
		
		// 修改数据状态
		pcProvider.setDataState(new Short("2"));
		
		int i = pcProviderService.updatePcProvider(pcProvider);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("删除失败");
		}else{
			resultBean.setMsg("删除成功");
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
		}
		
		return resultBean;
	}
}
