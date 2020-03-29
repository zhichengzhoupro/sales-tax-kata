package com.zhicheng.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiptItem {
    private Product product;
    private int quantity;
    private BigDecimal itemTax;
    private BigDecimal itemPrise;
}
