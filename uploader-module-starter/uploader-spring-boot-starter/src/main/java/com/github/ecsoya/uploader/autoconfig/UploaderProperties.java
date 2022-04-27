package com.github.ecsoya.uploader.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.github.ecsoya.uploader.core.UploadConfig;

@ConfigurationProperties(prefix = "spring.uploader")
public class UploaderProperties extends UploadConfig {

}
