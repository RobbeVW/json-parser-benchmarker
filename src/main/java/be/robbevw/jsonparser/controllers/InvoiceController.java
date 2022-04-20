package be.robbevw.jsonparser.controllers;

import be.robbevw.jsonparser.exception.ResourceNotFoundException;
import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.services.InvoiceService;
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


    @GetMapping()
    public List<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice findById(@PathVariable long id) {
        return invoiceService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "ID", id));
    }

}

