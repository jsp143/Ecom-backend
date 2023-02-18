package com.pvrschcms.pvrcinemaschdulernew.product.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pvrschcms.pvrcinemaschdulernew.product.controller.PvrDeptController;
import com.pvrschcms.pvrcinemaschdulernew.product.model.DeptModel;
import com.pvrschcms.pvrcinemaschdulernew.product.service.PvrDeptService;

@Controller
@RequestMapping("/web/department/")
public class PvrDeptWebController {
private static final Logger logger = LoggerFactory.getLogger(PvrDeptController.class);
	
	@Autowired
	private PvrDeptService deptService;
	
	@RequestMapping("list")
	public String viewdeptList(Model model) {
	    List<DeptModel> listDepartment = deptService.getDeptList();
	    model.addAttribute("listDepartment", listDepartment);
	    return "admin-departments";
	}
	
	@RequestMapping("save")
	public String saveDept(@RequestParam String deptName
			,@RequestParam(required = false, defaultValue = "") String deptCode,@RequestParam("file") MultipartFile file,Model model) {
		try {
			DeptModel dpt = deptService.createDepartment(deptName,deptCode, file);
		   // List<DeptModel> listDepartment = deptService.getDeptList();
		   // model.addAttribute("listDepartment", listDepartment);
		    return "redirect:/web/department/list";
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/web/department/list";
		}
	}
	
	@RequestMapping("update")
	public String updateDept(@RequestParam String deptIdEdit,@RequestParam String deptNameEdit
			,@RequestParam(required = false, defaultValue = "") String deptCodeEdit,@RequestParam("fileEdit") MultipartFile fileEdit,Model model) {
		try {
			DeptModel dpt = deptService.editDeptDetail(deptIdEdit,deptNameEdit,deptCodeEdit, fileEdit);
		    //List<DeptModel> listDepartment = deptService.getDeptList();
		    //model.addAttribute("listDepartment", listDepartment);
		    return "redirect:/web/department/list";
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/web/department/list";
		}
	}
	
	@RequestMapping("delete")
	public String deleteDept(@RequestParam String deptIdDel,Model model) {
		try {
			DeptModel dpt = deptService.deleteDept(deptIdDel);
		    //List<DeptModel> listDepartment = deptService.getDeptList();
		    //model.addAttribute("listDepartment", listDepartment);
		    return "redirect:/web/department/list";
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/web/department/list";
		}
	}
}
