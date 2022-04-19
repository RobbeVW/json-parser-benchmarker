package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonParserBenchmarkTest {

    private static final int MIN_COMMENT_SIZE = 750;
    private static final int MAX_COMMENT_SIZE = 1500;

    private JsonParserBenchmark jsonParserBenchmark;

    @BeforeEach
    void setUp() {
        jsonParserBenchmark = new JsonParserBenchmark();
        jsonParserBenchmark.setUp();
    }

    @Test
    void averageTime() {
        final List<Invoice> invoices = jsonParserBenchmark.averageTime();

        assertParsingResults(invoices);
    }

    @Test
    void singleShotTime() {
        final List<Invoice> invoices = jsonParserBenchmark.singleShotTime();

        assertParsingResults(invoices);
    }

    @Test
    void throughPut() {
        final List<Invoice> invoices = jsonParserBenchmark.throughPut();

        assertParsingResults(invoices);
    }

    @Test
    void sampleTime() {
        final List<Invoice> invoices = jsonParserBenchmark.sampleTime();

        assertParsingResults(invoices);
    }

    private void assertParsingResults(List<Invoice> invoices) {
        assertThat(invoices).hasSize(1000);

        assertThat(invoices.get(0).getTotalAmount()).isEqualTo("124901.32");
        assertThat(invoices.get(0).getCompanyName()).isEqualTo("Reputation");
        assertThat(invoices.get(0).getComment()).isEqualTo("7PTLp[37e3iM");
        assertThat(invoices.get(999).getTotalAmount()).isEqualTo("842717.24");
        assertThat(invoices.get(999).getCompanyName()).isEqualTo("Handkerchief");
        assertThat(invoices.get(999).getComment()).isEqualTo("I7^VYqY8L7{sP1s7q!r13-tRy*V@8D");

//        assertThat(invoices).allSatisfy(invoice -> {
//            assertThat(invoice.getTotalAmount()).isPositive();
//            assertThat(invoice.getCompanyName()).isNotEmpty();
//            assertThat(invoice.getComment()).isNotEmpty();
//            assertThat(invoice.getComment()).hasSizeBetween(MIN_COMMENT_SIZE, MAX_COMMENT_SIZE);
//        });
    }
}
