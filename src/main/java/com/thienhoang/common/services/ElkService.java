package com.thienhoang.common.services;

import com.thienhoang.common.models.enums.LogAction;
import com.thienhoang.common.models.exceptions.ErrorResponse;
import com.thienhoang.common.models.values.logs.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public interface ElkService {
  void whiteLogObject(
      Long userId, String className, Long objectId, LogAction logAction, Object data);

  void whiteLogApi(
      Long userId,
      String url,
      HttpMethod method,
      Object auth,
      HttpStatus status,
      Object body,
      Object response);

  void whiteLogException(ErrorResponse exceptionResponse, HttpServletRequest request);

  void whiteLogRequest(
      Long userId, String url, HttpMethod method, Object body, HttpStatus status, Object response);

  void whiteLogRequest(HttpServletRequest request, HttpServletResponse response, Long timeTaken)
      throws IOException;

  void whiteLogEventSystem(
      Long userId,
      Long enterpriseId,
      Class<?> objectName,
      LogAction action,
      Object preValue,
      Object value,
      Boolean isSystem);

  void whiteLogEvent(
      Long userId,
      Long enterpriseId,
      Class<?> objectName,
      LogAction action,
      Object preValue,
      Object value);
}
