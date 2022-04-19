package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import be.robbevw.jsonparser.parsers.JsonParser;
import be.robbevw.jsonparser.parsers.JsonSimpleParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@State(Scope.Benchmark)
public class JsonParserBenchmark {

    public static final String SOURCE_MOCK_JSON_DATA_SMALL = "src/main/resources/invoices/MockInvoicesSmall.json";
    public static final String SOURCE_MOCK_JSON_DATA_MEDIUM = "src/main/resources/invoices/MockInvoicesMedium.json";
    public static final String SOURCE_MOCK_JSON_DATA_LARGE = "src/main/resources/invoices/MockInvoicesLarge.json";

    // Replace this instance with your desired parser
    private final JsonParser jsonParser = new JsonSimpleParser();

    private List<String> testData;

    @Setup
    public void setUp() {
        testData = new ArrayList<>();
        populateTestData();
    }

    /**
     * <a href="https://www.baeldung.com/java-microbenchmark-harness">https://www.baeldung.com/java-microbenchmark-harness</a>
     * 6. Dead Code Elimination
     * return List bij Benchmarks is ivm invloed op test results..
     * returnType VOID zorgt voor dat compiler gaat optimisen en dat beinvloedt het resultaat.
     *
     * Benchmarkmodes javadocs:
     * <a href="http://javadox.com/org.openjdk.jmh/jmh-core/1.7/org/openjdk/jmh/annotations/Mode.html">http://javadox.com/org.openjdk.jmh/jmh-core/1.7/org/openjdk/jmh/annotations/Mode.html</a>
     * - Average time: average time per per operation.
     * - Single shot time: measures the time for a single operation.
     * - Throughput: operations per unit of time.
     * - Sample time: samples the time for each operation.
     */
    @Fork(value = 1, warmups = 1)
    @Benchmark()
    @BenchmarkMode(Mode.AverageTime)
    public List<Invoice> averageTime(){
        return testData.stream().map(jsonParser::jsonToInvoice).toList();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Invoice> singleShotTime() {
        return testData.stream().map(jsonParser::jsonToInvoice).toList();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public List<Invoice> throughPut() {
        return testData.stream().map(jsonParser::jsonToInvoice).toList();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public List<Invoice> sampleTime() {
        return testData.stream().map(jsonParser::jsonToInvoice).toList();
    }

    //#region helper method
    private void populateTestData() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(SOURCE_MOCK_JSON_DATA_SMALL))) {
            reader.lines().map(removeTrailingComma()).forEach(testData::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Function<String, String> removeTrailingComma() {
        return s -> s.replace("},", "}");
    }
    //#endregion
}
