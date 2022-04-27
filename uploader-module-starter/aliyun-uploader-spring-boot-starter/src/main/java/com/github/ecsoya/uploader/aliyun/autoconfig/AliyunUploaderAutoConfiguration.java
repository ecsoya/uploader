package com.github.ecsoya.uploader.aliyun.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class AliyunUploaderAutoConfiguration {

}
