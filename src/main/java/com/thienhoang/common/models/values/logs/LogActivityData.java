package com.thienhoang.common.models.values.logs;

import com.thienhoang.common.models.enums.LogAction;
import com.thienhoang.common.models.values.logs.base.LogData;
import java.util.Map;
import lombok.Data;

@Data
public class LogActivityData extends LogData {
  private Long userId;
  private Long mainId;
  private Boolean isSystem = false;
  private LogAction action;
  private Object relatedObject;
  //  private String ipAddress;
  //  private String device;
  //  private String location;
  private Object performedBy;
  private Map<String, Object> extraFields;
}
