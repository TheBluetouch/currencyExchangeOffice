package com.kacperBogusz.CurrencyExchangeOffice.domain;


import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String pesel;

    private String firstName;

    private String lastName;

    private Long accountNumber;

    private Long quantity;

    private LocalDateTime creationTime;
}

