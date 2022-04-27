package com.github.ecsoya.uploader.autoconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.github.ecsoya.uploader.util.UploaderUtility;

@Service
public class UploaderService {

	@Autowired(required = false)
	private IFileUploader fileUploader;

	@Autowired
	private UploaderProperties config;

	private IFileUploader getFileUploader() {
		if (fileUploader != null) {
			return fileUploader;
		}
		return UploaderUtility.getFileLoader(config);
	}

	public String upload(MultipartFile file) throws FileUploadException {
		return getFileUploader().upload(build(file), config);
	}

	public List<String> upload(List<MultipartFile> files) throws FileUploadException {
		if (files == null) {
			return Collections.emptyList();
		}
		List<UploadData> datas = files.stream().map(f -> build(f)).collect(Collectors.toList());
		return getFileUploader().upload(datas, config);
	}

	/**
	 * Builds the.
	 *
	 * @param file the file
	 * @return the upload data
	 * @throws FileUploadException the file upload exception
	 */
	private static UploadData build(MultipartFile file) throws FileUploadException {
		if (file == null) {
			throw new RuntimeException("Upload file is empty");
		}

		UploadData uploadData;
		try {
			final InputStream inputStream = file.getInputStream();
			final byte[] datas = file.getBytes();
			uploadData = new UploadData();

			final String name = file.getOriginalFilename();
			if (name != null && name.indexOf(".") != -1) {
				uploadData.setExtension(name.substring(name.lastIndexOf(".")));
			}
			uploadData.setFileName(name);
			uploadData.setContentType(file.getContentType());
			uploadData.setLength(file.getSize());
			uploadData.setInputStream(inputStream);
			uploadData.setDatas(datas);
			return uploadData;
		} catch (final IOException e) {
			throw new FileUploadException(e.getLocalizedMessage());
		}
	}
}
