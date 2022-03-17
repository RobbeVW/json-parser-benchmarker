package be.robbevw.jsonparser.services;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public record InvoiceServiceImpl(
        InvoiceRepository invoiceRepository) implements InvoiceService {

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
