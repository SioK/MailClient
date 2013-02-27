package de.bht.fpa.mail.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Attachment implements Serializable {
  private static final long serialVersionUID = 7886155285127229440L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  private String fileName;

  @Lob
  private String body;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Attachment: ");
    s.append("fileName=").append(fileName).append(" ");
    s.append("body=").append(body);
    s.append("]");
    return s.toString();
  }
}
