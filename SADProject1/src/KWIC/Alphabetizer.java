package KWIC;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String value1, String value2) {
		//return Integer.compare(value1.length(), value2.length());// arranges strings in ascending order 
		 return Integer.compare(value2.length(), value1.length());//arranges strings in descending order
	
	}
}

public class Alphabetizer extends Filter<String, Set<String>> {

	private Set<String> sorted = new TreeSet<>();
	
	public Alphabetizer(Pipe<String> inPipe, Pipe<Set<String>> outPipe) {
		super(inPipe, outPipe);

	}

	@Override
	public void filter() {
		if (!inPipe.buffer.isEmpty()) {
			String s = inPipe.pull();
			sorted.add(s);
			outPipe.push(new TreeSet<>(sorted));
		}

	}

}
