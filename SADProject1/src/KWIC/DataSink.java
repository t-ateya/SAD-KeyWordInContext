package KWIC;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class DataSink implements Runnable {

	private Pipe<Set<String>> inPipe;
	private TreeSet<String> temp;
	//private ArrayList<String>temp = new ArrayList<>(temp1);

	public DataSink(Pipe<Set<String>> inPipe) {
		this.inPipe = inPipe;
	}
	public synchronized void writeData() {
		if (!inPipe.buffer.isEmpty()) {
			if (temp != null && temp.equals(inPipe.peek())) {
				inPipe.pull();
			} else {
				
				temp = (TreeSet<String>) inPipe.pull();
				
			}
			
			/*System.out.println("============[RESULSTS]==============");
			for (var s:temp) {
				System.out.println(s);
			}*/
			System.out.println(temp);
			for (var p : temp) {
				System.out.println(p);
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
