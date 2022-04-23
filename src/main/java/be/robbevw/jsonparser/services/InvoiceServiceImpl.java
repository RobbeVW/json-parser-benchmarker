package be.robbevw.jsonparser.services;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.parsers.*;
import be.robbevw.jsonparser.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public final class InvoiceServiceImpl implements InvoiceService {

    // Replace this instance with your desired parser
//    private final JsonParser jsonParser = new JacksonParser();
    private final JsonParser jsonParser = new GsonParser();
//    private final JsonParser jsonParser = new JsonSimpleParser();
//    private final JsonParser jsonParser = new FastJsonParser();
//    private final JsonParser jsonParser = new JsonJavaParser();

    private final InvoiceRepository invoiceRepository;

    @Override
    public void save(String jsonInvoice) {
        final Invoice parsedInvoice = jsonParser.jsonToInvoice(jsonInvoice);

        invoiceRepository.save(parsedInvoice);
    }

    public Optional<String> getById(Long id) {
        Optional<Invoice> invoice =  invoiceRepository.findById(id);

        if (invoice.isPresent()){
            String json = jsonParser.invoiceToJson(invoice.get());
            return Optional.of(json);
        }
        return Optional.empty();
    }
}
