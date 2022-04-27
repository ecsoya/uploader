package com.github.ecsoya.uploader.core;

import java.io.InputStream;

import javax.annotation.Generated;

/**
 * The Class UploadData.
 */
public class UploadData {

	/** The extension. */
	private String extension;

	/** The input stream. */
	private InputStream inputStream;

	/** The datas. */
	private byte[] datas;

	/** The content type. */
	private String contentType;

	/** The length. */
	private Long length;

	/** The file name. */
	private String fileName;

	public UploadData() {
	}

	private UploadData(Builder builder) {
		this.extension = builder.extension;
		this.inputStream = builder.inputStream;
		this.datas = builder.datas;
		this.contentType = builder.contentType;
		this.length = builder.length;
		this.fileName = builder.fileName;
	}

	/**
	 * Builds the.
	 *
	 * @param fileName    the file name
	 * @param inputStream the input stream
	 * @return the upload data
	 */
	public static UploadData build(String fileName, InputStream inputStream) {
		final UploadData uploadData = new UploadData();
		uploadData.setFileName(fileName);
		uploadData.setInputStream(inputStream);
		return uploadData;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets the 输入流.
	 *
	 * @return the 输入流
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Sets the 输入流.
	 *
	 * @param inputStream the new 输入流
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * Gets the 数据.
	 *
	 * @return the 数据
	 */
	public byte[] getDatas() {
		return datas;
	}

	/**
	 * Sets the 数据.
	 *
	 * @param datas the new 数据
	 */
	public void setDatas(byte[] datas) {
		this.datas = datas;
	}

	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public Long getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(Long length) {
		this.length = length;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String extension;
		private InputStream inputStream;
		private byte[] datas;
		private String contentType;
		private Long length;
		private String fileName;

		private Builder() {
		}

		public Builder setExtension(String extension) {
			this.extension = extension;
			return this;
		}

		public Builder setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
			return this;
		}

		public Builder setDatas(byte[] datas) {
			this.datas = datas;
			return this;
		}

		public Builder setContentType(String contentType) {
			this.contentType = contentType;
			return this;
		}

		public Builder setLength(Long length) {
			this.length = length;
			return this;
		}

		public Builder setFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public UploadData build() {
			return new UploadData(this);
		}
	}

}
