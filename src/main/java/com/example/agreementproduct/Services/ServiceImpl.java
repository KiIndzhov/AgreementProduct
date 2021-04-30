package com.example.agreementproduct.Services;

import com.example.agreementproduct.models.Agreement;
import com.example.agreementproduct.models.Product;
import com.example.agreementproduct.models.contracts.Parent;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private final String INDENT = "   ";
    private Integer i = 0;

    // Just a placeholder directory
    private final String ROOT = Paths.get(".").normalize().toAbsolutePath().toString();

    @Override
    public String save(Agreement agreement) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append("name: ").append(agreement.getName()).append(System.lineSeparator());
        fileContent.append("signed by: ").append(agreement.getSignedBy()).append(System.lineSeparator());
        fileContent.append("products: [");
        for (Product product : agreement.getProductList()) {
            fileContent.append(System.lineSeparator());
            serializeProduct(product, fileContent, 1);
        }
        fileContent.append(System.lineSeparator());
        fileContent.append("]");
        String path = ROOT + agreement.getName() + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(fileContent.toString());
        writer.close();

        return path;

    }

    private void serializeProduct(Product product, StringBuilder fileContent, int indentCounter) {
        fileContent.append(INDENT.repeat(Math.max(0, indentCounter)));
        fileContent.append("name: ").append(product.getName()).append(System.lineSeparator());
        fileContent.append(INDENT.repeat(Math.max(0, indentCounter)));
        fileContent.append("price: ").append(product.getPrice().setScale(2, RoundingMode.HALF_UP)).append(System.lineSeparator());
        fileContent.append(INDENT.repeat(Math.max(0, indentCounter)));
        fileContent.append("childrenProducts: [");
        for (Product childrenProduct : product.getChildrenProducts()) {
            indentCounter++;
            fileContent.append(System.lineSeparator());
            serializeProduct(childrenProduct, fileContent, indentCounter);
            indentCounter--;
        }
        if (product.getChildrenProducts().size() != 0) {
            fileContent.append(System.lineSeparator());
            fileContent.append(INDENT.repeat(Math.max(0, indentCounter)));
        }
        fileContent.append("]");

    }


    @Override
    public Agreement getAgreement(String path) throws IOException {
        i = 0;
        Agreement agreement = new Agreement();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        agreement.setName(lines.get(i++).substring(6));
        agreement.setSignedBy(lines.get(i++).substring(11));
        i++;
        while (!lines.get(i).equals("]")) {
            i = deserializeProduct(agreement, lines, i);
        }

        return agreement;
    }

    private int deserializeProduct(Parent parent, List<String> lines, int i) {
        Product product = new Product();
        product.setName(lines.get(i++).trim().substring(6));
        BigDecimal price = new BigDecimal(lines.get(i++).trim().substring(7));
        product.setPrice(price);
        parent.addChild(product);
        product.setParentObject(parent);
        while (!lines.get(i).contains("]")) {
            if(!lines.get(i).trim().startsWith("name")){
                i++;
            }
            i = deserializeProduct(product, lines, i);
        }
        i++;
        return i;
    }
}
