package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.AdminDashBoard;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.OrderRepository;
import com.agrodev.wifarm.repository.StoreFoodItemRepo;
import com.agrodev.wifarm.repository.StoreMealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StoreMealRepo storeMealRepo;
    @Autowired
    private StoreFoodItemRepo storeFoodItemRepo;
    public ResponseEntity<StandardResponse> getDashBoardInfo(Long id) {

        try {
            AdminDashBoard dashBoard = new AdminDashBoard();
            //Get number of meals ordered
            int orders = orderRepository.findAll().size();
            Long ords = orderRepository.count();
            Long meals = (long) storeMealRepo.findAll().size();
            Long foodItems = (long) storeFoodItemRepo.findAll().size();
            //Get number of fulfilled orders
            Long fulfilledOrders = (long) orderRepository.findByIsDelivered(true).size();
            Long pendingOrders = (long) orderRepository.findByIsAttendedTo(false).size();
            if(orders == ords){
                dashBoard.setNumberOfOrders(orders);
            }else{
                dashBoard.setNumberOfOrders(ords);
            }
            dashBoard.setPendingOrders(pendingOrders);
            dashBoard.setFulfilledOrders(fulfilledOrders);
            dashBoard.setStoreMeals(storeMealRepo.findAll());
            dashBoard.setFoodItems(foodItems);
            dashBoard.setNoOfStoreMeals(meals);
            return StandardResponse.sendHttpResponse(true, "Successful", dashBoard);
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
