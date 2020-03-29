package com.zhicheng.sales.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private List<ReceiptItem> receiptItems;
    private BigDecimal totalTax;
    private BigDecimal totalPrise;

}
