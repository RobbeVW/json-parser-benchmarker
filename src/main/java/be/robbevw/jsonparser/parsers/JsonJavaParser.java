package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import org.json.JSONObject;

public class JsonJavaParser implements JsonParser {

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        final JSONObject jsonObject = new JSONObject(jsonLine);

        return new Invoice()
            .setTotalAmount(jsonObject.getBigDecimal("totalAmount"))
            .setCompanyName(jsonObject.getString("companyName"))
            .setComment(jsonObject.getString("comment"));
    }
}
