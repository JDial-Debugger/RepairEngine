package raw_trace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RawVariableParserImplTest {

    private RawVariableParserImpl parserToTest;
    private Map<String, String> mockInput;

    @BeforeEach
    public void SetUp() {
        parserToTest = new RawVariableParserImpl();
        mockInput = mock(Map.class);
    }

    @Test
    void parseRawVariables() {

    }
}