package com.r1cardohj.deviceshadow.core.storage;

import java.util.Collection;
import java.util.Map;

import com.r1cardohj.deviceshadow.core.model.DeviceShadow;

public interface ShadowStorage {
  void saveShadow(DeviceShadow shadow);

  DeviceShadow getShadow(String deviceId);

  boolean deleteShadow(String deviceId);

  Map<String, DeviceShadow> getShadow(Collection<String> deviceIds);

  void saveShadow(Map<String, DeviceShadow> shadows);
}
