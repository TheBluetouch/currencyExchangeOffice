package com.kacperBogusz.CurrencyExchangeOffice.dto;

import lombok.Data;
import lombok.NonNull;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
public class CreateAccountDto {

    @PESEL
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pesel;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
