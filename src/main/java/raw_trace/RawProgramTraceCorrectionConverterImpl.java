package raw_trace;

import data.ProgramTrace;
import raw_trace.data.RawProgramTraceCorrection;

public class RawProgramTraceCorrectionConverterImpl implements RawProgramTraceCorrectionConverter {

    private RawVariableParser rawVariableParser;

    public RawProgramTraceCorrectionConverterImpl(RawVariableParser rawVariableParser) {
        this.rawVariableParser = rawVariableParser;
    }

    @Override
    public ProgramTrace convertTraceCorrection(RawProgramTraceCorrection rawTraceCorrection) {
        return null;
    }
}
