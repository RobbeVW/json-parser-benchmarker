package be.robbevw.jsonparser.parsers;

import be.robbevw.jsonparser.models.Invoice;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractParserTest {

    private final JsonParser jsonParser;

    public AbstractParserTest() {
        jsonParser = getParser();
    }

    @Test
    void shouldParseObject() {
        final String testJsonString = "{\"totalAmount\":124901.32,\"companyName\":\"Zontrax\",\"comment\":\"molestie sed justo pellentesque\"}";

        final Invoice invoice = jsonParser.jsonToInvoice(testJsonString);

        assertThat(invoice.getTotalAmount()).isEqualTo("124901.32");
        assertThat(invoice.getCompanyName()).isEqualTo("Zontrax");
        assertThat(invoice.getComment()).isEqualTo("molestie sed justo pellentesque");
    }

    abstract JsonParser getParser();
}
