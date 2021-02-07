package KWIC;

import java.util.SortedSet;

public class DataSink implements Runnable {

	private Pipe<SortedSet<String>> inPipe;
	private SortedSet<String> temp;

	public DataSink(Pipe<SortedSet<String>> inPipe) {
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

				Pipeline.arrivalTime = System.nanoTime();
				System.out.println();
				System.out.println("[Statistics]");
				System.out.println();
				System.out.println("Start Time: " + Pipeline.startTime);
				System.out.println("Arrival Time: " + Pipeline.arrivalTime);
				System.out.println("Duration: " + ((Pipeline.arrivalTime-Pipeline.startTime)/1000000) + "(ms)");
				System.out.println();

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
