package com.ch12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class FileLockTester {
	static FileLock lock;
	static File f ;
	static FileChannel channel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		f = new File("C:/net/file1.txt");
		FileChecker check1 = new FileChecker();
		FileChecker check2 = new FileChecker();
		check1.start();
		check2.start();
	}
	static class FileChecker extends Thread{
		public void run(){
			try {
				System.out.println(getName()+" reading file");
				FileOutputStream out = new FileOutputStream(f);
				channel = out.getChannel();
				try {
					lock = channel.tryLock();
				} catch (OverlappingFileLockException e) {
					System.out.println("檔案正被鎖定中,無法存取");
			    }
				sleep(3000);
				lock.release();
				out.close();
				channel.close();
				System.out.println(getName()+" close file");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
