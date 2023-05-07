package com.example.neoflex.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public int getHolidayPay(int averageSalary, int countDays) {
        return averageSalary * countDays;
    }
}
