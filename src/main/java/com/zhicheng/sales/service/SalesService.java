package com.zhicheng.sales.service;


import com.zhicheng.sales.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    TaxService taxService;


    public Receipt getRecipt(Basket basket) {
        if (Optional.ofNullable(basket).isPresent()) {
            List<BasketItem> basketItems = basket.getBasketItems();
            if (!CollectionUtils.isEmpty(basketItems)) {
                List<ReceiptItem> receiptItems = basketItems.stream().map(basketItem -> {
                    final Product product = basketItem.getProduct();
                    final int quantity = basketItem.getQuantity();
                    final BigDecimal taxRate = taxService.getProductTaxRate(basketItem.getProduct());
                    final BigDecimal singlePrise = product.getPrise();
                    final BigDecimal tax = taxService.calculateTax(singlePrise, quantity, taxRate);
                    final BigDecimal total = taxService.calculateTotal(singlePrise, quantity, taxRate);
                    return new ReceiptItem(
                            product,
                            quantity,
                            tax,
                            total);
                }).collect(Collectors.toList());

                BigDecimal totalTax = receiptItems.stream().map(receiptItem -> receiptItem.getItemTax()).reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal totalPrise = receiptItems.stream().map(receiptItem -> receiptItem.getItemPrise()).reduce(BigDecimal.ZERO, BigDecimal::add);

                return new Receipt(receiptItems, totalTax, totalPrise);
            }
        }
        return null;
    }


    void printReceipt(Basket basket) {
        Receipt receipt = getRecipt(basket);
        if (Optional.ofNullable(receipt).isPresent()) {
            List<ReceiptItem> receiptItems = receipt.getReceiptItems();
            if (!CollectionUtils.isEmpty(receiptItems)) {
                receiptItems.forEach(receiptItem -> {
                    System.out.println(receiptItem.getQuantity() + " " + receiptItem.getProduct().getName() + ":" + receiptItem.getItemPrise());
                });
            }

            System.out.println("Sales Taxes : " + receipt.getTotalTax());
            System.out.println("total : " + receipt.getTotalPrise());
        }

    }

}
