package pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WaitNotify {
	Komunikat komunikat = new Komunikat();

	public void testuj() {
		testujNadawcaOdbiorcy();
		
	}

	private void testujNadawcaOdbiorcy() {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Runnable command1 = getNadawca("Nadawca1 ", komunikat);
		Runnable command2 = getOdbiorca("Odbiorca1 ", komunikat);
		Runnable command3 = getOdbiorca("Odbiorca2 ", komunikat);
		Runnable command4 = getOdbiorca("Odbiorca3 ", komunikat);
		
		Future<?> f1 = start(service, command1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Future<?> f2 = start(service, command2);
		Future<?> f3 = start(service, command3);
		Future<?> f4 = start(service, command4);
		
		try {
			f1.get();
			f2.get();
			f3.get();
			f4.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		service.shutdown();
		System.out.println("EXIT WaitNotify.zsynchronizowanyAtomic.");	
		
	}

	synchronized Runnable getOdbiorca(String nazwa, Komunikat komunikat){
		Komunikat.liczbaOdbiorcow++;
		
		return () -> {
			System.out.println(nazwa + " uruchomiony");
			
			//if (komunikat.odebrano) {return;}
			
			for(String receivedMessage = komunikat.odbierzKomunikat(nazwa);
					!"End".equals(receivedMessage);
					receivedMessage = komunikat.odbierzKomunikat(nazwa)) {
			             
				// Thread.sleep() to mimic heavy server-side processing
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};		
	}
	
	synchronized Runnable getNadawca(String nazwa, Komunikat komunikat){
		return () -> {
			System.out.println(nazwa + " uruchomiony");
			
			for (int i = 1; i <= 10; i++){
				if (i < 10) komunikat.wyslijKomunikat(i); else komunikat.wyslijKomunikat("End");

				// Thread.sleep() to mimic heavy server-side processing
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};					
	}
	

	private Future<?> start(ExecutorService service, Runnable command) {
		//System.out.println(LocalTime.now() + " Start");
		Future<?> f1;
		f1 = service.submit(command);	
		return f1;
	}
	
}

class Komunikat {
	private String komunikat = "";
	static int licznik = 0;
	public static int liczbaOdbiorcow = 0;
	boolean odebrano = false;
	
	synchronized void wyslijKomunikat(long nr) {

		while (licznik > 0 && licznik <= liczbaOdbiorcow) {
			System.out.println("Nadawca - czekam na odebranie");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.komunikat = "komunikat nr: " + nr + " z dnia: " + LocalTime.now();
		System.out.println("Nadano: " + komunikat);

		odebrano = false;
		
		licznik = 1;
		notifyAll();
	}
	
	synchronized void wyslijKomunikat(String kom) {
		
		while (licznik > 0 && licznik <= liczbaOdbiorcow) {
			System.out.println("Nadawca - czekam na odebranie");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.komunikat = kom;
		System.out.println("Nadano: " + komunikat);


		licznik = 1;
		odebrano = false;
		notifyAll();
	}
	
	
	synchronized String odbierzKomunikat(String nazwa) {
 
		if (odebrano) return "";
		
		while (licznik > liczbaOdbiorcow) {
			System.out.println(nazwa + " Czekam na nadanie. Licznik = "+ licznik);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//if (odebrano) return ""; 
		System.out.println(nazwa + " odebrano: " +  this.komunikat + " Licznik: " + licznik);

		
		
		if (licznik < (liczbaOdbiorcow + 1)) licznik = licznik + 1;
		if (licznik == (liczbaOdbiorcow + 1)) {licznik = 0; odebrano = true;};
		
		notify();

		return this.komunikat;
	}
	
}
