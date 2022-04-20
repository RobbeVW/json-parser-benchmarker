package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import com.google.gson.Gson;

public class GsonParser implements JsonParser {

    private final Gson gson = new Gson();

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        return gson.fromJson(jsonLine, Invoice.class);
    }

    @Override
    public String invoiceToJson(Invoice invoice) {
        return gson.toJson(invoice);
    }
}
