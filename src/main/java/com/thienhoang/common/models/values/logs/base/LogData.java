package com.thienhoang.common.models.values.logs.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thienhoang.common.models.enums.LogType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LogData {
  protected LogType type;

  @JsonProperty("created_at")
  protected LocalDateTime createdAt;
}
