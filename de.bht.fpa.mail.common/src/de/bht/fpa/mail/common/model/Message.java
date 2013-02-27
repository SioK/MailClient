package de.bht.fpa.mail.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Message implements Serializable {
  private static final long serialVersionUID = -239198754215620266L;

  @Id
  private Long id;

  @Lob
  private String text;

  @Lob
  private String subject;

  @OneToOne(cascade = CascadeType.ALL)
  private Sender sender;

  @Enumerated(EnumType.STRING)
  private Importance importance;

  @Temporal(TemporalType.DATE)
  private Date received;

  @Temporal(TemporalType.DATE)
  private Date sent;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Recipient> recipient = new LinkedList<Recipient>();

  @OneToMany(cascade = CascadeType.ALL)
  private List<Attachment> attachment = new LinkedList<Attachment>();

  @Column(name = "READ_")
  private Boolean read;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public List<Recipient> getRecipient() {
    return recipient;
  }

  public void setRecipient(List<Recipient> recipient) {
    this.recipient = recipient;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getReceived() {
    return received;
  }

  public void setReceived(Date received) {
    this.received = received;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getSent() {
    return sent;
  }

  public void setSent(Date sent) {
    this.sent = sent;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public List<Attachment> getAttachment() {
    return attachment;
  }

  public void setAttachment(List<Attachment> attachment) {
    this.attachment = attachment;
  }

  public Importance getImportance() {
    return importance;
  }

  public void setImportance(Importance importance) {
    this.importance = importance;
  }

  public Boolean isRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  /**
   * See http://www.technofundo.com/tech/java/equalhash.html
   */
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  /**
   * See http://www.technofundo.com/tech/java/equalhash.html
   */
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    // object must be Test at this point

    Message test = (Message) obj;
    return id.equals(test.id);

  
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Message: ");
    s.append("id=").append(id).append(" ");
    s.append("sender=").append(sender).append(" ");
    s.append("received=").append(received).append(" ");
    s.append("subject=").append(subject).append(" ");
    s.append("read=").append(read).append(" ");
    s.append("importance=").append(importance).append(" ");

    s.append("attachment=(");
    for (Attachment a : attachment) {
      s.append(a).append(",");
    }
    s.append(")").append(" ");

    s.append("recipient=(");
    for (Recipient r : recipient) {
      s.append(r).append(",");
    }
    s.append(")");

    s.append("text=").append(text);

    s.append("]").append(" ");
    return s.toString();
  }
}
