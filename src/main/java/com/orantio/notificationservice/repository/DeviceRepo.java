package com.orantio.notificationservice.repository;

import com.orantio.notificationservice.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceRepo extends MongoRepository<Device, String> {
    List<Device> findByUserIdIn(List<String> userIds);
}
