package be.robbevw.jsonparser.parsers;

class GsonParserTest extends AbstractParserTest {

    @Override
    JsonParser getParser() {
        return new GsonParser();
    }
}
