package com.thienhoang.common.config.web.interceptors.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thienhoang.common.config.web.interceptors.IInterceptor;
import com.thienhoang.common.services.ElkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class LogInterceptor implements IInterceptor {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final ElkService elkService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    return true; // Proceed with the execution chain  }
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    //    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    long startTime = (long) request.getAttribute("startTime");
    long endTime = System.currentTimeMillis();
    long timeTaken = endTime - startTime;
    elkService.whiteLogRequest(request, response, timeTaken);
  }
}
