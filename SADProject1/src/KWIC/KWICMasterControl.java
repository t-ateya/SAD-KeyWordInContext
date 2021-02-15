package KWIC;

import java.util.Arrays;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.SortedSet;

public class KWICMasterControl {
	public static void main(String[] args) {
		printHeader(args);
		new KWICMasterControl(args);
	}
	
	private static void printHeader(String[] stopWords) {
		System.out.println("=====================================");
		System.out.println("Input titles in each line.");
		System.out.println("Press [ENTER] to input more lines");
		System.out.println("Press [CTRL+D] when you are done");
		//System.out.println("Ignoring words: [" + Arrays.stream(stopWords).collect(Collectors.joining(", ")) + "]");
		
	}

	public KWICMasterControl(String...stopWords) {
		Pipe<String>p1 = new Pipe<>();
		Pipe<String>p2 = new Pipe<>();
		Pipe<Set<String>>p3 = new Pipe<>();
		
		DataSource dataSource = new DataSource(p1);
		Filter circularShift = new CircularShift(p1, p2, stopWords);
		Filter alphabetizer = new Alphabetizer(p2, p3);
		DataSink dataSink = new DataSink(p3);
		
		Pipeline pipeline = new Pipeline();
		pipeline.generateData(dataSource)
				.transformData(circularShift)
				.transformData(alphabetizer)
				.outputData(dataSink)
				.run();
				
	}

}
