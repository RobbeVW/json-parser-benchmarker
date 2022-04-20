package be.robbevw.jsonparser.services;

import java.util.Optional;

public interface InvoiceService {

    void save(String jsonInvoice);

    Optional<String> getById(Long id);
}
