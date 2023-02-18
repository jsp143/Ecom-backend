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
import com.pvrschcms.pvrcinemaschdulernew.product.model.SubDeptModel;
import com.pvrschcms.pvrcinemaschdulernew.product.model.view.SubDeptViewModel;
import com.pvrschcms.pvrcinemaschdulernew.utils.model.ImageResponse;
import com.pvrschcms.pvrcinemaschdulernew.product.repository.PvrSubDeptRepository;
import com.pvrschcms.pvrcinemaschdulernew.product.repository.view.SubDeptViewRepository;

@Service
public class PvrSubDeptService {
	private static final Logger logger = LoggerFactory.getLogger(PvrDeptService.class);
	
	@Autowired
	private PvrSubDeptRepository subDeptRepository;
	@Autowired
	private SubDeptViewRepository subDeptViewRepository;
	@Autowired
    private FileStorageService fileStorageService;
	
	public SubDeptModel createSubDepartment(String departmentName, String code, String deptId, MultipartFile file) {
		logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code);
		try {
			if(!file.isEmpty()) {
				//List<ImageResponse> fileslist = Arrays.asList(files).stream().map(file -> uploadfileone(file)).collect(Collectors.toList());
				ImageResponse filedata = uploadfileone(file);
				SubDeptModel subdept = new SubDeptModel(UUID.randomUUID().toString(),departmentName,code,deptId,filedata.getFileName(),filedata.getContentType(),filedata.getFileSize(),filedata.getUploadDir());
				subDeptRepository.save(subdept);
				return subdept;
			}else {
				SubDeptModel dept = new SubDeptModel(UUID.randomUUID().toString(),departmentName,code,deptId);
				subDeptRepository.save(dept);
				return dept;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code+" ::excp :: "+e.getMessage());
			return null;
		}
		
	}
	
	public ImageResponse uploadfileone(MultipartFile file) {

        String fileName = fileStorageService.storeFile(file,"SUBDEPT");
        String[] filedet = fileName.split("\\|");

        return new ImageResponse(filedet[0],file.getContentType(), String.valueOf(file.getSize()),filedet[1]);
    }

	public SubDeptModel getSubDeptDetail(String departmentId) {
		logger.debug(this.getClass().getName()+".getDeptDetail() departmentId :: "+departmentId);
		try {
			SubDeptModel dept = subDeptRepository.findById(departmentId);
			return dept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() departmentId :: "+departmentId+" ::excp :: "+e.getMessage());
			return null;
		}
	}

	public List<SubDeptViewModel> getSubDeptList() {
		logger.debug(this.getClass().getName()+".getDeptList() departmentId :: ");
		try {
			List<SubDeptViewModel> dept = subDeptViewRepository.findAllSubDept();
			return dept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".getDeptList() departmentId :: ::excp :: "+e.getMessage());
			return null;
		}
	}

	public SubDeptModel editSubDeptDetail(String subDeptId, String departmentName, String code, MultipartFile file) {
		logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code);
		try {
			SubDeptModel subdept = getSubDeptDetail(subDeptId);
			if(!file.isEmpty()) {
				ImageResponse filedata = uploadfileone(file);
				if(!ObjectUtils.isEmpty(filedata)) {
					subdept.setFileName(filedata.getFileName());
					subdept.setFileSize(filedata.getFileSize());
					subdept.setContentType(filedata.getContentType());
				}
			}
			subdept.setCode(code);
			subdept.setName(departmentName);
			subDeptRepository.save(subdept);
			return subdept;
		}catch (Exception e) {
			logger.debug(this.getClass().getName()+".createDepartment() departmentName :: "+departmentName+" :: code :: "+code+" ::excp :: "+e.getMessage());
			return null;
		}
	}
	
	
}
