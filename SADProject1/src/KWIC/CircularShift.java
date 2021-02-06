package KWIC;

public class CircularShift extends Filter {
	
	public final String[] STOP_WORDS;

	public CircularShift(Pipe<String> inPipe, Pipe<String> outPipe, String... stopWords) {
		super(inPipe, outPipe);
		this.STOP_WORDS = stopWords;
		
	}

	@Override
	public void filter() {
		if (!inPipe.buffer.isEmpty()) {
			
			String sentence = (String) inPipe.pull();
			String []words = extractWords(sentence);
			for (int i = 0; i<words.length; i++) {
				String firstWord = words[0];
				if (!isStopWord(firstWord)) {
					String shitedSentence = String.join(" ", words);
					outPipe.push(shitedSentence);
				}
				
				String[] shiftedWords = leftShift(words);
				System.arraycopy(shiftedWords, 0, words, 0, shiftedWords.length);
				
			}
			
		}
		
		
	}

	private String[] leftShift(String[] s) {
		String[] shiftedWords=new String[s.length];
		
		if (s.length > 1) {
			System.arraycopy(s, 1, shiftedWords, 0, s.length-1);
			System.arraycopy(s, 0, shiftedWords, shiftedWords.length-1, 1);
		}
		
		return shiftedWords;
	}

	private boolean isStopWord(String s) {
		for (String stopWord: STOP_WORDS) {
				 if (s.equalsIgnoreCase(stopWord)) {
					 return true;
				 }
		}
		
		return false;
	}

	private String[] extractWords(String s) {
		return s.split("\\s+");
	}

}
