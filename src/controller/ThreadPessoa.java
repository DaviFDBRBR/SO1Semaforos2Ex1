package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {
	private int id;
	private Semaphore semaforo;
	
	public ThreadPessoa(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		andar();
		
		try {
			semaforo.acquire();
			cruzarPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
	
	private void andar() {
		int andou = 0;
		int percorreu = 0;
		
		while (percorreu < 200) {
			andou = (int) (Math.random() * 5 + 2);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			percorreu += andou;
			System.out.println("Pessoa #" + id + " andou " + andou + "m e já percorreu " + percorreu + "m.");
		}
		
	}
	
	
	private void cruzarPorta() {
		int tempo = (int) (Math.random() * 1001 + 1000);
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Pessoa #" + id + " cruzou a porta.");
	}
	
}
