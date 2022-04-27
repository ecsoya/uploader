package com.github.ecsoya.uploader.uploader;

import java.util.List;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;

public class UnsupportedFileUploader implements IFileUploader {

	public static final IFileUploader INSTANCE = new UnsupportedFileUploader();

	@Override
	public boolean isAdapter(UploadConfig config) {
		return true;
	}

	@Override
	public String upload(UploadData file, UploadConfig config) throws FileUploadException {
		throw new FileUploadException("Unsupported");
	}

	@Override
	public List<String> upload(List<UploadData> files, UploadConfig config) throws FileUploadException {
		throw new FileUploadException("Unsupported");
	}

}
