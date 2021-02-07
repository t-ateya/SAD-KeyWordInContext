package KWIC;

import java.util.Scanner;

public class DataSource implements Runnable {

	private Scanner input;
	protected Pipe<String> outPipe;

	public DataSource(Pipe<String> outPiple) {
		this.outPipe = outPipe;
		this.input = new Scanner(System.in);
	}

	protected synchronized void readData() {
		outPipe.push(input.nextLine());

		Pipeline.startTime = System.nanoTime();
	}

	@Override
	public void run() {
		while (input.hasNext()) {
			readData();
		}

		System.out.println("Closing application...GoodBye From Data Source");
		System.exit(0);

	}

}
