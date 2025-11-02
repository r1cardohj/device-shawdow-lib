package com.r1cardohj.deviceshadow.core.storage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.r1cardohj.deviceshadow.core.model.DeviceShadow;

public class InMemoryShadowStorage implements ShadowStorage{

  private final ConcurrentHashMap<String, DeviceShadow> storage;

  public InMemoryShadowStorage() {
    this.storage = new ConcurrentHashMap<>();
  }


  @Override
  public void saveShadow(DeviceShadow shadow) {
      Objects.requireNonNull(shadow, "shadow must not be null");
      Objects.requireNonNull(shadow.getDeviceId(), "deviceId must not be null");
      storage.put(shadow.getDeviceId(), shadow);
  }

  @Override
  public DeviceShadow getShadow(String deviceId) {
      Objects.requireNonNull(deviceId, "deviceId must not be null");
      return storage.get(deviceId);
  }

  @Override
  public boolean deleteShadow(String deviceId) {
      Objects.requireNonNull(deviceId, "deviceId must not be null");
      return storage.remove(deviceId) != null;
  }

  @Override
  public Map<String, DeviceShadow> getShadow(Collection<String> deviceIds) {
      Objects.requireNonNull(deviceIds, "deviceIds must not be null");
      Map<String, DeviceShadow> res = new LinkedHashMap<>();
      for (var deviceId : deviceIds) {
          if (deviceId == null)
              continue;
          res.put(deviceId, getShadow(deviceId));
      }

      return Collections.unmodifiableMap(res);
  }

  @Override
  public void saveShadow(Map<String, DeviceShadow> shadows) {
      for (var entry : shadows.entrySet()) {
          var key = entry.getKey();
          var value = entry.getValue();

          if (key == null || value == null)
              continue;

          storage.put(key, value);
      }
  }
}
