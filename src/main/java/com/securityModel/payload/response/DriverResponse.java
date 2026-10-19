package com.securityModel.payload.response;




import com.securityModel.models.Driver;
import com.securityModel.payload.request.DriverRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DriverResponse {

    private Long id;
    private String adress;
    private List<OrderResponse> orders;

    public static DriverResponse fromEntity(Driver driver) {
        DriverResponse subDriverResponse = new DriverResponse();
        BeanUtils.copyProperties(driver, subDriverResponse);
        if (driver.getOrderList() != null) {
            //Conversion des sous-catégories
            subDriverResponse.setOrders(driver.getOrderList().stream()
                    .map(OrderResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return subDriverResponse;
    }

    public static Driver toEntity(DriverRequest driverRequest) {
        Driver s = new Driver();
        BeanUtils.copyProperties(driverRequest, s);
        return s;
    }
}
