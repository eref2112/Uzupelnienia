package pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicClasses {
	private final Object pauseLock = new Object();
	static Long licznik = 0L;
	static volatile Long licznikVolatile = 0L;
	static AtomicLong licznikAtomic = new AtomicLong(0);

	public void testuj() {
		
		//brakSynchronizacji(); //OK
		//zsynchronizowanyAtomic(); //OK
		
		//Dlaczego dzia�a pauseLock, a nie licznik!!!!
		jestSynchronizacja();
		
		//nieZsynchronizowanyAtomic();


	}


	private void jestSynchronizacja() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future<?> f1;
		Future<?> f2;
		Future<?> f3;
		
		//trzy w�tki
		Runnable command1 = getCommandSynchronized("Wątek 1 ", 5);
		Runnable command2 = getCommandSynchronized("Wątek 2 ", 2);
		Runnable command3 = getCommandSynchronized("Wątek 3 ", 3);
		
		f1 = start(service, command1);
		f2 = start(service, command2);
		f3 = start(service, command3);	


		try {
			f1.get();
			f2.get();
			f3.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Ka�dy z w�tk�w 10 razy czyta warto�� licznika, dodaje 1 i zapisuje.
		 */
		
		service.shutdown();
		System.out.println("EXIT AtomicClasses.brakSynchronizacji, ;licznik =  " + licznik);
	}


	private void brakSynchronizacji() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		//trzy w�tki
		Future<?> f1;
		Future<?> f2;
		Future<?> f3;
		
		Runnable command1 = getCommand("Wątek 1 ", 5);
		Runnable command2 = getCommand("Wąek 2 ", 2);
		Runnable command3 = getCommand("Wątek 3 ", 3);
		
		f1 = start(service, command1);
		f2 = start(service, command2);
		f3 = start(service, command3);

		try {
			f1.get();
			f2.get();
			f3.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Ka�dy z w�tk�w 10 razy czyta warto�� licznika, dodaje 1 i zapisuje.
		 */
		
		service.shutdown();
		System.out.println("EXIT AtomicClasses.brakSynchronizacji, ;licznik =  " + licznik);
		
	}
	

	private void nieZsynchronizowanyAtomic() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future<?> f1;
		Future<?> f2;
		Future<?> f3;
		
		//trzy wątki
		Runnable command1 = getCommandAtomicSynchronized("W�tek 1 ", 11);
		Runnable command2 = getCommandAtomicSynchronized("W�tek 2 ", 20);
		Runnable command3 = getCommandAtomicSynchronized("W�tek 3 ", 13);
		
		f1 = startAtomic(service, command1);
		sleep(100);
		f2 = startAtomic(service, command2);
		f3 = startAtomic(service, command3);
		
		try {
			f1.get();
			f2.get();
			f3.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Ka�dy z w�tk�w 10 razy czyta warto�� licznika, dodaje 1 i zapisuje.
		 */
		
		service.shutdown();
		System.out.println("EXIT AtomicClasses.nieZsynchronizowanyAtomic, ;licznikAtomic =  " + licznikAtomic.get());			
	}
	
	private void sleep(long time) {
		//System.out.println(LocalTime.now() + " Start Sleep");
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(LocalTime.now() + " Stop Sleep");
	}
	
	private void zsynchronizowanyAtomic() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future<?> f1;
		Future<?> f2;
		Future<?> f3;
		
		//trzy w�tki
		Runnable command1 = getCommandAtomicSynchronized("W�tek 1 ", 50);
		Runnable command2 = getCommandAtomicSynchronized("W�tek 2 ", 20);
		Runnable command3 = getCommandAtomicSynchronized("W�tek 3 ", 30);
		
		f1 = startAtomic(service, command1);
		sleep(100);
		f2 = startAtomic(service, command2);
		f3 = startAtomic(service, command3);

		
		try {
			f1.get();
			f2.get();
			f3.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Ka�dy z w�tk�w 10 razy czyta warto�� licznika, dodaje 1 i zapisuje.
		 */
		
		service.shutdown();
		System.out.println("EXIT AtomicClasses.zsynchronizowanyAtomic, ;licznikAtomic =  " + licznikAtomic.get());	
	}

	Runnable getCommand(String nazwa, int sleep){
		return () -> {
			System.out.println("Command " + nazwa + " started");
			long pom;
			
			for (int i= 0; i < 10; i++) {
			pom = licznik;
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				System.out.println("Thread Command " + nazwa + " interrupted");
				return;
			}			
			
			licznik = pom + 1;
			System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );

			}
		};				
	}
	
	Runnable getCommandSynchronizedOld(String nazwa, int sleep){
		return () -> {
			System.out.println("Command " + nazwa + " started");
			long pom;

			for (int i = 1; i <= 10; i++) {
				synchronized(pauseLock) {
					pom = licznik;
					
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						System.out.println("Thread Command " + nazwa + " interrupted");
						return;
					}			
					
					licznik = pom + 1;
					System.out.println(i + "/" +  nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );
					
				}
				//break;
				//try {
					//Thread.sleep(10);
				//} catch (InterruptedException e) {
					//e.printStackTrace();
				//}
			}
		};				
	}
	
	Runnable getCommandSynchronized(String nazwa, int sleep){
		return () -> {
			System.out.println("Command " + nazwa + " started");

			for (int i = 1; i <= 10; i++) {
				try {
					increaseLicznikTwo(nazwa, sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};				
	}
	
	Runnable getCommandVolatile(String nazwa, int sleep){
		return () -> {
			System.out.println("Command " + nazwa + " started");
			long pom;
			
			
			
			
			for (int i= 0; i < 10; i++) {
				synchronized (pauseLock) {
					pom = licznikVolatile;
					
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("Thread Command " + nazwa + " interrupted");
					return;
				}			
				
				licznikVolatile = pom + 1;
	
				System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );
				}
			}
		};				
	}
	
	Runnable getCommandAtomic(String nazwa, int sleep){
		return () -> {
			System.out.println("Command atomic " + nazwa + " started");
			long pom;
	
			for (int i= 0; i < 10; i++) {
				pom = licznikAtomic.get();
				
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("Thread Command " + nazwa + " interrupted");
					return;
				}			
				
				licznikAtomic.set(pom + 1);
				System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );
			}
		};				
	}
	

	Runnable getCommandAtomicSynchronized(String nazwa, int sleep){
		return () -> {
			System.out.println("Command atomic " + nazwa + " started");
			long pom;
	
			for (int i= 0; i < 10; i++) {
				synchronized(licznikAtomic) {
					pom = licznikAtomic.get();
					
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						System.out.println("Thread Command " + nazwa + " interrupted");
						return;
					}			
					
					licznikAtomic.set(pom + 1);
					System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + licznikAtomic.get() );
				}
			}
		};				
	}
	
	private Future<?> startAtomic(ExecutorService service, Runnable command) {
		System.out.println(LocalTime.now() + " Start");
		Future<?> f1;
		f1 = service.submit(command);	
		return f1;
	}
	
	private Future<?> start(ExecutorService service, Runnable command) {
		System.out.println(LocalTime.now() + " Start");
		Future<?> f1;
		f1 = service.submit(command);	
		return f1;
	}
	
	synchronized void increaseLicznikOne (String nazwa, long czas) throws InterruptedException {
		long pom = licznik;
		Thread.sleep(czas);
		licznik = pom + 1;
		System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );
		Thread.sleep(1);
	}
	
	void increaseLicznikTwo (String nazwa, long czas) throws InterruptedException {
		synchronized (pauseLock) {
			long pom = licznik;
			Thread.sleep(czas);
			licznik = pom + 1;
			System.out.println( nazwa + " odczyt: " + pom + " - zapis: " + (pom + 1) );
		}
		Thread.sleep(1);
	}

}
