package com.shivanshu.inventory.device_inventory.service;

import com.shivanshu.inventory.device_inventory.exception.DeviceNotFoundException;
import com.shivanshu.inventory.device_inventory.model.Device;
import com.shivanshu.inventory.device_inventory.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeviceServiceTest {
    @Test
    void testSaveDevice() {
        //creating a fake repository so that I can test my application with data
        //which don't get saved in my original database
        DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);

        //injected my fake repository into service
        DeviceService deviceService = new DeviceService(deviceRepository);

        //new device
        Device device = new Device();
        device.setName("Router-01");

        //setting my mockito behaviour
        Mockito.when(deviceRepository.save(device)).thenReturn(device);

        //now make a call to service method
        Device savedDevice = deviceService.saveDevice(device);

        //verify result with assetEqual inbuilt function
        assertEquals("Router-01", savedDevice.getName());

    }

    @Test
    void testGetDeviceById_Found() {
        DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceRepository);
        Device device = new Device();
        device.setName("Router-01");
        device.setId(1L);
        //basically we say to mockito whenever someone run deviceRepository.findById() then
        //return new device that we created just above
        Mockito.when(deviceRepository.findById(device.getId())).thenReturn(Optional.of(device));
        Device result = deviceService.getDeviceById(1L);
        assertEquals("Router-01", result.getName());
    }

    @Test
    void testGetDeviceById_NotFound() {
        DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceRepository);
        //we didn't create any new device , we are checking that exception is thrown properly
        //or not, that's why we tell mockito if someone type findById then return empty
        //and in next line, if empty is return then Device not found exception must be
        //thrown is checked
        Mockito.when(deviceRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceById(1L);
        });
    }

    @Test
    void testDeleteDevice() {
        DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceRepository);
        Device device = new Device();
        device.setId(1L);
        Mockito.when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        deviceService.deleteDevice(1L);
        Mockito.verify(deviceRepository).detachDeleteById(1L);
    }

    @Test
    void testUpdateDevice() {
        DeviceRepository deviceRepository = Mockito.mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceRepository);
        //existing device in our DB
        Device existingDevice = new Device();
        existingDevice.setId(1L);
        existingDevice.setName("Old Router");

        //new Updated device
        Device updatedDevice = new Device();
        updatedDevice.setName("New Router");

        Mockito.when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        Device result = deviceService.updateDevice(1L, updatedDevice);
        assertEquals("New Router", result.getName());

    }
}
