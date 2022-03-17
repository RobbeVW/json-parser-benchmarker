package be.robbevw.jsonparser.services;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public final class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
