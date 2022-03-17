package be.robbevw.jsonparser.controllers;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public void save(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
    }

}

