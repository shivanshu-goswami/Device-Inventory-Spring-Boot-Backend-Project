package com.shivanshu.inventory.device_inventory.repository;

import com.shivanshu.inventory.device_inventory.model.Device;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface DeviceRepository extends Neo4jRepository<Device, Long> {
    //spring automatically creates query based on method name
    List<Device> findByLocation(String location);
}
