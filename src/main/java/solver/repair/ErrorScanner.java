package solver.repair;

import java.io.InputStream;
import java.util.Scanner;

public class ErrorScanner extends ScannerDelegateBase {
	public ErrorScanner(Process process) {
		InputStream stderr = process.getErrorStream();
		this.scnr = new Scanner(stderr);
		this.scnr.useDelimiter("\\A");
	}
}
