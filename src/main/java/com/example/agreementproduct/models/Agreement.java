package com.example.agreementproduct.models;

import com.example.agreementproduct.models.contracts.Parent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Agreement implements Parent {

    private String name;
    private String signedBy;

    private final List<Product> productList;

    public Agreement() {
        this.productList = new ArrayList<>();
    }

    public Agreement(String signedBy, List<Product> productList) {
        this.name = formAgreementName();
        this.signedBy = signedBy;
        this.productList = productList;

    }

    private String formAgreementName() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return "Agreement " + date;
    }

    @Override
    public void addChild(Product product){
        if(product.getParentObject() instanceof Product){
            throw new IllegalArgumentException("Cannot add Product with parent Product to Agreement");
        }
        this.productList.add(product);
    }

    public String getName() {
        return name;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }


}
