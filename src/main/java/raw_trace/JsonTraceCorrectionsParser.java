package raw_trace;

import raw_trace.data.RawProgramTraceCorrection;

public interface JsonTraceCorrectionsParser {
    public RawProgramTraceCorrection readTraceCorrections(String json);
}
