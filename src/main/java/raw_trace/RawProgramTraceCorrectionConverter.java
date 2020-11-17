package raw_trace;

import data.ProgramTrace;
import raw_trace.data.RawProgramTraceCorrection;

public interface RawProgramTraceCorrectionConverter {
    public ProgramTrace convertTraceCorrection(RawProgramTraceCorrection rawTraceCorrection);
}
