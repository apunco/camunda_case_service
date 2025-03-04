package com.apunco.case_service.controller;

import com.apunco.case_service.service.CalculateRiskService;
import com.apunco.openapi.model.CalculateRiskResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/risk")
@AllArgsConstructor
public class RiskController {
    private final CalculateRiskService calculateRiskService;

    @GetMapping("/calculate")
    public CalculateRiskResponse calculateRisk(@RequestParam String caseType){
        return calculateRiskService.calculateRisk(caseType);
    }
}
