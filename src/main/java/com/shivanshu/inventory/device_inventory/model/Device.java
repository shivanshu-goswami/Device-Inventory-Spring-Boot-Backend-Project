package com.shivanshu.inventory.device_inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

//this 'device' is the label which we have mentioned
@Node("Device")
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Device name is required")
    private String name;
    @NotBlank(message="IP Address is required")
    private String ipAddress;
    @NotBlank(message="Device Type is Required")
    private String deviceType;
    private String location;
    private String status;

    @Relationship(type="CONNECTED_TO")
    @JsonIgnoreProperties("connectedDevices")
    private List<Device> connectedDevices;

    public Device(Long id, String name, String ipAddress, String deviceType, String location, String status) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.deviceType = deviceType;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Device> getConnectedDevices() {
        return connectedDevices;
    }

    public void setConnectedDevices(List<Device> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }
}
