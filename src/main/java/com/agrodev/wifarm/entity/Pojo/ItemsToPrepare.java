package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ItemsToPrepare {
    private String itemName;
    private double units;
    private double itemWeight;
}
