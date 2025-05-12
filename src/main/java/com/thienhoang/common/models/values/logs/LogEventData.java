package com.thienhoang.common.models.values.logs;

import com.thienhoang.common.models.enums.LogAction;
import com.thienhoang.common.models.values.logs.base.LogData;
import lombok.Data;

@Data
public class LogEventData extends LogData {
  private Long userId;
  private Long mainId;
  private Boolean isSystem = false;
  private LogAction action;
  private String objectName;
  private Object preValue;
  private Object value;
}
