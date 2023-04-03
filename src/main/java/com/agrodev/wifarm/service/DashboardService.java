package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public ResponseEntity<StandardResponse> getDashBoardInfo(Long id) {

        try {
            //Get number of meals ordered

            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get dashbord info");
        }
    }
}
