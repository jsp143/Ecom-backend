package com.pvrschcms.pvrcinemaschdulernew.controller;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pvrschcms.pvrcinemaschdulernew.service.FileStorageService;

@RestController
@RequestMapping("/web/file/")
public class FileServeController {
	@Autowired 
	private FileStorageService fileService;
	
	 @GetMapping(path = "/get/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	 @ResponseBody
	 public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
         final Resource retrievedImage = fileService.loadFileAsResource(imageName);
         return IOUtils.toByteArray(retrievedImage.getInputStream());
	 }
}
