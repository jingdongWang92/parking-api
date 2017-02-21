package com.jcble.parking.api.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.api.controller.BaseController;
import com.jcble.parking.common.Exception.ParkingServiceException;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.admin.AdminDto;
import com.jcble.parking.common.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;

	/**
	 * 管理员登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseDto getParkings(@RequestBody AdminDto adminLoginDto) throws Exception {
		ResponseDto resp = new ResponseDto();
		try {
			if (adminLoginDto == null || StringUtils.isBlank(adminLoginDto.getAccount())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			AdminDto admin = adminService.login(adminLoginDto);
			resp.setPayload(admin);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

	/**
	 * 管理员找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/password", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public ResponseDto resetPassword(@RequestBody AdminDto adminDto,HttpServletRequest request) throws Exception {
		ResponseDto resp = new ResponseDto();
		try {
			if (adminDto == null || StringUtils.isBlank(adminDto.getAccount())
					|| StringUtils.isBlank(adminDto.getPassword())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			String code = (String) request.getSession().getAttribute(adminDto.getAccount());
			if(adminDto.getVcode().equals(code)) {
				request.getSession().removeAttribute(adminDto.getAccount());
			}else {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10006);
			}
			adminService.resetPassword(adminDto);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

}
