package be.robbevw.jsonparser.parsers;

class JsonSimpleParserTest extends AbstractParserTest {

    @Override
    JsonParser getParser() {
        return new JsonSimpleParser();
    }
}
