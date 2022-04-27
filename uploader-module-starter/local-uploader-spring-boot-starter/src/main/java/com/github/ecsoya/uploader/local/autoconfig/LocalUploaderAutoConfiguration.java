package com.github.ecsoya.uploader.local.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class LocalUploaderAutoConfiguration {

}
