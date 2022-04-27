package com.github.ecsoya.uploader.core;

import javax.annotation.Generated;

import com.github.ecsoya.uploader.util.Utility;

/**
 * The Class UploadConfig.
 */
public class UploadConfig {

	private String type;

	/** The base url. */
	private String baseUrl;

	/** The endpoint. */
	private String endpoint;

	/** The access key. */
	private String accessKey;

	/** The secret key. */
	private String secretKey;

	/** The bucket. */
	private String bucket;

	/** The add date path. */
	private boolean addDatePath = true;

	/**
	 * For local
	 */
	private String uploadPath;

	public UploadConfig() {
	}

	private UploadConfig(Builder builder) {
		this.type = builder.type;
		this.baseUrl = builder.baseUrl;
		this.endpoint = builder.endpoint;
		this.accessKey = builder.accessKey;
		this.secretKey = builder.secretKey;
		this.bucket = builder.bucket;
		this.addDatePath = builder.addDatePath;
		this.uploadPath = builder.uploadPath;
	}

	/**
	 * Gets the 对象存储，文件上传之后的域名前缀.
	 *
	 * @return the 对象存储，文件上传之后的域名前缀
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Sets the base url.
	 *
	 * @param baseUrl the base url
	 * @return the upload config
	 */
	public UploadConfig setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
		return this;
	}

	/**
	 * Gets the 对象存储，地域.
	 *
	 * @return the 对象存储，地域
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Sets the endpoint.
	 *
	 * @param endpoint the endpoint
	 * @return the upload config
	 */
	public UploadConfig setEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	/**
	 * Gets the 对象存储，AccessKey.
	 *
	 * @return the 对象存储，AccessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * Sets the access key.
	 *
	 * @param accessKey the access key
	 * @return the upload config
	 */
	public UploadConfig setAccessKey(String accessKey) {
		this.accessKey = accessKey;
		return this;
	}

	/**
	 * Gets the 对象存储，SecretKey.
	 *
	 * @return the 对象存储，SecretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the secret key
	 * @return the upload config
	 */
	public UploadConfig setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		return this;
	}

	/**
	 * Gets the 对象存储，存储桶.
	 *
	 * @return the 对象存储，存储桶
	 */
	public String getBucket() {
		return bucket;
	}

	/**
	 * Sets the bucket.
	 *
	 * @param bucket the bucket
	 * @return the upload config
	 */
	public UploadConfig setBucket(String bucket) {
		this.bucket = bucket;
		return this;
	}

	/**
	 * Checks if is 添加时间路径.
	 *
	 * @return the 添加时间路径
	 */
	public boolean isAddDatePath() {
		return addDatePath;
	}

	/**
	 * Sets the add date path.
	 *
	 * @param addDatePath the add date path
	 * @return the upload config
	 */
	public UploadConfig setAddDatePath(boolean addDatePath) {
		this.addDatePath = addDatePath;
		return this;
	}

	/**
	 * Test cloud validated.
	 *
	 * @throws FileUploadException the file upload exception
	 */
	public void testCloudValidated() throws FileUploadException {
		if (Utility.isEmpty(baseUrl)) {
			throw new FileUploadException("Base URL is empty");
		}
		if (Utility.isEmpty(bucket)) {
			throw new FileUploadException("Bucket is empty");
		}
		if (Utility.isEmpty(accessKey)) {
			throw new FileUploadException("AccessKey is empty");
		}
		if (Utility.isEmpty(secretKey)) {
			throw new FileUploadException("SecretKey is empty");
		}
		if (Utility.isEmpty(endpoint)) {
			throw new FileUploadException("Endpoint or Region is empty");
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[baseUrl=" + baseUrl + ", endpoint=" + endpoint + ", accessKey=" + accessKey + ", bucket=" + bucket
				+ ", addDatePath=" + addDatePath + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String type;
		private String baseUrl;
		private String endpoint;
		private String accessKey;
		private String secretKey;
		private String bucket;
		private boolean addDatePath = true;
		private String uploadPath;

		private Builder() {
		}

		public Builder setType(String type) {
			this.type = type;
			return this;
		}

		public Builder setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}

		public Builder setEndpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}

		public Builder setAccessKey(String accessKey) {
			this.accessKey = accessKey;
			return this;
		}

		public Builder setSecretKey(String secretKey) {
			this.secretKey = secretKey;
			return this;
		}

		public Builder setBucket(String bucket) {
			this.bucket = bucket;
			return this;
		}

		public Builder setAddDatePath(boolean addDatePath) {
			this.addDatePath = addDatePath;
			return this;
		}

		public Builder setUploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
			return this;
		}

		public UploadConfig build() {
			return new UploadConfig(this);
		}
	}

}
