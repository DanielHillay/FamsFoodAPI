package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountType;
    private String userId;
    private String scheducleId;
    private double kiloGrams;
    private String subscriptionType;
    private String subName;
    private Date subDate;
    private LocalDateTime subTime;
    private double accountBalance;
}
