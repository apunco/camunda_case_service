package com.apunco.case_service;

import com.apunco.case_service.exception.InvalidCaseTypeException;
import com.apunco.case_service.service.CalculateRiskService;
import com.apunco.openapi.model.CalculateRiskResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CalculateRiskServiceTest {
    private static final String NOT_SO_IMPORTANT_CASE = "NOT_SO_IMPORTANT_CASE";
    private static final String IMPORTANT_CASE = "IMPORTANT_CASE";
    private static final String VERY_IMPORTANT_CASE = "VERY_IMPORTANT_CASE";
    private static final String INVALID_CASE = "INVALID_CASE";

    @Autowired
    CalculateRiskService calculateRiskService;

    @Test
    void testWithNull() {
        String caseType = null;

        InvalidCaseTypeException exception = assertThrows(InvalidCaseTypeException.class, () -> {
            calculateRiskService.calculateRisk(caseType);
        });
        assertEquals("Case type cannot be null", exception.getMessage());
    }

    @Test
    void testIncorrectMapping() {
        String caseType = INVALID_CASE;

        InvalidCaseTypeException exception = assertThrows(InvalidCaseTypeException.class, () -> {
            calculateRiskService.calculateRisk(caseType);
        });

        assertEquals("Invalid case type provided: " + caseType, exception.getMessage());
    }
    @Test
    void testCorrectMapping() {
        String caseType;
        CalculateRiskResponse calculateRiskResponse = new CalculateRiskResponse();

        caseType = NOT_SO_IMPORTANT_CASE;
        calculateRiskResponse.setRiskScore(CalculateRiskResponse.RiskScoreEnum.LOW);
        assertEquals(calculateRiskService.calculateRisk(caseType), calculateRiskResponse);

        caseType = IMPORTANT_CASE;
        calculateRiskResponse.setRiskScore(CalculateRiskResponse.RiskScoreEnum.MEDIUM);
        assertEquals(calculateRiskService.calculateRisk(caseType), calculateRiskResponse);

        caseType = VERY_IMPORTANT_CASE;
        calculateRiskResponse.setRiskScore(CalculateRiskResponse.RiskScoreEnum.HIGH);
        assertEquals(calculateRiskService.calculateRisk(caseType), calculateRiskResponse);
    }
}
