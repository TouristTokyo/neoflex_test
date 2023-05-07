package com.example.neoflex.controllers;

import com.example.neoflex.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Integer> calculate(@RequestParam("avg_salary") String averageSalaryInput,
                                           @RequestParam("days") String countDaysInput) {
        int averageSalary;
        int countDays;

        try {
            averageSalary = Integer.parseInt(averageSalaryInput);
            countDays = Integer.parseInt(countDaysInput);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (averageSalary < 0 || countDays < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(calculateService.getHolidayPay(averageSalary, countDays), HttpStatus.OK);
    }
}
