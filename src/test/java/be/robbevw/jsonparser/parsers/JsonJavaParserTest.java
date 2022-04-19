package be.robbevw.jsonparser.parsers;

class JsonJavaParserTest extends AbstractParserTest {

    @Override
    JsonParser getParser() {
        return new JsonJavaParser();
    }
}
