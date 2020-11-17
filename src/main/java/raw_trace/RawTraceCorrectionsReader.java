package raw_trace;

import data.correction.ProgramTraceCorrection;

import java.util.Set;

public interface RawTraceCorrectionsReader {
    public Set<ProgramTraceCorrection> readRawTraceCorrections(String traceJson);
}
