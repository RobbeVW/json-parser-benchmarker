package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;

public class JsonSimpleParser implements JsonParser {

    private final JSONParser parser = new JSONParser();

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        try {
            JSONObject object = (JSONObject) parser.parse(jsonLine);

            return new Invoice()
                .setTotalAmount(BigDecimal.valueOf((double) object.get("totalAmount")))
                .setCompanyName((String) object.get("companyName"))
                .setComment((String) object.get("comment"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
