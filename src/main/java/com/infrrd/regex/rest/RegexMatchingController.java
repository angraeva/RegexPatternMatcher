package com.infrrd.regex.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.infrrd.regex.util.InfrrdUtil;


@RestController
@RequestMapping(value = "/match")
public class RegexMatchingController {


  @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"},
      produces = {"application/json"})
  public ResponseEntity<?> matchResponse(@RequestBody String requestBody) {
    try {
      System.out.println("match response");
      if (StringUtils.isBlank(requestBody)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            InfrrdUtil.getResponseBody("Request body is empty", HttpStatus.BAD_REQUEST.value(),
                true));
      }
      JSONObject request = new JSONObject(requestBody);
      System.out.println("request " + requestBody);
      String regex = request.getString("regex");
      String textBody = request.getString("textBody");
      if (StringUtils.isBlank(regex)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                InfrrdUtil.getResponseBody("Regex is empty", HttpStatus.BAD_REQUEST.value(), true));
      }
      if (StringUtils.isBlank(textBody)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(InfrrdUtil.getResponseBody("Text body is empty", HttpStatus.BAD_REQUEST.value(),
                true));
      }
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(textBody);
      String matchText = "";
      while (matcher.find()) {
        matchText = matcher.group();
      }
      if (StringUtils.isBlank(matchText)) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(InfrrdUtil.getResponseBody(matchText, HttpStatus.OK.value(), true));
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(InfrrdUtil.getResponseBody(matchText, HttpStatus.OK.value(), false));
    } catch (JSONException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(InfrrdUtil.getResponseBody("Exception while geting string from JSONObject ",
              HttpStatus.INTERNAL_SERVER_ERROR.value(), true));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(InfrrdUtil.getResponseBody("Exception while performing regex pattern matcher ",
              HttpStatus.INTERNAL_SERVER_ERROR.value(), true));
    }
  }



}
