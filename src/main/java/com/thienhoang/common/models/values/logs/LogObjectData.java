package com.thienhoang.common.models.values.logs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thienhoang.common.models.enums.LogAction;
import com.thienhoang.common.models.enums.LogStatus;
import com.thienhoang.common.models.values.logs.base.LogData;
import lombok.Data;

@Data
public class LogObjectData extends LogData {
  @JsonProperty("user_id")
  private Long userId;

  private Integer version;

  @JsonProperty("object_name")
  private String objectName;

  @JsonProperty("object_id")
  private Long objectId;

  private LogAction action;
  private Object data;
  private LogStatus status;
}
