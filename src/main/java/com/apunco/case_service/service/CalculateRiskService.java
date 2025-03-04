package com.apunco.case_service.service;

import com.apunco.case_service.exception.InvalidCaseTypeException;
import com.apunco.openapi.model.CalculateRiskResponse;
import com.apunco.openapi.model.CaseType;
import org.springframework.stereotype.Service;

@Service
public class CalculateRiskService {

    public CalculateRiskResponse calculateRisk(String caseType){
        if (caseType == null) {
            throw new InvalidCaseTypeException("400", "Case type cannot be null");
        }

        CalculateRiskResponse.RiskScoreEnum calculatedRisk;

        try {
            var type = CaseType.valueOf(caseType);
            calculatedRisk = mapToRiskScore(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidCaseTypeException("400", "Invalid case type provided: " + caseType);
        }

        return new CalculateRiskResponse()
                .riskScore(calculatedRisk);
    }

    private CalculateRiskResponse.RiskScoreEnum mapToRiskScore(CaseType type) {
        return switch (type) {
            case NOT_SO_IMPORTANT_CASE -> CalculateRiskResponse.RiskScoreEnum.LOW;
            case IMPORTANT_CASE -> CalculateRiskResponse.RiskScoreEnum.MEDIUM;
            case VERY_IMPORTANT_CASE -> CalculateRiskResponse.RiskScoreEnum.HIGH;
        };
    }
}
