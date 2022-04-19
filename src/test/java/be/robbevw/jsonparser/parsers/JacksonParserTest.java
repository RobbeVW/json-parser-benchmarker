package be.robbevw.jsonparser.parsers;

public class JacksonParserTest extends AbstractParserTest {

    @Override
    JsonParser getParser() {
        return new JacksonParser();
    }
}
