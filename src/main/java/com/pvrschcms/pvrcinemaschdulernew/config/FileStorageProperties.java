package com.pvrschcms.pvrcinemaschdulernew.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String uploadDir;
}
