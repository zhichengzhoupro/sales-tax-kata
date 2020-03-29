package com.zhicheng.sales.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhicheng.sales.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryService {

    private static Logger logger = LoggerFactory.getLogger(ProductCategoryService.class);

    private final String fileName = "ProductCategory.json";

    private List<ProductCategory> productCategories;

    public ProductCategoryService() {
        ClassLoader classLoader =  getClass().getClassLoader();
        final File f = new File(classLoader.getResource(fileName).getPath());
        try {
            ObjectMapper mapper = new ObjectMapper();
            productCategories = Arrays.asList(mapper.readValue(f, ProductCategory[].class));
        } catch (IOException e) {
            logger.error("can not read product category file");
        }
    }


    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }
}
