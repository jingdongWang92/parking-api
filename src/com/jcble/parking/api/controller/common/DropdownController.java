package com.jcble.parking.api.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.DropDownDto;
import com.jcble.parking.common.service.common.CommonService;

@Controller
public class DropdownController {
	
	@Autowired
	private CommonService commonService;

	
	/**
	 * 获取单个停车场信息
	 * @return
	 */
	@RequestMapping(value = "/dropdown/{dropdownName}",method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ResponseDto getParkinglot(@PathVariable String dropdownName) {
		ResponseDto resp = new ResponseDto();
		try {
			List<DropDownDto> dropdownList = commonService.getDropdownByName(dropdownName);
			resp.setPayload(dropdownList);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

}
