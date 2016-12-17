package com.atguigu.jf.console.item.controller;

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
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.common.utils.GlobalName;
import com.atguigu.jf.console.item.bean.bo.IcItemBean;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.item.bean.pojo.IcItemPicture;
import com.atguigu.jf.console.item.service.IcItemService;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/icItemController")
public class IcItemController {
	
	@Autowired
	private IcItemService icItemService;
	
	/**
	 * @methodName: getIcItemForList  
	 * @function: 获取集合用于分页，使用PageHelper
	 * @param page
	 * @param limit
	 * @param icItemBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/getIcItemForList")
	public String getIcItemForList(Integer page, Integer limit, IcItemBean icItemBean) throws Exception{
		// 封装查询条件
		Map<String, Object> map = new HashMap<>();
		if(icItemBean != null){
			if("".equals(icItemBean.getItemName())){
				icItemBean.setItemName(null);
			}
			if("".equals(icItemBean.getItemSaleAttr())){
				icItemBean.setItemSaleAttr(null);
			}
			
			map.put("itemType", icItemBean.getItemType());
			map.put("mallCatId", icItemBean.getMallCatId());
			map.put("itemSaleAttr", icItemBean.getItemSaleAttr());
			map.put("itemName", icItemBean.getItemName());
			map.put("mchtId", icItemBean.getMchtId());
			map.put("itemApprState", icItemBean.getItemApprState());
		}
		
		// 设置分页信息
		PageHelper.startPage(page, limit);
		
		// 获取数据集合
		List<IcItemBean> list = icItemService.getIcItemForList(map);
		
		// 包装数据集合，使用分页展示
		PageInfo<IcItemBean> pageInfo = new PageInfo<IcItemBean>(list);
		
		//设置日期显示格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * @methodName: toAdd  
	 * @function: 前往添加/修改/查看页面 
	 * @param type
	 * @param icItem
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@RequestMapping("/toAdd")
	public String toAdd(String type, IcItem icItem, Map<String,Object> map){
		
		// 修改操作，执行回显
		if("modify".equals(type) || "detail".equals(type)){
			IcItem modifyIcItem = icItemService.getIcItemById(icItem);
			
			// 若是修改操作，则将商品设置为修改完替换下架状态
			if("modify".equals(type)){
				modifyIcItem.setItemUdState(4);
			}
			
			map.put("icItem", modifyIcItem);
			
			// 格式化开始和结束时间，用于回显
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String itemValidStart = sdf.format(modifyIcItem.getItemValidStart());
			String itemValidEnd = sdf.format(modifyIcItem.getItemValidEnd());
			map.put("itemValidStart", itemValidStart);
			map.put("itemValidEnd", itemValidEnd);
			
			// 获取图片对象
			IcItemPicture icItemPicture = icItemService.getIcItemPictureByItemId(icItem.getItemId());
			map.put("icItemPicture", icItemPicture);
			
			// 若执行审批，前往审批页面
			/*if("apprPass".equals(type)){
				return "item/apprItem";
			}*/
			
			// 查看详情操作
			if("detail".equals(type)){
				map.put("detail", "detail");
			}
		}
		
		map.put("type", type);
		return "item/addItem";
	}
	
	/**
	 * @methodName: uploadFile  
	 * @function: 图片上传保存到服务器，并将路径返回到页面中 
	 * @param uploadFile
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFile(MultipartFile uploadFile, HttpSession session) throws IllegalStateException, Exception{
		JSONObject obj = new JSONObject();
		// 获取保存图片服务器的真实路径
		String virtualPath = "/pomp_console/item/20161111";
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
			obj.put("fileName", "/pomp_console/item/20161111/" + fileName);
		} catch (Exception e) {
			obj.put("message", "上传失败");
		}

		return obj;
	}
	
	/**
	 * @methodName: saveIcItem  
	 * @function: 保存商品信息 
	 * @param icItem 商品信息保存
	 * @param icItemPicture 商品图片路径保存
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/add")
	public ResultBean saveIcItem(IcItem icItem, IcItemPicture icItemPicture, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 设置商品状态
		if(icItem != null){
			// 待审批
			icItem.setItemApprState(2);
			icItem.setItemUdState(2);
			
			// creates
			icItem.setCreateTime(new Date());
			SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
			if(sysOp != null){
				icItem.setCreator(sysOp.getOpId());
			}
		}
		
		// 保存商品
		int i = icItemService.saveIcItem(icItem);
		
		// 获取生成的itemId，设置图片对应的itemId
		icItemPicture.setItemId(icItem.getItemId());
		
		// 保存图片
		int j = icItemService.saveIcItemPicture(icItemPicture);
		
		if(i == 1 && j == 1){
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("保存成功");
		}else{
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("操作失败，请联系管理员");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: updateIcItem  
	 * @function: 修改商品信息 
	 * @param icItem
	 * @param icItemPicture
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/update")
	public ResultBean updateIcItem(IcItem icItem, IcItemPicture icItemPicture, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 更改状态为待审批
		icItem.setItemApprState(2);
		
		// 修改商品信息
		int i = icItemService.updateIcItem(icItem);
		
		// 修改图片路径
		icItemService.updateIcItemPicture(icItemPicture);
		
		if(i == 1){
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("修改成功");
		}else{
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("操作失败，请联系管理员");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: updateIcItem  
	 * @function: 修改上下架和审批 
	 * @param icItem
	 * @param icItemPicture
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/updateState")
	public ResultBean updateState(IcItem icItem, IcItemPicture icItemPicture, HttpSession session){
		ResultBean resultBean = new ResultBean();
		
		// 修改商品信息
		int i = icItemService.updateIcItem(icItem);
		
		if(i == 1){
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("修改成功");
		}else{
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("操作失败，请联系管理员");
		}
		
		return resultBean;
	}
	
}
