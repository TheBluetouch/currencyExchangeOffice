package com.kacperBogusz.CurrencyExchangeOffice.controller;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class CurrencyExchangeDto {

 //   @NonNull
    private String currencyFrom;

//    @NonNull
    private String currencyTo;

//    @NonNull
//    @Digits(integer = 10, fraction = 2)
//    @DecimalMin( value = "0.00")
    private BigDecimal amount;
}
