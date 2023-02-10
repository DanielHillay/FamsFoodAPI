package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Land;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandService {
    @Autowired
    private LandRepository landRepository;

    public ResponseEntity<StandardResponse> registerNewLand(Land land) {
        try {
            List<Land> landList = landRepository.findByLandState(land.getLandState());
            for(Land l : landList){
                if(l.getLGA().equalsIgnoreCase(land.getLGA())){
                    l.setLandUnits(l.getLandUnits() + land.getLandUnits());
                    landRepository.save(l);
                }
            }
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not register new lands");
        }
    }
}
