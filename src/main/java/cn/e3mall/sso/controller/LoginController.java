package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.LoginService;
import cn.e3mall.sso.service.RegisterService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/page/login")
	public String showLogin(){
		return "login";
	}
	
	/*@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkData(@PathVariable String param,@PathVariable Integer type){
		return registerService.checkData(param, type);
	}*/
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		E3Result result = loginService.login(username,password);
		if(result.getStatus() == 200){
			//往cookie里写入token
			CookieUtils.setCookie(request, response, "token", (String)result.getData());
		}
		return result;
	}
}
