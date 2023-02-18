package com.pvrschcms.pvrcinemaschdulernew.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pvrschcms.pvrcinemaschdulernew.product.model.SubDeptModel;

public interface PvrSubDeptRepository extends JpaRepository<SubDeptModel, Integer> {
	SubDeptModel findById(String id);
	List<SubDeptModel> findAllByActiveTrue();
}
