package com.zhicheng.sales.service;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxServiceTest {

    @Autowired
    TaxService taxService;


    @Test
    public void shouldCaluculateTax() {
        BigDecimal prise = BigDecimal.valueOf(12.28);
        int quantity = 1;
        BigDecimal taxRate = BigDecimal.valueOf(0.01);

        BigDecimal priseAfterTax = taxService.calculateTax(prise, quantity, taxRate);

        assertThat(priseAfterTax).isEqualTo(0.10);
    }

//    @Test
//    public void shouldGetRoundTotalTax() {
//        double tax1 = 0.986;
//        double tax2 = 1.7654689;
//        double tax3 = 3.09897686;
//        double tax4 = 5.798768685667434345;
//
//        double totalTax = taxService.getAllBasketItemsTax(Lists.newArrayList(tax1, tax2, tax3, tax4));
//
//        assertThat(totalTax).isEqualTo(12.85);
//    }
}
