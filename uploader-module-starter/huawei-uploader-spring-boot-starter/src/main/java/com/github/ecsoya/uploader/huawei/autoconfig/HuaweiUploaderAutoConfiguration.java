package com.github.ecsoya.uploader.huawei.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;
import com.github.ecsoya.uploader.huawei.HuaweiFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class HuaweiUploaderAutoConfiguration {

	@Bean
	public IFileUploader fileUploader() {
		return new HuaweiFileUploader();
	}
}
