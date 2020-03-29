package com.zhicheng.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BasketItem {
    private Product product;
    private int quantity;
}
