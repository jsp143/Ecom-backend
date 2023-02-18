package com.pvrschcms.pvrcinemaschdulernew.utils.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageResponse {
	private String fileName;
	private String contentType;
	private String fileSize;
	private String uploadDir;
	public ImageResponse() {
		super();
	}
	public ImageResponse(String fileName, String contentType, String fileSize, String uploadDir) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.uploadDir = uploadDir;
	}
	
}
