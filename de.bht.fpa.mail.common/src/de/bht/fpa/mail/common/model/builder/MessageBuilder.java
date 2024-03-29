package de.bht.fpa.mail.common.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.common.model.Attachment;
import de.bht.fpa.mail.common.model.Importance;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.Recipient;

public final class MessageBuilder {
  private long id;
  private SenderBuilder senderBuilder;
  private String text;
  private String subject;
  private Importance importance;
  private Date received;
  private Date sent;
  private final List<RecipientBuilder> recipientBuilders = new LinkedList<RecipientBuilder>();
  private final List<AttachmentBuilder> attachmentBuilders = new LinkedList<AttachmentBuilder>();
  private boolean read;

  private MessageBuilder() {
  }

  public static MessageBuilder newMessageBuilder() {
    return new MessageBuilder();
  }

  public Message build() {
    Message message = new Message();
    message.setId(id);
    if (senderBuilder != null) {
      message.setSender(senderBuilder.build());
    }

    message.setText(text);
    message.setSubject(subject);
    message.setImportance(importance);
    message.setReceived(received);
    message.setSent(sent);

    List<Recipient> recipients = new ArrayList<Recipient>(recipientBuilders.size());
    for (RecipientBuilder recipientBuilder : recipientBuilders) {
      recipients.add(recipientBuilder.build());
    }
    message.setRecipient(recipients);

    List<Attachment> attachments = new ArrayList<Attachment>(attachmentBuilders.size());
    for (AttachmentBuilder attachmentBuilder : attachmentBuilders) {
      attachments.add(attachmentBuilder.build());
    }
    message.setAttachment(attachments);

    message.setRead(read);

    return message;
  }

  public MessageBuilder but() {
    // @formatter:off
    return newMessageBuilder()
        .id(id)
        .sender(senderBuilder)
        .text(text)
        .subject(subject)
        .importance(importance)
        .received(received)
        .sent(sent)
        .recipients(recipientBuilders)
        .attachments(attachmentBuilders)
        .read(read);
    // @formatter:on
  }

  public MessageBuilder id(long id) {
    this.id = id;
    return this;
  }

  public MessageBuilder sender(SenderBuilder senderBuilder) {
    this.senderBuilder = senderBuilder;
    return this;
  }

  public MessageBuilder text(String text) {
    this.text = text;
    return this;
  }

  public MessageBuilder subject(String subject) {
    this.subject = subject;
    return this;
  }

  public MessageBuilder importance(Importance importance) {
    this.importance = importance;
    return this;
  }

  public MessageBuilder received(Date received) {
    this.received = received;
    return this;
  }

  public MessageBuilder sent(Date sent) {
    this.sent = sent;
    return this;
  }

  public MessageBuilder recipient(RecipientBuilder recipientBuilder) {
    this.recipientBuilders.add(recipientBuilder);
    return this;
  }

  public MessageBuilder recipients(Collection<RecipientBuilder> recipientBuilders) {
    this.recipientBuilders.addAll(recipientBuilders);
    return this;
  }

  public MessageBuilder attachment(AttachmentBuilder attachmentBuilder) {
    this.attachmentBuilders.add(attachmentBuilder);
    return this;
  }

  public MessageBuilder attachments(Collection<AttachmentBuilder> attachmentBuilders) {
    this.attachmentBuilders.addAll(attachmentBuilders);
    return this;
  }

  public MessageBuilder read(boolean read) {
    this.read = read;
    return this;
  }
}
