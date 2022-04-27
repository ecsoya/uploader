package com.github.ecsoya.uploader.uploader;

import java.util.List;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;

/**
 * The Interface FileUploader.
 */
public interface IFileUploader {

	boolean isAdapter(UploadConfig config);

	/**
	 * Upload.
	 *
	 * @param file   the file
	 * @param config the config
	 * @return the string
	 * @throws FileUploadException the file upload exception
	 */
	String upload(UploadData file, UploadConfig config) throws FileUploadException;

	/**
	 * Upload.
	 *
	 * @param files  the files
	 * @param config the config
	 * @return the list
	 * @throws FileUploadException the file upload exception
	 */
	List<String> upload(List<UploadData> files, UploadConfig config) throws FileUploadException;
}
