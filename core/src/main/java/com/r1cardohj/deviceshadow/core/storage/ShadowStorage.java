package com.r1cardohj.deviceshadow.core.storage;

import java.util.Collection;
import java.util.Map;

import com.r1cardohj.deviceshadow.core.model.IDeviceShadow;

public interface ShadowStorage {
  void saveShadow(IDeviceShadow shadow);

  IDeviceShadow getShadow(String deviceId);

  boolean deleteShadow(String deviceId);

  Map<String, IDeviceShadow> getShadow(Collection<String> deviceIds);

  void saveShadow(Map<String, IDeviceShadow> shadows);
}
