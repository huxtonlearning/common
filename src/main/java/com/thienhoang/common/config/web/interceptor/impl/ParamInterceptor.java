package com.thienhoang.common.config.web.interceptor.impl;

import com.thienhoang.common.config.web.interceptor.IInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ParamInterceptor implements IInterceptor {

  @Override
  public boolean onIntercept(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    request.getMethod();

    return true;
  }
}
