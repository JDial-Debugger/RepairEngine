package solver.repair;

import java.io.FileWriter;

public class SketchInvoker implements SolverRepository {

	private FileWriter sketchInputWriter;
	private Process sketchProc;
	private Runtime runtime;
	private ScannerDelegate errorScanner;

	public SketchInvoker(FileWriter sketchInputWriter, Process sketchProc, Runtime runtime, ScannerDelegate errorScanner) {
		this.sketchInputWriter = sketchInputWriter;
		this.sketchProc = sketchProc;
		this.runtime = runtime;
		this.errorScanner = errorScanner;
	}

	@Override
	public String solve(String script) {
		return null;
	}
}
