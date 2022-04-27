package com.github.ecsoya.uploader.local;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.AbstractFileUploader;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.github.ecsoya.uploader.util.Utility;
import com.google.auto.service.AutoService;

/**
 * The Class LocalFileUploader.
 */
@AutoService(IFileUploader.class)
public class LocalFileUploader extends AbstractFileUploader {

	/**
	 * Test upload config.
	 *
	 * @param config the config
	 * @throws FileUploadException the file upload exception
	 */
	@Override
	protected void testUploadConfig(UploadConfig config) throws FileUploadException {
		if (!"local".equalsIgnoreCase(config.getType())) {
			throw new FileUploadException("Type missmatched");
		}
		if (Utility.isEmpty(config.getUploadPath())) {
			throw new FileUploadException("Upload path should not empty");
		}
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
		if (config == null) {
			throw new FileUploadException("配置错误");
		}
		if (files == null || files.isEmpty()) {
			return Collections.emptyList();
		}
		String uploadPath = config.getUploadPath();
		final File root = new File(uploadPath).getAbsoluteFile();
		if (!root.exists()) {
			try {
				root.mkdirs();
			} catch (final Exception e) {
				throw new FileUploadException("Could not init upload path");
			}
		}

		final List<String> result = new ArrayList<String>();
		for (final UploadData data : files) {
			final String fileName = getFileName(data, config);
			final File target = new File(root, fileName);
			try {
				if (!target.getParentFile().exists()) {
					target.getParentFile().mkdirs();
				}
				final InputStream in = data.getInputStream();
				if (in != null && in.available() > 0) {
					Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} else {
					final byte[] bytes = data.getDatas();
					if (bytes != null && bytes.length > 0) {
						Files.write(target.toPath(), bytes);
					}
				}
				result.add(root.toPath().relativize(target.toPath()).toString());
			} catch (final Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return result;
	}

}
