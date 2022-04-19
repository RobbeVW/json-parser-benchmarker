package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;

public interface JsonParser {

    Invoice jsonToInvoice(String jsonLine);
}
