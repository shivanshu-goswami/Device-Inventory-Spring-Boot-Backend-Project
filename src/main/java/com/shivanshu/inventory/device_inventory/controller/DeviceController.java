package com.shivanshu.inventory.device_inventory.controller;

import com.shivanshu.inventory.device_inventory.model.Device;
import com.shivanshu.inventory.device_inventory.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @PostMapping("{id1}/connect/{id2}")
    public Device connectDevices(@PathVariable Long id1, @PathVariable Long id2) {
        return deviceService.connectDevices(id1, id2);
    }
    @DeleteMapping("{id1}/disconnect/{id2}")
    public Device disconnectDevices(@PathVariable Long id1, @PathVariable Long id2) {
        return deviceService.disconnectDevices(id1,id2);
    }

    //I can directly combine it with getAllDevice method instead of creating new endpoint
    //but for testing purpose I created a new endpoint and new method in service layer
    // where whenever asked only then data is being fetched in page wise manner
    @GetMapping("/paged")
    //with pageabledefault annotation, i can set the default no of device on one page
    public Page<Device> getDevices(@PageableDefault(size=2) Pageable pageable) {
        return deviceService.getDevices(pageable);
    }

}
