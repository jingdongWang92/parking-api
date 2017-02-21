package com.jcble.parking.api.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.api.controller.BaseController;
import com.jcble.parking.common.Exception.ParkingServiceException;
import com.jcble.parking.common.dto.BindDevicesDto;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.service.admin.DevicesService;

/**
 * 设备相关控制器
 * 
 * @author Jingdong Wang
 *
 */
@Controller
public class DevicesController extends BaseController {

	@Autowired
	private DevicesService devicesService;

	/**
	 * 绑定车位锁/地磁设备到车位
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/devices", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseDto bindDeviceToParking(HttpServletRequest request,@RequestBody BindDevicesDto devicesDto) throws Exception {
		ResponseDto resp = new ResponseDto();
		try {
			if(devicesDto == null || devicesDto.getOperatorId() == null || devicesDto.getDevices() == null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			devicesService.bindDeviceToParking(devicesDto);
			
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

}
















