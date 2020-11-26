package solver.repair;

import java.util.Scanner;

public abstract class ScannerDelegateBase implements  ScannerDelegate{
	protected Scanner scnr;

	public void close() {
		this.scnr.close();
	}

	public boolean hasNext() {
		return this.scnr.hasNext();
	}

	public String next() {
		return this.scnr.next();
	}

	public String nextLine() {
		return this.scnr.nextLine();
	}

	public void remove() {
		this.scnr.remove();
	}
}
