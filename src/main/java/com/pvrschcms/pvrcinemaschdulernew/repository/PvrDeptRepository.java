package com.pvrschcms.pvrcinemaschdulernew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.model.product.DeptModel;

public interface PvrDeptRepository extends JpaRepository<DeptModel, Integer> {

	DeptModel findById(String id);


	List<DeptModel> findAllByActiveTrue();

}
