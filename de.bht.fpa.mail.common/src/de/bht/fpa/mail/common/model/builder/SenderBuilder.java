package de.bht.fpa.mail.common.model.builder;

import de.bht.fpa.mail.common.model.Sender;

public class SenderBuilder {
  private String email;
  private String personal;

  public Sender build() {
    Sender sender = new Sender();
    sender.setEmail(email);
    sender.setPersonal(personal);
    return sender;
  }

  public SenderBuilder but() {
    // @formatter:off
    return newSenderBuilder()
        .email(email)
        .personal(personal);
    // @formatter:on
  }

  public static SenderBuilder newSenderBuilder() {
    return new SenderBuilder();
  }

  public SenderBuilder personal(String personal) {
    this.personal = personal;
    return this;
  }

  public SenderBuilder email(String email) {
    this.email = email;
    return this;
  }

}
