package be.robbevw.jsonparser.services;

import be.robbevw.jsonparser.models.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    void save(String jsonInvoice);

    List<Invoice> findAll();

    Optional<Invoice> findById(Long id);
}
