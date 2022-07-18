package tn.com.well.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {
private String accountSid;
private String authToken;
private String trialNumber;



}

