package com.thienhoang.common.models;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderContext {
  private Long userId;
  private String name;
  private String username;
  private Map<String, Object> headers;
}
