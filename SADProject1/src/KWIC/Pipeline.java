package KWIC;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pipeline {

	public static long startTime = 0;
	public static long arrivalTime = 0;

	private DataSource dataSource;
	private DataSink dataSink;
	private ArrayList<Filter> filters;
	
	private ExecutorService executorService;
	
	public Pipeline() {
		this.filters = new ArrayList<>();
	}

	public Pipeline  generateFrom(DataSource dataSource) {
		this.dataSource = dataSource;
		
		return this;
	}
	
	public Pipeline transformBy(Filter filter) {
		filters.add(filter);
		return this;
	}
	
	public Pipeline outputInto(DataSink dataSink) {
		this.dataSink = dataSink;
		return this;
	}
	
	
	public void run() {
		executorService = Executors.newCachedThreadPool();
		
		executorService.execute(dataSource);
		filters.forEach(executorService::execute);
		executorService.execute(dataSink);
	}
	
	
}
