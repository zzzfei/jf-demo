package com.atguigu.jf.console.login.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.jf.console.common.utils.VerifyCodeUtil;
import com.atguigu.jf.console.login.service.LoginService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

@Controller
@RequestMapping("logincontroller")
public class LoginController {
	// 使用log4j
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginServie;
	
	/**
	 * @methodName: getVerifyCode  
	 * @function: 动态生成验证码
	 * @param response
	 * @param session
	 * @throws IOException
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	@RequestMapping("getVerifyCode")
	public void getVerifyCode(HttpServletResponse response,HttpSession session) throws IOException{
		/**
		 *   0）想办法禁用缓存
			  1）先使用VerifyCodeUtil工具类生成随机的4位数字
			  2）将生成的验证码放入session中
			  3）根据随机4位数字生成图片流
			  4）将图片流写入到输出流response.getOutputStream()
		 */
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		/*
		 * Parameters:
			type 验证码类型,参见本类的静态属性
			length 验证码长度,要求大于0的整数
			excludeString 需排除的特殊字符（无需排除则为null）
		 */
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		
		session.setAttribute("verifyCode", verifyCode);
		logger.debug("已将验证码放入到session中：【"+ verifyCode +"】");
		/*
		 * textCode 文本验证码
			width 图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
			height 图片高度
			interLine 图片中干扰线的条数
			randomLocation 每个字符的高低位置是否随机
			backColor 图片颜色,若为null则表示采用随机颜色
			foreColor 字体颜色,若为null则表示采用随机颜色
			lineColor 干扰线颜色,若为null则表示采用随机颜色

		 */
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLACK, null);
		
		// 1、生成的图片流
		// 2、图片格式的定义
		// 3、输出流
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		
	}
	
	/**
	 * 
	 * @methodName: login  
	 * @function: 登录
	 * @param username
	 * @param password
	 * @param verifyCode
	 * @param session
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam("username")String username,String password,String verifyCode,HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception{
		logger.debug("登录的参数为：" + username + "---" + password + "---" + verifyCode);
		// 校验验证码
		// 从session中获取当前的验证码
		String session_verifyCode = (String) session.getAttribute("verifyCode");
		// 如果验证码校验不通过
		if(!verifyCode.equals(session_verifyCode)){
			redirectAttributes.addFlashAttribute("errMsg", "验证码不正确！");
			return "redirect:/toLogin";
		}
		// 用户名和登录密码
		SysOp param = new SysOp();
		param.setLoginCode(username);
		param.setLoginPasswd(password);
		// 通过后台查询
		SysOp sysOp = loginServie.selectSysOpByUnameAndPwd(param);
		
		if(sysOp == null){
			redirectAttributes.addFlashAttribute("errMsg", "用户名或密码错误！");
			return "redirect:/toLogin";
		}
		
		//将登录的用户放入域中
		session.setAttribute("loginSysOp", sysOp);
		
		return "redirect:/index";
	}
	
	@ResponseBody
	@RequestMapping("/getMenu")
	public List<SysFuncBean> getMenu(HttpSession session) throws Exception{
		
		SysOp sysOp = (SysOp) session.getAttribute("loginSysOp");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opId", sysOp.getOpId());
		
		List<SysFuncBean> sysFuncList = loginServie.getMenuByOpId(map);
		
		List<SysFuncBean> list = new ArrayList<>();
		
		//转换为树
		// 先获取一级菜单
		for (SysFuncBean sysFuncBean : sysFuncList) {
			Short funcLevel = sysFuncBean.getFuncLevel();
			if(funcLevel.equals(new Short("1"))){
				list.add(sysFuncBean);
			}
		}
		
		//往往一级菜单中填充二级菜单
		for (SysFuncBean sysFuncBean1 : list) {
			Long funcId = sysFuncBean1.getFuncId();
			List<SysFuncBean> children = new ArrayList<>();
			
			for (SysFuncBean sysFuncBean2 : sysFuncList) {
				Long supFuncId = sysFuncBean2.getSupFuncId();
				if(funcId.equals(supFuncId)){
					children.add(sysFuncBean2);
				}
			}
			sysFuncBean1.setChildren(children);
		}
		
		return list;
	}
	
	/**
	 * @methodName: logout  
	 * @function: 退出功能 
	 * @param session
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login.jsp";
	}
}
