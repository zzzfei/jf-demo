package com.atguigu.jf.console.system.controller;

import java.io.File;
import java.util.Date;
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
import com.atguigu.jf.console.system.bean.pojo.IcMallCat;
import com.atguigu.jf.console.system.service.IcMallCatService;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system")
public class IcMallCatController {
	
	@Autowired
	private IcMallCatService icMallCatService;
	
	/**
	 * @methodName: getIcMallCat  
	 * @function: 获取类目集合，使用PageHelper进行分页 
	 * @param page
	 * @param limit
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月9日
	 */
	@ResponseBody
	@RequestMapping("/getMallCat")
	public String getIcMallCat(Integer page, Integer limit) throws Exception{
		// 设置分页
		PageHelper.startPage(page, limit);
		
		// 获取集合
		List<IcMallCat> list = icMallCatService.getIcMallCatList();
		
		// 用分页工具包装集合
		PageInfo<IcMallCat> pageInfo = new PageInfo<>(list);
		
		// 返回JSON数据
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * @methodName: toAdd  
	 * @function: 前往修改 / 添加页面 
	 * @param type add:添加页面  modify:修改页面
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月9日
	 */
	@RequestMapping("/toAdd")
	public String toAdd(String type, Long mallCatId, Map<String,Object> map) throws Exception{
		// 若是修改操作，则执行回显
		if("modify".equals(type)){
			// 根据id获取对象
			IcMallCat icMallCat = icMallCatService.getIcMallCatById(mallCatId);
			// 放到域中用于回显
			map.put("icMallCat", icMallCat);
		}
		
		map.put("type", type);
		return "system/addMallCat";
	}
	
	/**
	 * @methodName: uploadFile  
	 * @function: 图片上传保存 
	 * @param uploadFile
	 * @return
	 * @author 徐志超 
	 * @throws Exception 
	 * @throws IllegalStateException 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFile(MultipartFile uploadFile, HttpSession session) throws IllegalStateException, Exception{
		JSONObject obj = new JSONObject();
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
	 * @methodName: add  
	 * @function: 新增类目保存至数据库 
	 * @param icMallCat
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/add")
	public ResultBean add(IcMallCat icMallCat, HttpSession session) throws Exception{
		ResultBean resultBean = new ResultBean();
		
		// 添加操作人及数据状态
		SysOp sysOp = (SysOp) session.getAttribute("loginSysOp");
		if(sysOp != null){
			icMallCat.setCreator(sysOp.getOpId());
		}
		icMallCat.setCreateTime(new Date());
		icMallCat.setDataState(new Short("1"));
		icMallCat.setMallCatState(new Short("1"));
		
		if(icMallCat.getMallCatPicUrl()=="" || icMallCat.getMallCatPicUrl()==null){
			icMallCat.setMallCatPicUrl("/image/other.jpg");
		}
		
		// 将对象保存到数据库
		int i = icMallCatService.saveIcMallCat(icMallCat);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("保存失败");
		}else{
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("保存成功");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: modify  
	 * @function: 类目修改
	 * @param icMallCat
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/modify")
	public ResultBean modify(IcMallCat icMallCat, HttpSession session) throws Exception{
		ResultBean resultBean = new ResultBean();
		// 添加操作人及数据状态
		SysOp sysOp = (SysOp) session.getAttribute("loginSysOp");
		if(sysOp != null){
			icMallCat.setModifyer(sysOp.getOpId());
		}
		icMallCat.setModifyTime(new Date());
		
		if(icMallCat.getMallCatPicUrl()=="" || icMallCat.getMallCatPicUrl()==null){
			icMallCat.setMallCatPicUrl("/image/other.jpg");
		}
		
		// 执行修改操作
		int i = icMallCatService.updateeIcMallCat(icMallCat);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("保存失败");
		}else{
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("保存成功");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: delete  
	 * @function: 逻辑删除  
	 * @param icMallCat
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultBean delete(IcMallCat icMallCat, HttpSession session) throws Exception{
		ResultBean resultBean = new ResultBean();
		// 添加操作人及数据状态
		SysOp sysOp = (SysOp) session.getAttribute("loginSysOp");
		if(sysOp != null){
			icMallCat.setModifyer(sysOp.getOpId());
		}
		icMallCat.setModifyTime(new Date());
		
		if(icMallCat.getMallCatPicUrl()=="" || icMallCat.getMallCatPicUrl()==null){
			icMallCat.setMallCatPicUrl("/image/other.jpg");
		}
		
		// 物理删除
		icMallCat.setDataState(new Short("2"));
		
		// 执行删除操作（物理删除）
		int i = icMallCatService.updateeIcMallCat(icMallCat);
		
		if(i == 0){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("删除失败");
		}else{
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("删除成功");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: getIcMallCatForCombo  
	 * @function: 获取用于下拉框展示的商品类别集合  
	 * @return
	 * @author 徐志超 
	 * @throws Exception 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/getIcMallCatForCombo")
	public List<IcMallCat> getIcMallCatForCombo() throws Exception{
		
		List<IcMallCat> list = icMallCatService.getIcMallCatList();
		
		return list;
	}
}
