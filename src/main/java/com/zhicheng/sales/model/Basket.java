package com.zhicheng.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Basket {
    private List<BasketItem> basketItems;
}
