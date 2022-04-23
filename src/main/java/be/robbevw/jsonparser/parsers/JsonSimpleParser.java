package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSimpleParser implements JsonParser {

    private final JSONParser parser = new JSONParser();

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        try {
            JSONObject object = (JSONObject) parser.parse(jsonLine);
            JSONArray jsonArray = (JSONArray) object.get("comment");
            List<String> listData = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    listData.add(jsonArray.get(i).toString());
                }
            }
            return new Invoice()
                .setTotalAmount(BigDecimal.valueOf((double) object.get("totalAmount")))
                .setCompanyName((String) object.get("companyName"))
                .setComment(listData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String invoiceToJson(Invoice invoice) {
        Map<String, Object> map = new HashMap<>(){{
            put("id", invoice.getId());
            put("totalAmount", invoice.getTotalAmount());
            put("companyName", invoice.getCompanyName());
            put("comment", invoice.getComment());
        }};
        return JSONValue.toJSONString(map);
    }
}
