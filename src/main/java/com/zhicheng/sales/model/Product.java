package com.zhicheng.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private int  productCategoryId;
    private Origin origin;
    private BigDecimal prise;
}
