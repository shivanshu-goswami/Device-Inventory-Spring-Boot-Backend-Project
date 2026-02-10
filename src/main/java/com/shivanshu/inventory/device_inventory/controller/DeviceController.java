package com.shivanshu.inventory.device_inventory.controller;

import com.shivanshu.inventory.device_inventory.model.Device;
import com.shivanshu.inventory.device_inventory.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    //@Autowired no need since there is a single constructor
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    //Create Device (Create)
    @PostMapping
    public Device addDevice(@Valid @RequestBody Device device) {
       return deviceService.saveDevice(device);
    }

    //Get all device (Read)
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    //Get device by id(read)
    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    //update current device
    @PutMapping("{id}")
    public Device updateDevice(@PathVariable Long id,@Valid @RequestBody Device updateddevice) {
        return deviceService.updateDevice(id,updateddevice);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
         deviceService.deleteDevice(id);
    }

    @GetMapping("/location/{location}")
    public List<Device> getDeviceByLocation(@PathVariable String location) {
        return deviceService.getDevicesByLocation(location);
    }

}
