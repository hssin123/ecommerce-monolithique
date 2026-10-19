package com.securityModel.service.imple;




import com.securityModel.models.Driver;
import com.securityModel.payload.request.DriverRequest;
import com.securityModel.payload.response.DriverResponse;
import com.securityModel.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImple implements DriverService {



    private final DriverRepository driverRepository;

    public DriverServiceImple(DriverRepository driverRepositoy) {

        this.driverRepository = driverRepositoy;
    }

    @Override
    public DriverResponse createdriver(DriverRequest driver) {
        Driver c =DriverResponse.toEntity(driver);
        Driver saveddriver= driverRepository.save(c);
        return DriverResponse.fromEntity(saveddriver);
    }

    @Override
    public List<DriverResponse> allDriver() {
        return driverRepository.findAll().stream()
                .map(DriverResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public DriverResponse driverById(Long id) {

        return driverRepository.findById(id)
                .map(DriverResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Driver not found with this id:" +id));

    }

    @Override
    public DriverResponse updatedriver(DriverRequest driverRequest, Long id) {

        Driver driver= driverRepository.findById(id).orElseThrow(()->
                new RuntimeException("Driver not found with this id:" +id));
        if (driver != null) {
            Driver c = DriverResponse.toEntity(driverRequest);
            c.setId(id);
            c.setAdress(c.getAdress() == null ? driver.getAdress() : c.getAdress());
            Driver saveddriver = driverRepository.save(c);
            return DriverResponse.fromEntity(saveddriver);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deletedriver(Long id) {

        HashMap message =new HashMap<>();
        Driver c = driverRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                driverRepository.deleteById(id);
                message.put("message", "Driver deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Driver not found: " +id);
        }
        return message;

    }
}
