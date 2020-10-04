package com.kacperBogusz.CurrencyExchangeOffice.repository;

import com.kacperBogusz.CurrencyExchangeOffice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
