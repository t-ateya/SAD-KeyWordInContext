package KWIC;

import java.util.SortedSet;
import java.util.TreeSet;

public class Alphabetizer extends Filter<String, SortedSet<String>> {

	private SortedSet<String> sortedSet = new TreeSet();

	public Alphabetizer(Pipe<String> inPipe, Pipe<SortedSet<String>> outPipe) {
		super(inPipe, outPipe);

	}

	@Override
	public void filter() {
		if(!inPipe.buffer.isEmpty()) {
			String s = inPipe.pull();
			sortedSet.add(s);
			
			outPipe.push(new TreeSet<>(sortedSet));
		}

	}

}
