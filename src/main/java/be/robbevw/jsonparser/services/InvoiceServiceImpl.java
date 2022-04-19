package be.robbevw.jsonparser.services;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.parsers.JsonParser;
import be.robbevw.jsonparser.parsers.JsonSimpleParser;
import be.robbevw.jsonparser.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public final class InvoiceServiceImpl implements InvoiceService {

    // Replace this instance with your desired parser
    private final JsonParser jsonParser = new JsonSimpleParser();

    private final InvoiceRepository invoiceRepository;

    @Override
    public void save(String jsonInvoice) {
        final Invoice parsedInvoice = jsonParser.jsonToInvoice(jsonInvoice);

        invoiceRepository.save(parsedInvoice);
    }
}
