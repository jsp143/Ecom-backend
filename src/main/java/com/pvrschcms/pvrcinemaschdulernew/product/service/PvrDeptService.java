package com.pvrschcms.pvrcinemaschdulernew.product.service;

import java.util.List;
import java.util.UUID;

import com.pvrschcms.pvrcinemaschdulernew.utils.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pvrschcms.pvrcinemaschdulernew.product.model.DeptModel;
import com.pvrschcms.pvrcinemaschdulernew.utils.model.ImageResponse;
import com.pvrschcms.pvrcinemaschdulernew.product.repository.PvrDeptRepository;
@Service
public class PvrDeptService {
	private static final Logger logger = LoggerFactory.getLogger(PvrDeptService.class);
	//$()
	
	@Autowired
	private PvrDeptRepository deptRepository;
	@Autowired
    private FileStorageService fileStorageService;
	
	public DeptModel createDepartment(String departmentName, String code, MultipartFile file) {
		logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code);
		try {
			if(!file.isEmpty()) {
				//List<ImageResponse> fileslist = Arrays.asList(files).stream().map(file -> uploadfileone(file)).collect(Collectors.toList());
				ImageResponse filedata = uploadfileone(file);
				DeptModel dept = new DeptModel(UUID.randomUUID().toString(),departmentName,code,filedata.getFileName(),filedata.getContentType(),filedata.getFileSize(),filedata.getUploadDir());
				deptRepository.save(dept);
				return dept;
			}else {
				DeptModel dept = new DeptModel(UUID.randomUUID().toString(),departmentName,code);
				deptRepository.save(dept);
				return dept;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code+" ::excp :: "+e.getMessage());
			return null;
		}
		
	}
	
	public ImageResponse uploadfileone(MultipartFile file) {

        String fileName = fileStorageService.storeFile(file,"DEPT");
        String[] filedet = fileName.split("\\|");
        //return new ImageResponse(filedet[0],file.getContentType(), String.valueOf(file.getSize()),"/upload/dept/");
        return new ImageResponse(filedet[0],file.getContentType(), String.valueOf(file.getSize()),filedet[1]);
    }

	public DeptModel getDeptDetail(String departmentId) {
		logger.debug(this.getClass().getName()+".getDeptDetail() departmentId :: "+departmentId);
		try {
			DeptModel dept = deptRepository.findById(departmentId);
			return dept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() departmentId :: "+departmentId+" ::excp :: "+e.getMessage());
			return null;
		}
	}

	public List<DeptModel> getDeptList() {
		logger.debug(this.getClass().getName()+".getDeptList() departmentId :: ");
		try {
			List<DeptModel> dept = deptRepository.findAllByActiveTrue();
			return dept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".getDeptList() departmentId :: ::excp :: "+e.getMessage());
			return null;
		}
	}

	public DeptModel editDeptDetail(String departmentId, String departmentName, String code, MultipartFile file) {
		logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code);
		try {
			//List<ImageResponse> fileslist = Arrays.asList(files).stream().map(file -> uploadfileone(file)).collect(Collectors.toList());
			DeptModel dept = getDeptDetail(departmentId);
			if(!file.isEmpty()) {
				ImageResponse filedata = uploadfileone(file);
				if(!ObjectUtils.isEmpty(filedata)) {
					dept.setFileName(filedata.getFileName());
					dept.setFileSize(filedata.getFileSize());
					dept.setContentType(filedata.getContentType());
				}
			}
			dept.setCode(code);
			dept.setName(departmentName);
			
			deptRepository.save(dept);
			return dept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code+" ::excp :: "+e.getMessage());
			return null;
		}
	}

	public DeptModel deleteDept(String deptIdDel) {
		try {
			DeptModel dept = getDeptDetail(deptIdDel);
			dept.setActive(false);
			deptRepository.save(dept);
			return dept;
		}catch(Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() deleteDept :: "+deptIdDel+" ::excp :: "+e.getMessage());
			return null;
		}
	}

}
