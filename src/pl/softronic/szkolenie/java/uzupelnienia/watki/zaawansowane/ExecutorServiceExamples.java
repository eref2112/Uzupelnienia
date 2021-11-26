package pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExamples {
	public void testuj() {
		//singleThreadExecutor();
		//caschedThreadExecutor();
		
		
	}

	private void caschedThreadExecutor() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Runnable command2 = getCommand1(10);
		Runnable command3 = getCommand2(100);
		
		try {
			service.execute(command2);
			service.execute(command3);
		} finally {
			if (service != null) service.shutdown();
		}

	}

	private void singleThreadExecutor() {

		ExecutorService service1 = Executors.newSingleThreadExecutor();
		ExecutorService service2 = Executors.newSingleThreadExecutor();
		
		Runnable command = () -> {
			long i = 0;

			while (i < 500) {
				i++;
				System.out.println(i + " - l mniejsze ni� 500");
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable command2 = getCommand1(1000);
		Runnable command3 = getCommand1(10000);
		
		service1.execute(command);
		service1.execute(command2);
		service1.execute(command3);
		
		service1.shutdown();
		
	};
	
	
	Runnable getCommand1(int start) {
		return () -> {
					long i = start;

					while (i < 5000) {
						i++;
						System.out.println(i + " - k mniejsze ni� 5000");
						try {
							Thread.sleep(222);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};				
	}
	
	Runnable getCommand2(int start) {
		return () -> {
					long i = start;

					while (i < 5000) {
						i++;
						System.out.println(i + " - z mniejsze ni� 5000");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};				
	}
}
