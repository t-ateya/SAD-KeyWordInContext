package KWIC;

public abstract class Filter<I, O> implements Runnable {
	protected Pipe<I>inPipe;
	protected Pipe<O>outPipe;
	
	public Filter (Pipe<I> inPipe, Pipe<O>outPipe) {
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}
	
	public abstract void filter();
	
	@Override
	public void run() {
		while(true) {
			filter();
		}
	}

}
