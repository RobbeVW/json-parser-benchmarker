package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import org.json.*;
import lombok.Getter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonParserBenchmark {

    @State(Scope.Benchmark)
    public static class TestData {

        @Getter
        private Path invoicesJson;

        @Setup(Level.Trial)
        public void setup() {
            invoicesJson = Paths.get("src/main/resources/MOCK_DATA.json");
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkedObjectMapper {
        public List<Invoice> readValue(Path file) throws IOException {
            List<Invoice> invoices = new ArrayList<>();
            JSONObject jsonObject;

            BufferedReader reader = Files.newBufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                jsonObject = new JSONObject(line);
                invoices.add(jsonToInvoice(jsonObject));
            }
            return invoices;
        }

        //helper method
        private Invoice jsonToInvoice(JSONObject json) {
            Invoice invoice = new Invoice();
            invoice.setTotalAmount(json.getBigDecimal("totalAmount"));
            invoice.setCompanyName(json.getString("companyName"));
            invoice.setComment(json.getString("comment"));
//            invoice.setComment("hello it's me");
            return invoice;
        }

        public List<Invoice> init(BenchmarkedObjectMapper objectMapper, TestData testData) throws IOException {
            return objectMapper.readValue(testData.invoicesJson);
        }
    }

    @Benchmark
    public List<Invoice> init(BenchmarkedObjectMapper objectMapper, TestData testData) throws IOException {
        return objectMapper.readValue(testData.invoicesJson);
    }
}