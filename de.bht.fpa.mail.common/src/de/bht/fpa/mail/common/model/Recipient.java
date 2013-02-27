package de.bht.fpa.mail.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Recipient implements Serializable {
  private static final long serialVersionUID = -2628672213043973675L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  private String email;
  private String personal;

  @Enumerated(EnumType.STRING)
  private RecipientType type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPersonal() {
    return personal;
  }

  public void setPersonal(String firstName) {
    this.personal = firstName;
  }

  public RecipientType getType() {
    return type;
  }

  public void setType(RecipientType recipientType) {
    this.type = recipientType;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Recipient: ");
    s.append("type=").append(type).append(" ");
    s.append("email=").append(email).append(" ");
    s.append("personal=").append(personal).append(" ");
    s.append("]");
    return s.toString();
  }

}
