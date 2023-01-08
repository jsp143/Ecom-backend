package com.pvrschcms.pvrcinemaschdulernew.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pvrschcms.pvrcinemaschdulernew.model.product.SubDeptModel;

public interface PvrSubDeptRepository extends JpaRepository<SubDeptModel, Integer> {
	SubDeptModel findById(String id);
	List<SubDeptModel> findAllByActiveTrue();
}
