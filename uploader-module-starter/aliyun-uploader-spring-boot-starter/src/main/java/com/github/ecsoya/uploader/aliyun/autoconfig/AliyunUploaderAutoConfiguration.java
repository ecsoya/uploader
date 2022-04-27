package com.github.ecsoya.uploader.aliyun.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.aliyun.AliyunFileUploader;
import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;
import com.github.ecsoya.uploader.uploader.IFileUploader;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class AliyunUploaderAutoConfiguration {

	@Bean
	public IFileUploader fileUploader() {
		return new AliyunFileUploader();
	}
}
