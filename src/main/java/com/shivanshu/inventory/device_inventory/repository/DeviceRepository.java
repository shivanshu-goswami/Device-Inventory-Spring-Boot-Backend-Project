package com.shivanshu.inventory.device_inventory.repository;

import com.shivanshu.inventory.device_inventory.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface DeviceRepository extends Neo4jRepository<Device, Long> {
    //spring automatically creates query based on method name
    List<Device> findByLocation(String location);
    @Query("MATCH (n:NodeLabel {id: $id}) DETACH DELETE n")
    void detachDeleteById(Long id);
    Page<Device> findAll(Pageable pageable);
}
