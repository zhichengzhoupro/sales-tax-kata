package com.zhicheng.sales.service;

import com.zhicheng.sales.model.Origin;
import com.zhicheng.sales.model.Product;
import com.zhicheng.sales.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class TaxService {

    private static BigDecimal TVA_TAX_RATE = BigDecimal.valueOf(0.10);
    private static BigDecimal IMPORT_TAX_RATE =  BigDecimal.valueOf(0.05);

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;

    public BigDecimal getProductTaxRate(Product product) {
        BigDecimal tax = BigDecimal.valueOf(0);

        tax = AddTVATax(product, tax);

        tax = AddImportTax(product, tax);

        return tax;
    }

    private BigDecimal AddTVATax(Product product, BigDecimal tax) {
        Optional<ProductCategory> optionalProductCategory = productService.getProductCategory(product);
        if(optionalProductCategory.isPresent()) {
            ProductCategory productCategory = optionalProductCategory.get();
            if(!productCategory.getExemption()){
                tax = tax.add(TVA_TAX_RATE);
            }
        }
        return tax;
    }

    private BigDecimal AddImportTax(Product product, BigDecimal tax) {
        if(Origin.IMPORTED == product.getOrigin()) {
            tax = tax.add(IMPORT_TAX_RATE);
        }
        return tax;
    }

    public BigDecimal calculateTax(BigDecimal prise, int quantity, BigDecimal taxRate) {
        return round(
                prise.multiply(BigDecimal.valueOf(quantity)).multiply(taxRate),
                BigDecimal.valueOf(0.05),
                RoundingMode.UP);
    }

    public BigDecimal calculateTotal(BigDecimal prise, int quantity, BigDecimal taxRat) {
        return  calculateTax(prise, quantity, taxRat).add(prise.multiply(BigDecimal.valueOf(quantity)));
    }


    public  BigDecimal round(BigDecimal value, BigDecimal increment,
                                   RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }

}
