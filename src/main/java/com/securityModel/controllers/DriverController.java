package com.securityModel.controllers;




import com.securityModel.payload.request.DriverRequest;
import com.securityModel.payload.response.DriverResponse;
import com.securityModel.service.imple.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/save")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest) {
        return driverService.createdriver(driverRequest);
    }

    @GetMapping("/all")
    public List<DriverResponse> AllDriver() {
        return driverService.allDriver();
    }

    @GetMapping("/getone/{id}")
    public DriverResponse driverbyid(@PathVariable Long id) {
        return driverService.driverById(id);
    }

    @PutMapping("/update/{id}")
    public DriverResponse updatedriver(@RequestBody DriverRequest driverRequest, @PathVariable Long id) {
        return driverService.updatedriver(driverRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deletedriver(@PathVariable Long id) {
        return driverService.deletedriver(id);
    }
}
