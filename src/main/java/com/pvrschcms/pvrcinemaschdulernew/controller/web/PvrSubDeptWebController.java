package com.pvrschcms.pvrcinemaschdulernew.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pvrschcms.pvrcinemaschdulernew.controller.PvrDeptController;
import com.pvrschcms.pvrcinemaschdulernew.model.product.DeptModel;
import com.pvrschcms.pvrcinemaschdulernew.model.product.SubDeptModel;
import com.pvrschcms.pvrcinemaschdulernew.model.product.view.SubDeptViewModel;
import com.pvrschcms.pvrcinemaschdulernew.service.PvrDeptService;
import com.pvrschcms.pvrcinemaschdulernew.service.PvrSubDeptService;

@Controller
@RequestMapping("/web/subdept/")
public class PvrSubDeptWebController {
	private static final Logger logger = LoggerFactory.getLogger(PvrDeptController.class);
	
	@Autowired
	private PvrSubDeptService subdeptService;
	@Autowired
	private PvrDeptService deptService;
	
	@RequestMapping("list")
	public String viewdeptList(Model model) {
	    List<SubDeptViewModel> listSubDept = subdeptService.getSubDeptList();
	    List<DeptModel> dpt = deptService.getDeptList();
	    model.addAttribute("listSubDept", listSubDept);
	    model.addAttribute("listDept", dpt);
	    return "admin-subdept";
	}
	@RequestMapping("save")
	public String saveDept(@RequestParam String subDeptName,@RequestParam String deptId
			,@RequestParam(required = false, defaultValue = "") String subDeptCode,@RequestParam("file") MultipartFile file,Model model) {
		try {
			SubDeptModel dpt = subdeptService.createSubDepartment(subDeptName,subDeptCode,deptId, file);
		    //List<SubDeptModel> listDepartment = subdeptService.getSubDeptList();
		    //model.addAttribute("listDepartment", listDepartment);
		    return "redirect:/web/subdept/list";
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/web/subdept/list";
		}
	}
	
	@RequestMapping("update")
	public String updateDept(@RequestParam String subDeptIdEdit,@RequestParam String subDeptNameEdit
			,@RequestParam(required = false, defaultValue = "") String subDeptCodeEdit,@RequestParam("file") MultipartFile fileEdit,Model model) {
		try {
			//DeptModel dpt = subdeptService.editDeptDetail(subDeptIdEdit,subDeptNameEdit,subDeptCodeEdit, fileEdit);
		    //List<DeptModel> listDepartment = deptService.getDeptList();
		    //model.addAttribute("listDepartment", listDepartment);
		    return "redirect:/web/subdept/list";
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/web/subdept/list";
		}
	}
}
