package com.moigosa.moigosa.adapter.in.web.common.exception.status_code;

import org.springframework.http.HttpStatus;

public interface StatusCode {
  String getCode();
  String getMessage();
  HttpStatus getHttpStatus();
}