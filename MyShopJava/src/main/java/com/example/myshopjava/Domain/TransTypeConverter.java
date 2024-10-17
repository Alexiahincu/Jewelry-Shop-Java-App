package com.example.myshopjava.Domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        if (transactionType == null) {
            return null;
        }
        return transactionType.name();
    }

    @Override
    public TransactionType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }
        return TransactionType.valueOf(type);
    }
}
