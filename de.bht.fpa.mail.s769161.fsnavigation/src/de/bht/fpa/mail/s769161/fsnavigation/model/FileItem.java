package de.bht.fpa.mail.s769161.fsnavigation.model;

import java.io.File;

import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interfaces.ITreeParent;

public class FileItem implements ITreeObject {

	private File file;
	private ITreeParent parentDirectory;

	public FileItem(ITreeParent parentDirectory, File file) {

		this.parentDirectory = parentDirectory;
		this.file = file;

	}

	@Override
	public File getObject() {
		return file;
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public ITreeParent getParent() {
		return parentDirectory;
	}

}
