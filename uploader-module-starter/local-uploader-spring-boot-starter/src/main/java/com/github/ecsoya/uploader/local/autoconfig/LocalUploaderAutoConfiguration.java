package com.github.ecsoya.uploader.local.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;
import com.github.ecsoya.uploader.local.LocalFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class LocalUploaderAutoConfiguration {

	@ConditionalOnMissingBean(IFileUploader.class)
	public IFileUploader fileUploader() {
		return new LocalFileUploader();
	}
}
