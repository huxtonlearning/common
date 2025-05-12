package com.thienhoang.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class JsonParserUtils {

  public static final ObjectMapper objectMapper = new ObjectMapper();

  public static Map<String, String> toMap(String mapString) {
    try {
      return objectMapper.readValue(mapString, new TypeReference<>() {});
    } catch (Exception e) {
      return new HashMap<>();
    }
  }

  public static <T> T entity(String json, Class<T> tClass) {
    try {

      return objectMapper.readValue(json, tClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
