package com.zhicheng.sales.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhicheng.sales.model.Product;
import com.zhicheng.sales.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductCategoryService.class);


    @Autowired
    ProductCategoryService productCategoryService;

    private final String fileName = "Product.json";

    private List<Product> products;

    public ProductService() {
        ClassLoader classLoader =  getClass().getClassLoader();
        final File f = new File(classLoader.getResource(fileName).getPath());
        try {
            ObjectMapper mapper = new ObjectMapper();
            products = Arrays.asList(mapper.readValue(f, Product[].class));
        } catch (IOException e) {
            logger.error("can not read product category file");
        }
    }

    public Optional<ProductCategory> getProductCategory(Product product) {
        if(Optional.ofNullable(product).isPresent()) {
            int categoryId = product.getProductCategoryId();
            return productCategoryService.getProductCategories().stream().filter(productCategory -> productCategory.getId() == categoryId).findFirst();
        }
        return Optional.empty();
    }

    public List<Product> getProducts() {
        return products;
    }

}
