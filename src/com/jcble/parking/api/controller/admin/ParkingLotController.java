package com.jcble.parking.api.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.common.CommonConstants;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.admin.ParkingLotDto;
import com.jcble.parking.common.service.admin.ParkingLotService;

import baseproj.common.mybatis.page.PageParameter;

/**
 * 停车场相关控制器
 * @author Jingdong Wang
 *
 */
@Controller
public class ParkingLotController {
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	/**
	 * 获取停车场信息
	 * @return
	 */
	@RequestMapping(value = "/parkinglots",method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ResponseDto getParkings(@RequestParam(value = "page", required = false) Integer pageIndex,
			@RequestParam(value = "page_size", required = false) Integer pageSize) {
		ResponseDto resp = new ResponseDto();
		try {
			if(pageIndex == null) {
				pageIndex = CommonConstants.PAGE_INDEX;
			}
			if(pageSize == null) {
				pageSize = CommonConstants.PAGE_SIZE;
			}
			ParkingLotDto dto = new ParkingLotDto();
			dto.setPage(new PageParameter(pageIndex, pageSize));
			List<ParkingLotDto> parkingLots = parkingLotService.queryParkingLots(dto);
			resp.setPayload(parkingLots);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
	
	/**
	 * 获取单个停车场信息
	 * @return
	 */
	@RequestMapping(value = "/parkinglots/{id}",method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ResponseDto getParkinglot(@PathVariable String id) {
		ResponseDto resp = new ResponseDto();
		try {
			
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
	
}
