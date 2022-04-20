package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonParser implements JsonParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        try {
           return objectMapper.readValue(jsonLine, Invoice.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String invoiceToJson(Invoice invoice) {
        try {
            return objectMapper.writeValueAsString(invoice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
