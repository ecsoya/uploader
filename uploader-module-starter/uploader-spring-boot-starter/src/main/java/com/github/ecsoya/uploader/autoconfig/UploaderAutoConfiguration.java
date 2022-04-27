package com.github.ecsoya.uploader.autoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UploaderProperties.class)
public class UploaderAutoConfiguration {

	@Bean
	public UploaderService uploaderService() {
		return new UploaderService();
	}
}
