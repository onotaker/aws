package com.example.aws.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendSQSService {

  private AmazonSQS sqs;

  @Value("${aws.sqs.queue-url}")
  private String queueUrl;

  @Value("${aws.sqs.queue-endpoint}")
  private String queueEndpoint;

  @PostConstruct
  public void buildClient() {
    sqs =
        AmazonSQSClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("", "")))
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    queueEndpoint, Regions.AP_NORTHEAST_1.getName()))
            .build();
  }

  public void sendMessage() {
    sqs.sendMessage(
        new SendMessageRequest()
            .withQueueUrl(queueUrl)
            .withMessageBody("Hello World")
            .withDelaySeconds(5));
  }
}
