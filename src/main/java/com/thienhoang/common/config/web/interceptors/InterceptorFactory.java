package com.thienhoang.common.config.web.interceptors;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class InterceptorFactory {

  public final List<IInterceptor> methodArgumentList;
}
