package be.robbevw.jsonparser.controllers;

import be.robbevw.jsonparser.exception.ResourceNotFoundException;
import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.services.InvoiceService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public void save(@RequestBody String jsonInvoice) {
        invoiceService.save(jsonInvoice);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public String findById(@PathVariable long id) {
        return invoiceService
                .getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "ID", id));
    }

}

