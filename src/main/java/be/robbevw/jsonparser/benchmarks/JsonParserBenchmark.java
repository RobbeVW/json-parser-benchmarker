package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import org.json.*;
import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class JsonParserBenchmark {

    private List<String> testData;

    @Setup
    public void setUp(){
        testData = getTestData();
    }


    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.All)
    public List<Invoice> init(){
        List<Invoice> invoices = new ArrayList<>();
        for (String line : testData){
            invoices.add(jsonToInvoice(line));
        }
        return invoices;
        /**
         * https://www.baeldung.com/java-microbenchmark-harness
         * 6. Dead Code Elimination
         * return List is omdat returnType VOID zorgt voor dat compiler gaat optimisen en dat beinvloedt het resultaat.
         */
    }

    //JsonParser implementation
    private Invoice jsonToInvoice(String line) {
        //json-java
        JSONObject json = new JSONObject(line);
        Invoice invoice = new Invoice();

        invoice.setTotalAmount(json.getBigDecimal("totalAmount"));
        invoice.setCompanyName(json.getString("companyName"));
        invoice.setComment(json.getString("comment"));

        return invoice;
    }

    //#region helper method
    public List<String> getTestData() {
        List<String> testData = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/MOCK_DATA.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                testData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }
//#endregion
}