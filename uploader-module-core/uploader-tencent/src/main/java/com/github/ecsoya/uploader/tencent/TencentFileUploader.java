package com.github.ecsoya.uploader.tencent;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.AbstractFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.github.ecsoya.uploader.util.Utility;
import com.google.auto.service.AutoService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;

/**
 * The Class TencentFileUploader.
 */
@AutoService(IFileUploader.class)
public class TencentFileUploader extends AbstractFileUploader {

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

		final COSCredentials cred = new BasicCOSCredentials(config.getAccessKey(), config.getSecretKey());
		final String endpoint = config.getEndpoint();
		final ClientConfig clientConfig = new ClientConfig();
		clientConfig.setRegion(new com.qcloud.cos.region.Region(endpoint));
		final COSClient client = new COSClient(cred, clientConfig);

		final String bucket = config.getBucket();
		final List<String> result = new ArrayList<>();
		try {
			for (final UploadData file : files) {
				final String contentType = file.getContentType();
				final Long length = file.getLength();
				final String path = getFileName(file, config);
				final InputStream inputStream = file.getInputStream();
				if (inputStream != null && inputStream.available() > 0) {
					final ObjectMetadata metadata = new ObjectMetadata();
					if (length != null) {
						metadata.setContentLength(length);
					}
					if (contentType != null) {
						metadata.setContentType(contentType);
					}
					client.putObject(bucket, path, inputStream, metadata);
				} else if (file.getDatas() != null && file.getDatas().length != 0) {
					client.putObject(bucket, path, new String(file.getDatas()));
				}
				// 加速域名
				if (Utility.isNotEmpty(config.getBaseUrl())) {
					result.add(config.getBaseUrl() + "/" + path);
				} else {
					result.add("https://" + bucket + ".cos." + endpoint + ".myqcloud.com/" + path);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		return result;
	}

}
