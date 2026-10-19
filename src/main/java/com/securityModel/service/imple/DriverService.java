package com.securityModel.service.imple;





import com.securityModel.payload.request.DriverRequest;
import com.securityModel.payload.response.DriverResponse;

import java.util.HashMap;
import java.util.List;


public interface DriverService {



    DriverResponse createdriver(DriverRequest driver);
    List<DriverResponse> allDriver();
    DriverResponse driverById(Long id);
    DriverResponse updatedriver(DriverRequest driverRequest, Long id);
    HashMap<String, String> deletedriver(Long id);


}
