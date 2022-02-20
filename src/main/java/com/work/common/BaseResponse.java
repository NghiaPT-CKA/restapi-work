package com.work.common;

import java.util.List;

/**
 * Base response
 * 
 * @author NghiaPT12
 *
 */
public class BaseResponse<T> {
	/** Status */
	private String status;
	/** Error message */
	private String errorMessage;
	/** Data */
	private List<T> data;

	/**
	 * Get status
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set status
	 * 
	 * @return status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get error Message
	 * 
	 * @return errorMessage
	 */
	public String getMessage() {
		return errorMessage;
	}

	/**
	 * Set status
	 * 
	 * @return status
	 */
	public void setMessage(String message) {
		this.errorMessage = message;
	}

	/**
	 * Get data
	 * 
	 * @return data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * Set data
	 * 
	 * @return data
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
}
