package com.pvrschcms.pvrcinemaschdulernew.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ErrorResponse;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.product.model.DeptModel;
import com.pvrschcms.pvrcinemaschdulernew.product.service.PvrDeptService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/web/dept/")
public class PvrDeptController {
	private static final Logger logger = LoggerFactory.getLogger(PvrDeptController.class);
	
	@Autowired
	private PvrDeptService deptService;
	
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public ResponseEntity<ResponseDto<DeptModel,ErrorResponse>> createDepartment(@RequestParam String departmentName,
			@RequestParam(required = false, defaultValue = "") String code
			,@RequestParam("files") MultipartFile[] files) {
		logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code);
		ResponseDto resp = new ResponseDto();
		ResponseEntity<ResponseDto<DeptModel,ErrorResponse>> response = null;
		try {
			DeptModel dpt = null;//deptService.createDepartment(departmentName,code,files);
			resp.setSuccess(true);
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.USER_CREATED_SUCCESSFULY);
			resp.setData(dpt);
			response = new ResponseEntity<>(resp, HttpStatus.OK);
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code+" exc :: "+e.getMessage());
			resp.setSuccess(false);
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			ErrorResponse error = new ErrorResponse(e.getMessage());
			//resp.setError(error);
			response = new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	
	@RequestMapping(value = "detail")
	public ResponseDto getDeptDetail(@RequestParam String departmentId) {
		ResponseDto resp = new ResponseDto();
		try {
			DeptModel dpt = deptService.getDeptDetail(departmentId);
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			resp.setData(dpt);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	
	@RequestMapping(value = "edit/detail")
	public ResponseDto getEditDeptDetail(@RequestParam String departmentId,@RequestParam String departmentName,
			@RequestParam(required = false, defaultValue = "") String code
			,@RequestParam("files") MultipartFile[] files) {
		ResponseDto resp = new ResponseDto();
		try {
			DeptModel dpt = null;//deptService.editDeptDetail(departmentId,departmentName,code,files);
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			resp.setData(dpt);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	@RequestMapping(value = "list")
	public ResponseDto getDeptList() {
		ResponseDto resp = new ResponseDto();
		try {
			List<DeptModel> dpt = deptService.getDeptList();
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			resp.setData(dpt);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	@RequestMapping(value = "spc/list")
	public ResponseDto getDeptWebList() {
		ResponseDto resp = new ResponseDto();
		try {
			List<DeptModel> dpt = deptService.getDeptList();
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			resp.setData(dpt);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	
}
