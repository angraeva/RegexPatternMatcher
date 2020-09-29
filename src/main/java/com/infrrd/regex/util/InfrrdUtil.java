package com.infrrd.regex.util;

import com.infrrd.regex.models.RegexMatcherResponse;

public class InfrrdUtil {

  public static RegexMatcherResponse getResponseBody(String message, int code, boolean errorFlag) {
    RegexMatcherResponse resp = new RegexMatcherResponse();
    resp.setRespCode(code);
    resp.setMatchInfo(message);
    resp.setErroFlag(errorFlag);
    return resp;
  }
}
