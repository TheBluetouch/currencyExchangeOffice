package com.kacperBogusz.CurrencyExchangeOffice.controller;

import com.kacperBogusz.CurrencyExchangeOffice.domain.Account;
import com.kacperBogusz.CurrencyExchangeOffice.dto.CreateAccountDto;
import com.kacperBogusz.CurrencyExchangeOffice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private AccountService service;
    private RestTemplate restTemplate;
    @Autowired
    public AccountController(AccountService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestBody @Valid CreateAccountDto dto) {
        Account a = service.create(dto);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = service.findAll();
        return ResponseEntity.ok(accounts);
    }

    //    @GetMapping(value = "/pesel")
//    public ResponseEntity<List<Account>> findAccountByPesel() {
//        List<Account> accounts = service.findAccountByPesel(findAccountByPesel());
//        return ResponseEntity.ok(accounts);
//    }
    @GetMapping("/{pesel}")
    public ResponseEntity<Account> findAccountByPesel(@PathVariable(value = "pesel") String pesel) {
        Account account = service.findAccountByPesel(pesel);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/test")
    public RateResponse getResponse() {
        RateResponse response = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/usd?format=json", RateResponse.class);
        System.out.println(response);
        return response;
    }


    @GetMapping("/test1")
    public BigDecimal currencyExchange(@Valid CurrencyExchangeDto dto) {

        if (dto.getCurrencyFrom().equalsIgnoreCase("PLN")) {
            String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + dto.getCurrencyTo() +
                    "?format=json";
            RateResponse response = restTemplate.getForObject(url, RateResponse.class);
            BigDecimal rate = BigDecimal.valueOf(response.getRates().get(0).getMid())
                    .setScale(2, RoundingMode.HALF_UP);
            return dto.getAmount().divide(rate, RoundingMode.HALF_DOWN);
        } else {
            String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + dto.getCurrencyFrom() +
                    "?format=json";
            RateResponse response = restTemplate.getForObject(url, RateResponse.class);
            BigDecimal rate = BigDecimal.valueOf(response.getRates().get(0).getMid())
                    .setScale(2, RoundingMode.HALF_UP);
            return dto.getAmount().multiply(rate);
        }
    }
//
//    @GetMapping("calculator/{quantity}")
//    public double calculator(@PathVariable(value = "quantity") Double quantity) {
//        RestTemplate restTemplate = new RestTemplate();
//        RateResoponse response = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/usd?format=json", RateResoponse.class);
////        if (response.getRates() != null && response.getRates().size() == 1) {
//          Double  rate = response.getRates().get(0).getMid();
//            System.out.println();
//
////        }
//        Double result = quantity * rate;
//        return result;
//    }
}
