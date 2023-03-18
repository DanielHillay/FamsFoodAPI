package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.CropRepository;
import com.agrodev.wifarm.repository.MarketCropsRepo;
import com.agrodev.wifarm.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MarketService {
    @Autowired
    private MarketCropsRepo marketCropsRepo;

    public ResponseEntity<StandardResponse> updateMarketCrop(MarketCrops marketCrops) {
        try {
            updateAccruedAmount();
            return StandardResponse.sendHttpResponse(true, "Successful" , marketCropsRepo.save(marketCrops));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update market crop");
        }
    }

    public void updateAccruedAmount(){
        List<MarketCrops> cropsList = marketCropsRepo.findAll();
        List<MarketCrops> plantedCrops = new ArrayList<>();

        for(MarketCrops cr : cropsList){
            if(cr.isPlanted()){
                plantedCrops.add(cr);
            }
        }
        for(MarketCrops cr : plantedCrops){
            double hold = cr.getDailyInterestRate() * cr.getCropPrice();
            cr.setAccruedAmount(cr.getAccruedAmount() + hold);
            cr.setCropPrice(cr.getCropPrice() + cr.getAccruedAmount());
        }
    }

    public ResponseEntity<StandardResponse> getAllMarketCrops() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", marketCropsRepo.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all crops in the market");
        }
    }

    public ResponseEntity<StandardResponse> uploadPhoto(MultipartFile multipartFile, Long id) {
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            MarketCrops marketCrops = marketCropsRepo.findById(id).get();
            marketCrops.setPhoto(fileName);

            String uploadDir = "marketcrop-photos/" + marketCropsRepo.save(marketCrops).getCropId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return StandardResponse.sendHttpResponse(true, "Successful");
        }
        catch (Exception e) {
        return StandardResponse.sendHttpResponse(false, "Could not upload photo");
    }
    }

    public ResponseEntity<StandardResponse> createMarketCrop(MarketCrops marketCrops) {
        try {
            Random customRand = new Random();
            marketCrops.setCropId(String.format("%07d", customRand.nextInt(10000000)));
            return StandardResponse.sendHttpResponse(true, "Successful", marketCropsRepo.save(marketCrops));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create market crop");
        }
    }
}
