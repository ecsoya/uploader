package com.github.ecsoya.uploader.tencent.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;
import com.github.ecsoya.uploader.tencent.TencentFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class TencentUploaderAutoConfiguration {

	@ConditionalOnMissingBean(IFileUploader.class)
	public IFileUploader fileUploader() {
		return new TencentFileUploader();
	}
}
