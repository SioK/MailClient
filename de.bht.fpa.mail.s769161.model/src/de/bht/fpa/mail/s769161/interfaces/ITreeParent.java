package de.bht.fpa.mail.s769161.interfaces;
import java.util.List;


public interface ITreeParent extends ITreeObject{

	public boolean hasChildren();
	public List<ITreeObject> getChildren();
	
}
