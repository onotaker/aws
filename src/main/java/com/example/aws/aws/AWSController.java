package com.example.aws.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AWSController {

  @Autowired
  private SendSQSService sendSQSService;

  @GetMapping("/sendMessage")
  public String sendSQSService() {
    sendSQSService.sendMessage();
    return "sendMessage";
  }
}
