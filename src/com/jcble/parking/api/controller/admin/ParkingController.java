package com.jcble.parking.api.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.api.controller.BaseController;
import com.jcble.parking.common.CommonConstants;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.admin.ParkingDto;
import com.jcble.parking.common.service.admin.ParkingService;

import baseproj.common.mybatis.page.PageParameter;

/**
 * 车位相关控制器
 * 
 * @author Jingdong Wang
 *
 */
@Controller
public class ParkingController extends BaseController {

	@Autowired
	private ParkingService parkingService;

	
	/**
	 * 获取车位信息 
	 * @param pageIndex 
	 * @param pageSize 
	 * @param parkinglotId
	 * @param lockStatus
	 * @param operatorId
	 * @param devBindStatus
	 * @return
	 */
	@RequestMapping(value = "/parkings", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseDto getParkings(@RequestParam(value = "page", required = false) Integer pageIndex,@RequestParam(value = "page_size", required = false) Integer pageSize,
			@RequestParam(value = "parkinglotId", required = false) Integer parkinglotId,@RequestParam(value = "lockStatus", required = false) String lockStatus,
			@RequestParam(value = "operatorId") Integer operatorId,@RequestParam(value = "devBindStatus", required = false) String devBindStatus) {
		ResponseDto resp = new ResponseDto();
		try {
			ParkingDto dto = new ParkingDto();
			if(pageIndex == null) {
				pageIndex = CommonConstants.PAGE_INDEX;
			}
			if(pageSize == null) {
				pageSize = CommonConstants.PAGE_SIZE;
			}
			dto.setPage(new PageParameter(pageIndex, pageSize));
			dto.setLockStatus(lockStatus);
			dto.setDevBindStatus(devBindStatus);
			dto.setOperatorId(operatorId);
			List<ParkingDto> parkings = parkingService.queryParkings(dto);
			resp.setPayload(parkings);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
}
