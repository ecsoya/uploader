package com.github.ecsoya.uploader.huawei;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.AbstractFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.google.auto.service.AutoService;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.ObjectMetadata;

@AutoService(IFileUploader.class)
public class HuaweiFileUploader extends AbstractFileUploader {

	public HuaweiFileUploader() {
		super();
	}

	@Override
	protected void testUploadConfig(UploadConfig config) throws FileUploadException {
		config.testCloudValidated();
	}

	@Override
	protected List<String> doUpload(List<UploadData> files, UploadConfig config) throws FileUploadException {
		ObsConfiguration obs = new ObsConfiguration();
		obs.setEndPoint(config.getEndpoint());
		obs.setAuthType(AuthTypeEnum.OBS);
		List<String> result = new ArrayList<>();
		try (ObsClient client = new ObsClient(config.getAccessKey(), config.getSecretKey(), obs)) {
			String bucket = config.getBucket();
			for (UploadData file : files) {
				final String path = getFileName(file, config);
				final InputStream inputStream = file.getInputStream();
				final String contentType = file.getContentType();
				final Long length = file.getLength();
				ObjectMetadata meta = new ObjectMetadata();
				if (length != null) {
					meta.setContentLength(length);
				}
				if (contentType != null) {
					meta.setContentType(contentType);
				}
				if (inputStream != null && inputStream.available() > 0) {
					client.putObject(bucket, path, inputStream, meta);
				} else if (file.getDatas() != null && file.getDatas().length != 0) {
					client.putObject(bucket, path, new ByteArrayInputStream(file.getDatas()), meta);
				}
				result.add("https://" + bucket + "." + config.getEndpoint() + "/" + path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
