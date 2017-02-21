package com.jcble.parking.api.controller.admin;

import java.util.List;

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
import com.jcble.parking.common.dto.AlarmMsgReqDto;
import com.jcble.parking.common.dto.ResponseDto;
import com.jcble.parking.common.model.devices.AlarmMsgDto;
import com.jcble.parking.common.service.admin.AlarmMsgService;

import baseproj.common.mybatis.page.PageParameter;

/**
 * 告警消息相关控制器
 * 
 * @author Jingdong Wang
 *
 */
@Controller
public class AlarmMsgController extends BaseController {

	@Autowired
	private AlarmMsgService alarmMsgService;

	/**
	 * 获取告警消息
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/alarm_messages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseDto getAlarmMessages(HttpServletRequest request) throws Exception {
		ResponseDto resp = new ResponseDto();
		AlarmMsgDto dto = new AlarmMsgDto();
		try {
			String pageIndexStr = request.getParameter("page"); // 当前页
			String pageSizeStr = request.getParameter("page_size");// 页数据条数
			String parkinglotIdStr = request.getParameter("parkinglotId");// 停车场唯一标识
			String deviceType = request.getParameter("deviceType"); // 设备类型
			String snagType = request.getParameter("snagType"); // 故障类型
			String operatorIdStr = request.getParameter("operatorId"); // 管理员唯一标识
			Integer pageIndex = 1;
			Integer pageSize = 10;
			Integer operatorId = null;
			if (StringUtils.isNotBlank(operatorIdStr)) {
				operatorId = Integer.parseInt(operatorIdStr);
			} else {
				//管理员唯一标识不能为空
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			if (StringUtils.isNotBlank(pageIndexStr)) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			if (StringUtils.isNotBlank(pageSizeStr)) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			if (StringUtils.isNotBlank(parkinglotIdStr)) {
				dto.setParkingLotId(Integer.parseInt(parkinglotIdStr));
			}
			dto.setPage(new PageParameter(pageIndex, pageSize));
			dto.setOperatorId(operatorId);
			dto.setDeviceType(deviceType);
			dto.setSnagType(snagType);
			List<AlarmMsgDto> data = alarmMsgService.getAlarmMessages(dto);
			resp.setPayload(data);
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
	
	/**
	 * 处理告警消息
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/alarm_messages", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public ResponseDto handAlarmMessages(@RequestBody AlarmMsgReqDto dto) throws Exception {
		ResponseDto resp = new ResponseDto();
		try {
			if(dto == null || dto.getOperatorId() == null || dto.getAlarmMessage() == null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10001);
			}
			alarmMsgService.handAlarmMessages(dto);
			
		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

}
















