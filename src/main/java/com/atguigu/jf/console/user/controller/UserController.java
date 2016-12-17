package com.atguigu.jf.console.user.controller;

import java.io.File;
import java.io.IOException;
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
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * @methodName: getSysOp  
	 * @function: 获取分页信息 
	 * @param start
	 * @param limit
	 * @param sysOp
	 * @return
	 * @author 徐志超 
	 * @throws Exception 
	 * @date 2016年11月7日
	 */
	/*@ResponseBody
	@RequestMapping("/getSysOp")
	public PageModel<SysOp> getSysOp(Integer start, Integer limit, SysOp sysOp){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("limit", limit);
		
		if(sysOp != null){
			if("".equals(sysOp.getOpName())){
				sysOp.setOpName(null);
			}

			map.put("opKind", sysOp.getOpKind());
			map.put("opName", sysOp.getOpName());
		}
		
		//获取总记录数
		Integer total = userService.getTotal(map);
		
		//获取分页集合
		List<SysOp> rows = userService.getSysOpList(map);
		
		PageModel<SysOp> page = new PageModel<SysOp>();
		page.setPageNo(start);
		page.setPageSize(limit);
		page.setRows(rows);
		page.setTotal(total);
		
		return page;
	}*/
	
	@ResponseBody
	@RequestMapping("/getSysOp")
	public String getSysOp(Integer page, Integer limit, SysOp sysOp) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(sysOp != null){
			if("".equals(sysOp.getOpName())){
				sysOp.setOpName(null);
			}

			map.put("opKind", sysOp.getOpKind());
			map.put("opName", sysOp.getOpName());
		}
		
		// 分页查询设置
		PageHelper.startPage(page, limit);
		
		//获取分页集合
		List<SysOp> list = userService.getSysOpListForPageInfo(map);
		
		// 对查询结果进行包装, 前台数据节点名称需要更改为list
		PageInfo<SysOp> pageInfo = new PageInfo<>(list);
		
		// 设定返回对象中的日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy:MM:dd hh:mm:ss";
		
		// 可以直接返回pageInfo对象或者使用FastJSON将对象转换为json字符串
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * @methodName: toAdd  
	 * @function: 前往添加/修改页面
	 * @param type
	 * @param opId
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	@RequestMapping("/toAdd")
	public String toAdd(String type, Integer opId, Map<String, Object> map) throws Exception{
		
		if("modify".equals(type) && opId != null){
			SysOp sysOp = userService.getSysOpById(opId);
			map.put("sysOp", sysOp);
		}
		
		map.put("type", type);
		
		return "user/userAdd";
	}
	
	/**
	 * @methodName: saveSysOp  
	 * @function: 保存用户
	 * @param sysOp
	 * @param session
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	@RequestMapping("/add")
	public void saveSysOp(SysOp sysOp, HttpSession session)throws Exception{
		// 获取当前登录用户,设置创建人字段
		SysOp loginSysOp = (SysOp) session.getAttribute("loginSysOp");
		if(loginSysOp != null){
			sysOp.setCreator(loginSysOp.getOpId());
		}
		
		// 设置创建时间字段
		sysOp.setCreateDate(new Date());
		
		userService.saveSysOp(sysOp);
	}
	
	@RequestMapping("/modify")
	public void updateSysOp(SysOp sysOp, HttpSession session) throws Exception{
		// 获取当前登录用户,设置修改人字段
		SysOp loginSysOp = (SysOp) session.getAttribute("loginSysOp");
		if(loginSysOp != null){
			sysOp.setCreator(loginSysOp.getOpId());
		}
		
		// 设置修改时间字段
		sysOp.setCreateDate(new Date());
		
		userService.updateSysOp(sysOp);
	}
	
	/**
	 * @methodName: deleteSysOp  
	 * @function: 执行用户的逻辑删除
	 * @param sysOp
	 * @param session
	 * @return
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultBean deleteSysOp(SysOp sysOp, HttpSession session) throws Exception{
		// 获取当前登录用户,设置修改人字段
		SysOp loginSysOp = (SysOp) session.getAttribute("loginSysOp");
		if(loginSysOp != null){
			sysOp.setCreator(loginSysOp.getOpId());
		}
		
		// 设置修改时间字段
		sysOp.setCreateDate(new Date());
		
		// 进行逻辑删除
		sysOp.setDataState(new Short("2"));
		
		userService.updateSysOp(sysOp);
		
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(ResultBean.SUCCESS_RESULT);
		resultBean.setMsg("删除成功");
		
		return resultBean;
	}
	
	/**
	 * @methodName: uploadFile  
	 * @function: 图片上传保存 
	 * @param uploadFile
	 * @return
	 * @author 徐志超 
	 * @throws Exception 
	 * @throws IllegalStateException 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFile(MultipartFile uploadFile, HttpSession session) throws IllegalStateException, Exception{
		JSONObject obj = new JSONObject();
		// 获取保存图片服务器的真实路径
		String virtualPath = "/image";
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
			obj.put("fileName", "/image/" + fileName);
		} catch (Exception e) {
			obj.put("message", "上传失败");
		}

		return obj;
	}
	
}
