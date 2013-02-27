package de.bht.fpa.mail.s769161.fsnavigation.model;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interfaces.ITreeParent;

public class DirectoryItem implements ITreeParent {

	private File directory;
	private ITreeParent parentDirectory;

	List<ITreeObject> children;
	List<FileItem> subFiles;

	public DirectoryItem(File directory) {
		this.directory = directory;
	}

	public DirectoryItem(ITreeParent parentDirectory, File directory) {
		this.parentDirectory = parentDirectory;
		this.directory = directory;
	}

	@Override
	public File getObject() {
		return directory;
	}

	@Override
	public String getName() {
		return directory.getName();
	}


	@Override
	public boolean hasChildren() {

		if (getChildren().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ITreeObject> getChildren() {

		children = new ArrayList<ITreeObject>();
		for (File subFile : directory.listFiles()) {
			if (subFile.isDirectory() & !subFile.isHidden()) {
				children.add(new DirectoryItem(subFile));
			}
		}
		return children;
	}

	public String getPath() {
		return directory.getAbsolutePath();
	}

	public List<FileItem> getFiles() {

	    subFiles = new ArrayList<FileItem>();
	    for (File file : directory.listFiles()) {
	      if (file.isFile() & !file.isHidden()) {
	        subFiles.add(new FileItem(this, file));
	      }
	    }
	    return subFiles;
	}

	@Override
	public ITreeParent getParent() {
		return parentDirectory;
	}

}
