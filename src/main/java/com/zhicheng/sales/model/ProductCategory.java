package com.zhicheng.sales.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategory {
    private int id;
    private String name;
    private Boolean exemption;
}
