package com.peoplehub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3StorageConfig {

	//@Value("${cloud.aws.credentials.access-key}")
	private String accessKey="AKIA6N6B5OYKWQYPI37N";
	
	//@Value("${cloud.aws.credentials.screte-key}")
	private String accessSecret="deayGrzVOZxph3+ByGUjEgJe26mimItth89iLvEy";
	
	//@Value("${cloud.aws.region.static}")
	private String region="us-east-2";
	
	@Bean
	public AmazonS3 generateS3Client() {
		AWSCredentials credentials=new BasicAWSCredentials(accessKey, accessSecret);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
	}
}
