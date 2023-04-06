package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private OrderRepository orderRepository;
    public ResponseEntity<StandardResponse> getDashBoardInfo(Long id) {

        try {
            //Get number of meals ordered
            int orders = orderRepository.findAll().size();
            Long ords = orderRepository.count();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get dashbord info");
        }
    }

    public ResponseEntity<StandardResponse> getUserDashboardInfo(Long id) {
        try {
            //Get number of meals ordered
            int orders = orderRepository.findAll().size();
            Long ords = orderRepository.count();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get dashbord info");
        }
    }
}
