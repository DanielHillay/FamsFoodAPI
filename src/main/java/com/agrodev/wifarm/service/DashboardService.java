package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Crops;
import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.Pojo.Dashboard;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.FarmRepository;
import com.agrodev.wifarm.repository.MarketCropsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private MarketCropsRepo marketCropsRepo;

    public ResponseEntity<StandardResponse> getUserFarmDetails(String userId) {
        try {
            Dashboard dashboard = new Dashboard();
            Farm farms = farmRepository.findByCustomerId(userId).get();

            double squareMeters = 0;
            double cropTypes = 0;
            double totalPrincipalAmount = 0;
            double accruedAmount = 0;
            for(Crops cr : farms.getCropsList()){
                squareMeters = squareMeters + (cr.getMinSquareMeter() * cr.getAmountPlanted());
                cropTypes = cropTypes + 1;
                totalPrincipalAmount = totalPrincipalAmount + cr.getPrincipalAmount();

                MarketCrops marketCrop = marketCropsRepo.findByCropName(cr.getCropName()).get();
                double accruedPercent = Math.abs((marketCrop.getAccruedAmount()/cr.getPrice())*100)/100;
                double amount = (cr.getAmountPlanted() * cr.getPrice())/accruedPercent;
                accruedAmount = accruedAmount + amount;
            }

            dashboard.setFarm(farms);
            dashboard.setCropsList(farms.getCropsList());
            dashboard.setAccuredAmount(accruedAmount);
            dashboard.setPrincipalAmount(totalPrincipalAmount);
            dashboard.setTotalSquareMeters(squareMeters);

            return StandardResponse.sendHttpResponse(true, "Successful", dashboard);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get user details");
        }
    }
}
