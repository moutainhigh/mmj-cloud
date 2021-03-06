package com.mmj.common.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {
  
  private String clientId;
  
  private String clientSecret;
  
  private int accessTokenValiditySeconds = 7200;

}
