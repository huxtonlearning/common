package com.thienhoang.common.config.web.method.argument.impl;

import com.thienhoang.common.config.web.method.argument.IMethodArgument;
import com.thienhoang.common.models.HeaderContext;
import com.thienhoang.common.utils.HeaderKeys;
import com.thienhoang.common.utils.JsonParserUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@Slf4j
public class HeaderContextResolver implements IMethodArgument {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(HeaderContext.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    String user = request.getHeader(HeaderKeys.USER);
    try {
      HeaderContext context = JsonParserUtils.entity(user, HeaderContext.class);

      log.info("Triển khai ánh xạ dữ liệu user vào HeaderContext thành công");
      return context;
    } catch (Exception e) {

      return HeaderContext.builder().name("Hoang").userId(1L).build();
      //      throw new ExceptionResponse(HttpStatus.FORBIDDEN, CommonErrorMessage.FORBIDDEN);
    }
  }
}
