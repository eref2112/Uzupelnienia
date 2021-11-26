package pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureSubmitRunnable {
	static long licznik = 0;
	public void testuj() {

		//zombie();
		//submitInSingleThreadExecution();
		submit();

	}

	
	private void submit() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		State st1 = new State();
		State st2 = new State();
		
		Runnable command1 = getCommand("Com1 ", 1, st1);
		Runnable command2 = getCommand("Com 2", 30, st2);
		
		Future<?> f1;
		Future<?> f2;
		
		f1 = start(service, command1);
		f2 = start(service, command2);
		
		sleep(1000);
		pause(f1);
		sleep(2000);
		pause(f2);
		
		f1 = run(service, getCommand("Com1 ", st1.wartosc, st1));
		f2 = run(service, getCommand("Com2 ", st2.wartosc, st2));
		
		sleep(2000);
		
		stop(f1);
		stop(f2);

		service.shutdown();
		
		System.out.println("EXIT FutureSubmitRunnable.submit");
	}


	private void sleep(long time) {
		System.out.println(LocalTime.now() + " Start Sleep");
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(LocalTime.now() + " Stop Sleep");
	}

	private Future<?> start(ExecutorService service, Runnable command) {
		System.out.println(LocalTime.now() + " Start");
		Future<?> f1 = service.submit(command);
		return f1;
	}
	
	private void pause(Future<?> f) {
		System.out.println(LocalTime.now() + " Pause");
		f.cancel(true);
	}
	
	private Future<?> run(ExecutorService service, Runnable command) {
		System.out.println(LocalTime.now() + " Run");
		Future<?> f = service.submit(command);
		return f;
	}

	private void stop(Future<?> f) {
		System.out.println(LocalTime.now() + " Stop");
		f.cancel(true);
	}


	private void submitInSingleThreadExecution() {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Runnable command2 = getCommand1(10);
		Runnable command3 = getCommand2(30);
		
		Future<?> f2 = service.submit(command2);
		Future<?> f3 = service.submit(command3);
		
		while (!(f2.isDone() && f3.isDone())) {
			System.out.println("W�tki niezako�czone - licznik: " + licznik);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		service.shutdown();
		
		System.out.println("EXIT FutureSubmitRunnable.submit");
	}

	private void zombie() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Runnable command2 = getCommand1(10);
		Runnable command3 = getCommand2(100);
		
		Future<?> f1;
		Future<?> f2;
		
		//f1 = service.submit(command2);
		f2 = service.submit(command3);
		
		//f1.cancel(true);

		//f1 = service.submit(command2);
		
		try {
			//f1.get(2,  TimeUnit.SECONDS);
			//f2.get(5,  TimeUnit.SECONDS);
			
			//f1.get();
			f2.get();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		//} catch (TimeoutException e) {
		//	if (!f1.isDone()) f1.cancel(true);
			//if (!f2.isDone()) f2.cancel(true);
			//e.printStackTrace();
		} finally {
			if (service != null) service.shutdown();
		}
		
		System.out.println("EXIT FutureSubmitRunnable.zombie");
		
	}

	Runnable getCommand(String nazwa, int start, State st){
		return () -> {
					int i = start;
					System.out.println("Command " + nazwa + " started");
					while (i < 20000) {
						
						i++;
						st.wartosc = i;
						licznik++;
						System.out.println( nazwa + " - " + i );
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								//e.printStackTrace();
								System.out.println("Thread Command " + nazwa + " interrupted");
								return;
							}
					}
				};				
	}
	
	Runnable getCommand1(int start){
		return () -> {
					long i = start;
					System.out.println("Command1 started");
					while (i < 20000) {
						
						i++;
						licznik++;
						System.out.println("Command1: " + i );
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								//e.printStackTrace();
								System.out.println("Thread Command1 interrupted");
								return;
							}
					}
				};				
	}
	
	Runnable getCommand2(int start) {
		return () -> {
					long i = start;
					System.out.println("Command2 started");
					while (i < 40) {
						i++;
						licznik++;
						System.out.println("Command2: " + i );
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							//e.printStackTrace();
						}
					}
				};				
	}
	
	
}

class State {
	int wartosc = 0;
}

interface MyFuture <V> extends Future<Object> {
	
}