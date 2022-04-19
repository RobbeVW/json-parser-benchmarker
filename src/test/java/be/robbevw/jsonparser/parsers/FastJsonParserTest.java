package be.robbevw.jsonparser.parsers;

class FastJsonParserTest extends AbstractParserTest {

    @Override
    JsonParser getParser() {
        return new FastJsonParser();
    }
}
