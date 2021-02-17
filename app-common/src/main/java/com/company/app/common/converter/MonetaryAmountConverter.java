package com.company.app.common.converter;

import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {
    
    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
        return attribute.getNumber().numberValue(BigDecimal.class);
    }
    
    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
        return FastMoney.of(dbData, "USD");
    }
}
