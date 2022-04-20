package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import com.alibaba.fastjson.JSON;

public class FastJsonParser implements JsonParser {

    @Override
    public Invoice jsonToInvoice(String jsonLine) {
        return JSON.parseObject(jsonLine, Invoice.class);
    }

    @Override
    public String invoiceToJson(Invoice invoice) {
        return JSON.toJSONString(invoice);
    }
}
