package com.github.ecsoya.uploader.aliyun;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.AbstractFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.google.auto.service.AutoService;

/**
 * The Class AliyunFileUploader.
 */
@AutoService(IFileUploader.class)
public class AliyunFileUploader extends AbstractFileUploader {

	/**
	 * Test upload config.
	 *
	 * @param config the config
	 * @throws FileUploadException the file upload exception
	 */
	@Override
	protected void testUploadConfig(UploadConfig config) throws FileUploadException {
		config.testCloudValidated();
	}

	/**
	 * Do upload.
	 *
	 * @param files  the files
	 * @param config the config
	 * @return the list
	 * @throws FileUploadException the file upload exception
	 */
	@Override
	protected List<String> doUpload(List<UploadData> files, UploadConfig config) throws FileUploadException {

		final OSS client = new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKey(),
				config.getSecretKey());

		final List<String> result = new ArrayList<>();
		try {
			for (final UploadData file : files) {
				final String fileName = getFileName(file, config);
				final String contentType = file.getContentType();
				final Long length = file.getLength();
				final InputStream inputStream = file.getInputStream();
				if (inputStream != null && inputStream.available() > 0) {
					final ObjectMetadata metadata = new ObjectMetadata();
					if (length != null) {
						metadata.setContentLength(length);
					}
					if (contentType != null) {
						metadata.setContentType(contentType);
					}
					client.putObject(config.getBucket(), fileName, inputStream, metadata);
				}
				final String url = getUrl(config.getBaseUrl(), fileName);
				result.add(url);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated
			 * resources.
			 */
			client.shutdown();
		}

		return result;
	}

}