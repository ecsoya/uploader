package com.github.ecsoya.uploader.huawei.autoconfig;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.github.ecsoya.uploader.autoconfig.UploaderAutoConfiguration;

@Configuration
@ImportAutoConfiguration(classes = UploaderAutoConfiguration.class)
public class HuaweiUploaderAutoConfiguration {

}
