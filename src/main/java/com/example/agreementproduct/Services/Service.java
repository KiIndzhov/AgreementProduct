package com.example.agreementproduct.Services;

import com.example.agreementproduct.models.Agreement;

import java.io.IOException;

public interface Service {
    String save(Agreement agreement) throws IOException;

    Agreement getAgreement(String path) throws IOException;
}
