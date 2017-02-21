package com.jcble.parking.api.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.common.Exception.ParkingServiceException;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.admin.ParkingAreaDto;
import com.jcble.parking.common.service.admin.ParkingAreaService;

/**
 * 停车场区域相关控制器
 * @author Jingdong Wang
 *
 */
@Controller
public class ParkingAreaController {
	
	@Autowired
	private ParkingAreaService parkingAreaService;
	
	/**
	 * 获取停车场区域
	 * @return
	 */
	@RequestMapping(value = "/parkinglot/{parkinglotId}/area",method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public ResponseDto getParkings(HttpServletRequest request,@PathVariable Integer parkinglotId) {
		ResponseDto resp = new ResponseDto();
		try {
			if(parkinglotId == null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			ParkingAreaDto dto = new ParkingAreaDto();
			dto.setParkingLotId(parkinglotId);
			List<ParkingAreaDto> parkingLots = parkingAreaService.queryParkingAreas(dto);
			resp.setPayload(parkingLots);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
	
}
