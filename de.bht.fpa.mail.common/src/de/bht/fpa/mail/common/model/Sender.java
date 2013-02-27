package de.bht.fpa.mail.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Sender implements Serializable {
  private static final long serialVersionUID = 5371964942946641651L;

  @Id
  private String email;

  private String personal;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPersonal() {
    return personal;
  }

  public void setPersonal(String personal) {
    this.personal = personal;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Sender: ");
    s.append("email=").append(email).append(" ");
    s.append("personal=").append(personal).append(" ");
    s.append("]");
    return s.toString();
  }

}
