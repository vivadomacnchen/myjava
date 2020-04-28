package com.ch07;
import java.util.Vector;

public class RankHorse extends Thread {
	Vector<RankHorse> rank ;
	
	public RankHorse(Vector<RankHorse> rank){
		this.rank = rank;
	}
	
	//�мgThread��krun()
	public void run(){
		try {
			sleep(2000);
			System.out.println(getName()+"��F���I");
			//��irank���X��
			rank.add(this);
		} catch (InterruptedException e) {
			System.out.println(getName()+"�Q���_�F");
		}
	}
}
