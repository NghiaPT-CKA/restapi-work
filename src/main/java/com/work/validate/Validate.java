package com.work.validate;

import java.util.Map;

import com.work.common.BaseResponse;
import com.work.enums.StatusResponse;
import com.work.enums.StatusWork;
import com.work.util.DateTimeUtils;

/**
 * Validate
 * 
 * @author NghiaPT
 *
 */
public class Validate {

	/**
	 * Validate input
	 * 
	 * @param workInputMap
	 * @param baseResponse
	 * @param isRegisterWork
	 * @return BaseResponse
	 */
	public static BaseResponse<?> validateInput(Map<String, String> workInputMap, BaseResponse<?> baseResponse, boolean isRegisterWork) {
		// Create base response is successfully
		baseResponse.setStatus(StatusResponse.SUCCESS.toString());
		
		String startingDate =  workInputMap.get("startingDate");
		String endingDate =  workInputMap.get("endingDate");
		String status =  workInputMap.get("status");
		
		if (isRegisterWork) {
			if(startingDate == null) {
				baseResponse.setStatus(StatusResponse.FAIL.toString());
				baseResponse.setMessage("startingDate can't be empty");
				return baseResponse;
			}
			
			if(endingDate == null) {
				baseResponse.setStatus(StatusResponse.FAIL.toString());
				baseResponse.setMessage("endingDate can't be empty");
				return baseResponse;
			}
			
			if(status == null) {
				baseResponse.setStatus(StatusResponse.FAIL.toString());
				baseResponse.setMessage("status can't be empty");
				return baseResponse;
			}
			
		}
		
		// Validate starting date
		if (startingDate != null && !DateTimeUtils.isValid(startingDate)) {
			baseResponse.setStatus(StatusResponse.FAIL.toString());
			baseResponse.setMessage("startingDate's format is YYYYMMDD");
			return baseResponse;
		}
		
		// Validate ending date
		if (endingDate != null && !DateTimeUtils.isValid(endingDate)) {
			baseResponse.setStatus(StatusResponse.FAIL.toString());
			baseResponse.setMessage("endingDate's format is YYYYMMDD");
			return baseResponse;
		}
		
		// Validate status
		if (status != null
				&& !StatusWork.PLANNING.getMessage().equals(status)
				&& !StatusWork.DOING.getMessage().equals(status)
				&& !StatusWork.COMPLETE.getMessage().equals(status)) {
			baseResponse.setStatus(StatusResponse.FAIL.toString());
			baseResponse.setMessage("status does not exist( Planning, Doing, Complete)");
			return baseResponse;
		}

		return baseResponse;
	}
}
