package KWIC;

import java.util.Queue;
import java.util.Set;

public class DataSink implements Runnable {

	private Pipe<Set<String>> inPipe;
	private Set<String> temp;

	public DataSink(Pipe<Set<String>> inPipe) {
		this.inPipe = inPipe;
	}

	public synchronized void writeData() {
		if (!inPipe.buffer.isEmpty()) {
			if (temp != null && temp.equals(inPipe.peek())) {
				inPipe.pull();
			} else {
				System.out.println("==========================");
				System.out.println();
				System.out.println("[Results]");
				System.out.println();
				temp = inPipe.pull();
				temp.forEach(System.out::println);

				

			}
		}
	}

	@Override
	public void run() {
		while (true) {
			writeData();
		}

	}

}
