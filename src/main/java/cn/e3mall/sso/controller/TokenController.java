package cn.e3mall.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.TokenService;

@Controller
public class TokenController {
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 根据token查询用户信息
	 */
	@RequestMapping(value="/user/token/{token}",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback){
		E3Result result = tokenService.getUserByToken(token);
		//响应结果之前判断是否为jsonp请求 如果为jsonp请求则应响应js文本
		if(StringUtils.isNotBlank(callback)){
			String newResult = callback+"("+JsonUtils.objectToJson(result)+");";
			return newResult;		
		}
		return JsonUtils.objectToJson(result);
	}
}
