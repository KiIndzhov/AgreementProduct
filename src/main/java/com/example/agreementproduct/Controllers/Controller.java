package com.example.agreementproduct.Controllers;

import com.example.agreementproduct.Services.Service;
import com.example.agreementproduct.models.Agreement;
import com.example.agreementproduct.models.PathDto;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/")
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    //Requires a valid object in the body
    @PostMapping("save")
    public String saveAgreement(@RequestBody Agreement agreement) throws IOException {
        return service.save(agreement);
    }

    // Requires the path in the body, has to have escape characters on the slashes
    @GetMapping("get")
    public Agreement getAgreement(@RequestBody PathDto path) throws IOException {
        return service.getAgreement(path.getPath());
    }

    // Requires the path as a URL encoded parameter
    @GetMapping("getUrl")
    public Agreement getAgreementWithUrl(@RequestParam String path) throws IOException {
        return service.getAgreement(path);
    }
}
