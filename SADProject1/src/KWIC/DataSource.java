package KWIC;

import java.util.Scanner;

public class DataSource implements Runnable {

	private Scanner scanner;
	protected Pipe<String> outPipe;
	
	public DataSource(Pipe<String> outPiple) {
		this.outPipe = outPipe;
		this.scanner = new Scanner(System.in);
	}
	
	protected synchronized void readData() {
		outPipe.push(scanner.nextLine());
		
		Pipeline.startTime = System.nanoTime();
	}

	@Override
	public void run() {
		while(scanner.hasNext()) {
			readData();
		}
		
		System.out.println("Closing application...GoodBye From Data Source");
		System.exit(0);

	}

}
