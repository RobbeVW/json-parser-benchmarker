package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class JsonParserBenchmark {

    private List<String> testData;
    private final ObjectMapper mapper = new ObjectMapper(); //jackson

    @Setup
    public void setUp(){
        testData = getTestData();
    }

    /**
     * https://www.baeldung.com/java-microbenchmark-harness
     * 6. Dead Code Elimination
     * return List bij Benchmarks is ivm invloed op test results..
     * returnType VOID zorgt voor dat compiler gaat optimisen en dat beinvloedt het resultaat.
     *
     * Benchmarkmodes javadocs:
     * http://javadox.com/org.openjdk.jmh/jmh-core/1.7/org/openjdk/jmh/annotations/Mode.html
     * - Average time: average time per per operation.
     * - Single shot time: measures the time for a single operation.
     * - Throughput: operations per unit of time.
     * - Sample time: samples the time for each operation.
     */
    @Fork(value = 1, warmups = 1)
    @Benchmark()
    @BenchmarkMode(Mode.AverageTime)
    public List<Invoice> averageTime(){
        List<Invoice> invoices = new ArrayList<>();
        for (String line : testData){
            invoices.add(jsonToInvoice(line));
        }
        return invoices;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Invoice> singleShotTime() {
        List<Invoice> invoices = new ArrayList<>();
        for (String line : testData) {
            invoices.add(jsonToInvoice(line));
        }
        return invoices;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public List<Invoice> throughPut() {
        List<Invoice> invoices = new ArrayList<>();
        for (String line : testData) {
            invoices.add(jsonToInvoice(line));
        }
        return invoices;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public List<Invoice> sampleTime() {
        List<Invoice> invoices = new ArrayList<>();
        for (String line : testData) {
            invoices.add(jsonToInvoice(line));
        }
        return invoices;
    }

    //JsonParser implementation
    private Invoice jsonToInvoice(String line) {
        Invoice invoice = null;
        //jackson
        try {
            invoice = mapper.readValue(line, Invoice.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

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