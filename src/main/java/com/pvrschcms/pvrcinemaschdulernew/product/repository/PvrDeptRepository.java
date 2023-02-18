package com.pvrschcms.pvrcinemaschdulernew.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.product.model.DeptModel;

public interface PvrDeptRepository extends JpaRepository<DeptModel, Integer> {

	DeptModel findById(String id);


	List<DeptModel> findAllByActiveTrue();

}
