package raw_trace;

import com.google.gson.Gson;
import raw_trace.data.RawProgramTraceCorrection;

public class JsonTraceCorrectionsParserImpl implements  JsonTraceCorrectionsParser {

    private Gson gson;

    public JsonTraceCorrectionsParserImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public RawProgramTraceCorrection readTraceCorrections(String json) {
        return null;
    }
}
