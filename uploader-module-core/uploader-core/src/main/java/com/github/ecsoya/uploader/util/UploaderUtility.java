package com.github.ecsoya.uploader.util;

import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.ecsoya.uploader.core.FileUploadException;
import com.github.ecsoya.uploader.core.UploadConfig;
import com.github.ecsoya.uploader.core.UploadData;
import com.github.ecsoya.uploader.uploader.IFileUploader;
import com.github.ecsoya.uploader.uploader.UnsupportedFileUploader;

/**
 * The Class FileUploadUtils.
 */
public class UploaderUtility {

	public static IFileUploader getFileLoader(UploadConfig config) {
		ServiceLoader<IFileUploader> loader = ServiceLoader.load(IFileUploader.class);
		Stream<IFileUploader> stream = StreamSupport.stream(loader.spliterator(), false);
		if (config == null) {
			return stream.findFirst().orElse(UnsupportedFileUploader.INSTANCE);
		}
		return stream.filter(p -> p.isAdapter(config)).findFirst().orElse(UnsupportedFileUploader.INSTANCE);
	}

	public static final String upload(UploadData data, UploadConfig config) throws FileUploadException {
		final IFileUploader uploader = getFileLoader(config);
		return uploader.upload(data, config);
	}

	public static final List<String> upload(List<UploadData> files, UploadConfig config) throws FileUploadException {
		if (files == null || files.isEmpty()) {
			return Collections.emptyList();
		}
		final IFileUploader uploader = getFileLoader(config);
		return uploader.upload(files, config);
	}

}