package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.StatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class GeneralServices {

    @Autowired
    private StatesRepo statesRepo;

    public ResponseEntity<StandardResponse> getCountries() {
        try {
            List<String> countries = List.of(Locale.getISOCountries());
            List<String> countryNames = new ArrayList<>();
            for(String ss : countries){
                Locale locale = new Locale("en", ss);
                countryNames.add(locale.getDisplayCountry());
            }
            return StandardResponse.sendHttpResponse(true, "Successful", countryNames);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not retrieve countries");
        }
    }

    public ResponseEntity<StandardResponse> getStatesOfCountry(String country){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", statesRepo.findByCountryName(country));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get states");
        }
    }
}
