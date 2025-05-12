package com.thienhoang.common.models.values.logs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thienhoang.common.models.values.logs.base.LogData;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogExceptionData extends LogData {
  private Integer status;
  private LocalDateTime timestamp;
  private String message;

  @JsonProperty("message_code")
  private String messageCode;

  private String description;
  private String path;
  private Object params;
  private Object body;
  private Object headers;
}
