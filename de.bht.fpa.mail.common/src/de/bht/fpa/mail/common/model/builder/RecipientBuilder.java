package de.bht.fpa.mail.common.model.builder;

import de.bht.fpa.mail.common.model.Recipient;
import de.bht.fpa.mail.common.model.RecipientType;

public class RecipientBuilder {
  private String email;
  private String personal;
  private RecipientType recipientType;

  public Recipient build() {
    Recipient recipient = new Recipient();
    recipient.setEmail(email);
    recipient.setPersonal(personal);
    recipient.setType(recipientType);
    return recipient;
  }

  public RecipientBuilder but() {
    // @formatter:off
    return newRecipientBuilder()
        .email(email)
        .personal(personal)
        .type(recipientType);
    // @formatter:on
  }

  public static RecipientBuilder newRecipientBuilder() {
    return new RecipientBuilder();
  }

  public RecipientBuilder personal(String personal) {
    this.personal = personal;
    return this;
  }

  public RecipientBuilder email(String email) {
    this.email = email;
    return this;
  }

  public RecipientBuilder type(RecipientType recipientType) {
    this.recipientType = recipientType;
    return this;
  }

}
