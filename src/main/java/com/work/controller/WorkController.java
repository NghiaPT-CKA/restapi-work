package com.work.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.Service.WorkService;
import com.work.common.BaseResponse;
import com.work.entity.Work;
import com.work.enums.StatusResponse;
import com.work.validate.Validate;

/**
 * Work controller
 * 
 * @author NghiaPT12
 *
 */
@RestController
@RequestMapping(value = "/work")
public class WorkController {

	/** work service */
	@Autowired
	private WorkService workService;

	/**
	 * Get work list
	 * 
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseResponse<Work> getAllWork() {
		// Create base response
		BaseResponse<Work> response = new BaseResponse<Work>();

		try {
			// Get work list
			List<Work> workList = workService.getAll();

			// Setting response information successfully
			response.setStatus(StatusResponse.SUCCESS.toString());
			response.setData(workList);
		} catch (Exception e) {
			e.printStackTrace();

			// Setting response information fail
			response.setStatus(StatusResponse.FAIL.toString());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Register work
	 * 
	 * @param input
	 * @return BaseResponse
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResponse<?> registerWork(@RequestBody String input) {
		// Create base response
		BaseResponse<?> response = new BaseResponse<>();

		try {
			// Validate input
			Map<String, String> inputMap = new ObjectMapper().readValue(input, Map.class);
			response = Validate.validateInput(inputMap, response, true);
			if (!StatusResponse.SUCCESS.toString().equals(response.getStatus())) {
				return response;
			}
			;

			// Register work
			Work work = new Work(inputMap, null);
			workService.save(work);

			// Setting response information successfully
			response.setStatus(StatusResponse.SUCCESS.toString());
		} catch (Exception e) {
			e.printStackTrace();

			// Setting response information fail
			response.setStatus(StatusResponse.FAIL.toString());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Update work
	 * 
	 * @param input
	 * @return BaseResponse
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public BaseResponse<?> updateWork(@RequestBody String input, @PathVariable Integer id) {
		// Create base response
		BaseResponse<?> response = new BaseResponse<>();
		try {
			// Validate input
			Map<String, String> inputMap = new ObjectMapper().readValue(input, Map.class);
			response = Validate.validateInput(inputMap, response, false);
			if (!StatusResponse.SUCCESS.toString().equals(response.getStatus())) {
				return response;
			}
			;

			// Update work
			Work work = new Work(inputMap, id);
			workService.update(work);

			// Setting response information successfully
			response.setStatus(StatusResponse.SUCCESS.toString());
		} catch (Exception e) {
			e.printStackTrace();

			// Setting response information fail
			response.setStatus(StatusResponse.FAIL.toString());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Delete work
	 * 
	 * @param id
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseResponse<?> deleteWork(@PathVariable Integer id) {
		// Create base response
		BaseResponse<?> response = new BaseResponse<>();
		if (workService.isExist(id)) {
			// If work is exist
			// Delete work
			workService.delete(id);

			// Setting response information successfully
			response.setStatus(StatusResponse.SUCCESS.toString());
			return response;
		}

		// Setting response information fail
		response.setStatus(StatusResponse.FAIL.toString());
		response.setMessage("work dose not exit");

		return response;
	}
}
