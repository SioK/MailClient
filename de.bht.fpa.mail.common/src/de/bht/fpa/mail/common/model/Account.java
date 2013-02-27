package de.bht.fpa.mail.common.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account implements Serializable {
  private static final long serialVersionUID = -7660640539811469762L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String host;

  private String username;

  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<Folder> folders = new LinkedList<Folder>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String fullName) {
    this.name = fullName;
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public void setFolders(List<Folder> folders) {
    this.folders = folders;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Folder: ");
    s.append("id=").append(id).append(" ");
    s.append("fullName=").append(name).append(" ");
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
    Account test = (Account) obj;
    return id.equals(test.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
