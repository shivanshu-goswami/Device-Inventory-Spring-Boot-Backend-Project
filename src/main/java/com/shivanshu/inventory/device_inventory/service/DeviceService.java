package com.shivanshu.inventory.device_inventory.service;

import com.shivanshu.inventory.device_inventory.exception.DeviceNotFoundException;
import com.shivanshu.inventory.device_inventory.model.Device;
import com.shivanshu.inventory.device_inventory.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    public Device getDeviceById(Long id) {
        //this is optional
        Optional<Device> device= deviceRepository.findById(id);
        return device.orElseThrow(()->new DeviceNotFoundException("Device not found with id : "+id));
    }

    public Device updateDevice(Long id,Device updatedDevice) {
        //.findbyid returns "device" type but we are storing in optional type in existing device thats why we are type
        //casting it into optional.of() otherwise dont typecast and in next line do existingDevice.orElseThrow as done in above function
       Optional<Device> existingDevice = Optional.of(deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found with id : " + id)));
       //we can directly do .save as neo4j first checks existence then update
        //but in real projects wde want to check if device exist or not.
        //if not then we want to throw an exception

           Device device=existingDevice.get();
           device.setName(updatedDevice.getName());
           device.setIpAddress(updatedDevice.getIpAddress());
           device.setDeviceType(updatedDevice.getDeviceType());
           device.setLocation(updatedDevice.getLocation());
           device.setStatus(updatedDevice.getStatus());
           return updatedDevice;
    }

    public void deleteDevice(Long id) {
       Device device = deviceRepository.findById(id).
               orElseThrow(()->new DeviceNotFoundException("Device not found with id : "+id));

    }

    public List<Device> getDevicesByLocation(String location) {
        List<Device> devices= deviceRepository.findByLocation(location);
        if(devices.isEmpty()){
            throw new DeviceNotFoundException("no device found for location : "+location);
        }
        return devices;
    }
}
