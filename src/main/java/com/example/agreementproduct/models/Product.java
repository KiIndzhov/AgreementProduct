package com.example.agreementproduct.models;

import com.example.agreementproduct.models.contracts.Parent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product implements Parent{

    private String name;
    private BigDecimal price;

    private Parent parentObject;
    private List<Product> childrenProducts;

    public Product() {
        this.childrenProducts = new ArrayList<>();
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.childrenProducts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    public Parent getParentObject() {
        return parentObject;
    }

    public void setParentObject(Parent parentObject) {
        if(this.parentObject != null){
            throw new IllegalArgumentException("Product already has a Parent");
        }
        this.parentObject = parentObject;
    }

    public List<Product> getChildrenProducts() {
        return childrenProducts;
    }

    public void setChildrenProducts(List<Product> childrenProducts) {
        this.childrenProducts = childrenProducts;
    }

    @Override
    public void addChild(Product product) {
        if(product.getParentObject() != null ){
            throw new IllegalArgumentException("Cannot add Product that already has parent");
        }
        this.childrenProducts.add(product);
    }
}
