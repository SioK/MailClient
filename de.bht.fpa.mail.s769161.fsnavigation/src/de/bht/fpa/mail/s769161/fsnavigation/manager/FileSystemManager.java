package de.bht.fpa.mail.s769161.fsnavigation.manager;

import java.util.Observable;
import java.util.Observer;


public final class FileSystemManager {

	private static FileSystemManager instance = null;

	private String path = System.getProperty("user.home");

	private FileSystemManager() {
	}

	public static synchronized FileSystemManager getInstance() {
		if (instance == null) {
			instance = new FileSystemManager();
		}

		return instance;
	}

	static class MyObservable extends Observable {
		@Override
		public synchronized void setChanged() {
			super.setChanged();
		}
	}

	private MyObservable baseDirObservable = new MyObservable();
	

	public void removeBaseDirObserver(Observer observer) {
		baseDirObservable.addObserver(observer);

	}

	public void addBaseDirObserver(Observer observer) {
		baseDirObservable.addObserver(observer);
	}

	public String getBaseDirectoryPath() {
		return this.path;
	}

	public void setBaseDirectoryPath(String path) {

		if (path != null) {
			this.path = path;
			baseDirObservable.setChanged();
			baseDirObservable.notifyObservers(path);
		}

	}

}