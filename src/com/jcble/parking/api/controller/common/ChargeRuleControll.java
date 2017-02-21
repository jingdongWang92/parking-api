package com.jcble.parking.api.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.api.controller.BaseController;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.service.common.CommonService;

@Controller
@RequestMapping("/common")
public class ChargeRuleControll extends BaseController {
	
	@Autowired
	private CommonService commonService;

	/**
	 * 获取验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/charge_rule", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseDto getVerifyCode(@RequestParam(value = "parkinglotId", required = true) Integer parkinglotId) throws Exception {
		ResponseDto resp = new ResponseDto();
		try {
			
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

}
