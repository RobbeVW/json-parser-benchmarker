package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import lombok.Getter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonParserBenchmark {

    @State(Scope.Benchmark)
    public static class TestData {

        @Getter
        private File invoicesJson;

        @Setup(Level.Trial)
        public void setup() {
            invoicesJson = new File("src/main/resources/MOCK_DATA.json");
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkedObjectMapper {
        //public final ObjectMapper mapper = new ObjectMapper();
    }

    @Benchmark
    public List<Invoice> init() throws IOException {
       //optie 1
        /*
        List<Invoice> invoices = new ArrayList<Invoice>();
        File invoiceJson = new File("src/main/resources/MOCK_DATA.json");
        JSONReader reader = new JSONReader(new FileReader(invoiceJson));
        reader.startArray();
        try {
            while (reader.hasNext()){
                Invoice in=reader.readObject(Invoice.class);
                invoices.add(in);
            }
            reader.endArray();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;

         */

        //optie 2 (iets compacter ook van Mykong :))
        List<Invoice>invoices = null;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/MOCK_DATA.json"))) {
            String content = lines.collect(Collectors.joining());
            invoices=JSON.parseArray(content,Invoice.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return invoices;
    }
}
