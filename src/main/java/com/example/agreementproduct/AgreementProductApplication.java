package com.example.agreementproduct;

import com.example.agreementproduct.Services.Service;
import com.example.agreementproduct.Services.ServiceImpl;
import com.example.agreementproduct.models.Agreement;
import com.example.agreementproduct.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.IIOException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class AgreementProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgreementProductApplication.class, args);

        try {
            Agreement agreement = new Agreement("UserName", new ArrayList<>());
            Product product1 = new Product("productName1", BigDecimal.valueOf(20.5));
            agreement.addChild(product1);
                 Product product1_2 = new Product("productName1_2", BigDecimal.valueOf(30.5));
                 product1.addChild(product1_2);
                    Product product1_2_2 = new Product("productName1_2_2", BigDecimal.valueOf(10.5));
                    product1_2.addChild(product1_2_2);
                    Product product1_2_3 = new Product("productName1_2_3", BigDecimal.valueOf(10.5));
                    product1_2.addChild(product1_2_3);
                 Product product1_3 = new Product("productName1_3", BigDecimal.valueOf(22.65));
                 product1.addChild(product1_3);
                    Product product1_3_2 = new Product("productName1_3_2", BigDecimal.valueOf(10.5));
                    product1_3.addChild(product1_3_2);
            Product product2 = new Product("productName2", BigDecimal.valueOf(25.5));
            agreement.addChild(product2);
            Product product3 = new Product("productName3", BigDecimal.valueOf(25.5123423));
            agreement.addChild(product3);
                Product product3_2 = new Product("productName3_2", BigDecimal.valueOf(10.5));
                product3.addChild(product3_2);

            Service service = new ServiceImpl();
            System.out.println(service.save(agreement));

        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }


}
