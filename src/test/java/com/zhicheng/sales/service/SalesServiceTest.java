package com.zhicheng.sales.service;

import com.google.common.collect.Lists;
import com.zhicheng.sales.model.Basket;
import com.zhicheng.sales.model.BasketItem;
import com.zhicheng.sales.model.Product;
import com.zhicheng.sales.model.Receipt;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesServiceTest {

    @Autowired
    SalesService salesService;

    @Autowired
    ProductService productService;

    // purchase 1
    @Test
    public void getRecipt() throws Exception {

        List<Product> products = productService.getProducts();

        Product dictionary = products.get(1);
        Product choclate = products.get(2);
        Product musicCd = products.get(3);

        Basket basket = new Basket(Lists.newArrayList(
                new BasketItem(dictionary, 1),
                new BasketItem(choclate, 1),
                new BasketItem(musicCd, 1)));

        Receipt receipt = salesService.getRecipt(basket);

        Assertions.assertThat(receipt.getTotalPrise()).isEqualTo(BigDecimal.valueOf(29.83));
    }


    // purchase 3
    @Test
    public void getReciptPurchase3() throws Exception {

        List<Product> products = productService.getProducts();

        Product parfumImported = products.get(4);
        Product parfumLoccal = products.get(5);
        Product pills = products.get(6);
        Product importedBoxOfChocolates = products.get(7);


        Basket basket = new Basket(Lists.newArrayList(
                new BasketItem(parfumImported, 1),
                new BasketItem(parfumLoccal, 1),
                new BasketItem(pills, 1),
                new BasketItem(importedBoxOfChocolates, 1)));

        Receipt receipt = salesService.getRecipt(basket);

        Assertions.assertThat(receipt.getTotalPrise()).isEqualTo(BigDecimal.valueOf(74.68));
    }


    // print receipt
    @Test
    public void printRecepit(){

        List<Product> products = productService.getProducts();

        Product parfumImported = products.get(4);
        Product parfumLoccal = products.get(5);
        Product pills = products.get(6);
        Product importedBoxOfChocolates = products.get(7);


        Basket basket = new Basket(Lists.newArrayList(
                new BasketItem(parfumImported, 1),
                new BasketItem(parfumLoccal, 1),
                new BasketItem(pills, 1),
                new BasketItem(importedBoxOfChocolates, 1)));

        salesService.printReceipt(basket);
    }

}
