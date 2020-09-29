package com.infrrd.regex.models;

public class RegexMatcherResponse {

  int respCode;
  String matchInfo;
  boolean erroFlag;

  public int getRespCode() {
    return respCode;
  }

  public void setRespCode(int respCode) {
    this.respCode = respCode;
  }
  public String getMatchInfo() {
    return matchInfo;
  }
  public void setMatchInfo(String matchInfo) {
    this.matchInfo = matchInfo;
  }

  public boolean isErroFlag() {
    return erroFlag;
  }

  public void setErroFlag(boolean erroFlag) {
    this.erroFlag = erroFlag;
  }


}
