package com.pvrschcms.pvrcinemaschdulernew.product.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pvrschcms.pvrcinemaschdulernew.product.model.view.SubDeptViewModel;

public interface SubDeptViewRepository extends JpaRepository<SubDeptViewModel, Integer> {
	@Query(value = "SELECT sd.id,d.name as deptName,sd.name,sd.code,sd.dept_id as deptId,sd.file_name as fileName,sd.active FROM sys_subdept sd inner join sys_department d on d.id=sd.dept_id", 
			  nativeQuery = true)
	List<SubDeptViewModel> findAllSubDept();
	
}
