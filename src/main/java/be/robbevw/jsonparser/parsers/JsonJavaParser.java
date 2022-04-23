package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonJavaParser implements JsonParser {

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        final JSONObject jsonObject = new JSONObject(jsonLine);
        final JSONArray jsonArray = jsonObject.getJSONArray("comment");
        List<String> listData = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++){
                listData.add(jsonArray.getString(i));
            }
        }
        return new Invoice()
            .setTotalAmount(jsonObject.getBigDecimal("totalAmount"))
            .setCompanyName(jsonObject.getString("companyName"))
            .setComment(listData);
    }

    @Override
    public String invoiceToJson(Invoice invoice) {
        return new JSONObject(invoice).toString();
    }
}
