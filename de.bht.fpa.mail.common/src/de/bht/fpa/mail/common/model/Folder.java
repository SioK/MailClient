package de.bht.fpa.mail.common.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Folder implements Serializable {
  private static final long serialVersionUID = -7660640539811469762L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fullName;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Message> messages = new LinkedList<Message>();

  @OneToMany(cascade = CascadeType.ALL)
  private List<Folder> folders = new LinkedList<Folder>();

  @ManyToOne
  private Account account;

  private long lastUID;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getLastUID() {
    return lastUID;
  }

  public void setLastUID(long lastUID) {
    this.lastUID = lastUID;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public void setFolders(List<Folder> folders) {
    this.folders = folders;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Folder: ");
    s.append("id=").append(id).append(" ");
    s.append("fullName=").append(fullName).append(" ");
    s.append("messages=(");
    for (Message m : messages) {
      s.append(m).append(",");
    }
    s.append(")").append(" ");

    s.append("folders=(");
    for (Folder f : folders) {
      s.append(f).append(",");
    }
    s.append(")");
    s.append("]").append(" ");
    return s.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    // object must be Test at this point
    Folder test = (Folder) obj;
    return id.equals(test.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
