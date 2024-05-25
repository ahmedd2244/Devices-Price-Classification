package com.example.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map; // Import java.util.Map

import org.springframework.web.client.RestTemplate; // Import RestTemplate

@RestController
@RequestMapping("/api")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @PostMapping("/devices")
    public Device createDevice(@RequestBody Device device) {
        return deviceRepository.save(device);
    }

    @GetMapping("/devices")
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found for this id :: " + id));
        return ResponseEntity.ok().body(device);
    }
    @PostMapping("/predict/{deviceId}")
    public ResponseEntity<Device> predictPrice(@PathVariable Long deviceId) {
          Device device = deviceRepository.findById(deviceId)
                  .orElseThrow(() -> new ResourceNotFoundException("Device not found for this id :: " + deviceId));
  
          // Prepare the data to send to the Flask API
          Map<String, Object> data = new HashMap<>();
          data.put("battery_power", device.getBatteryPower());
          data.put("blue", device.getBlue());
          data.put("clock_speed", device.getClockSpeed());
          data.put("dual_sim", device.getDualSim());
          data.put("fc", device.getFc());
          data.put("four_g", device.getFourG());
          data.put("int_memory", device.getIntMemory());
          data.put("m_dep", device.getMDep());
          data.put("mobile_wt", device.getMobileWt());
          data.put("n_cores", device.getNCores());
          data.put("pc", device.getPc());
          data.put("px_height", device.getPxHeight());
          data.put("px_width", device.getPxWidth());
          data.put("ram", device.getRam());
          data.put("sc_h", device.getScH());
          data.put("sc_w", device.getScW());
          data.put("talk_time", device.getTalkTime());
          data.put("three_g", device.getThreeG());
          data.put("touch_screen", device.getTouchScreen());
          data.put("wifi", device.getWifi());
  
          // Call the Flask API to predict the price
          RestTemplate restTemplate = new RestTemplate();
          String url = "http://localhost:5000/predict";
          ResponseEntity<Map> response = restTemplate.postForEntity(url, data, Map.class);
          Map<String, Integer> result = response.getBody();
  
          // Update the device with the predicted price range
          device.setPriceRange(result.get("price_range"));
          deviceRepository.save(device);
  
          return ResponseEntity.ok().body(device);
    
    }
}
