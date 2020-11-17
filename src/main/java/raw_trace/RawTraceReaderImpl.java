package raw_trace;

import data.correction.ProgramTraceCorrection;

import java.util.Set;

public class RawTraceReaderImpl implements RawTraceCorrectionsReader {

    private JsonTraceCorrectionsParser parser;
    private RawProgramTraceCorrectionConverter converter;

    public RawTraceReaderImpl(
            JsonTraceCorrectionsParser parser,
            RawProgramTraceCorrectionConverter converter
    ) {
        this.parser = parser;
        this.converter = converter;
    }

    @Override
    public Set<ProgramTraceCorrection> readRawTraceCorrections(String traceJson) {
        return null;
    }
}
