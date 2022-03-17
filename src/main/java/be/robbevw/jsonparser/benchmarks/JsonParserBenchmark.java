package be.robbevw.jsonparser.benchmarks;

import be.robbevw.jsonparser.models.Invoice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        public final ObjectMapper mapper = new ObjectMapper();
    }

    @Benchmark
    public List<Invoice> init(BenchmarkedObjectMapper objectMapper, TestData testData) throws IOException {
        return objectMapper.mapper.readValue(testData.invoicesJson, new TypeReference<>() {});
    }
}
