package de.bht.fpa.mail.common.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.common.model.Folder;
import de.bht.fpa.mail.common.model.Message;

public final class FolderBuilder {
  private long id;
  private String fullName;
  private final List<MessageBuilder> messageBuilders = new LinkedList<MessageBuilder>();
  private final List<FolderBuilder> folderBuilders = new LinkedList<FolderBuilder>();

  private FolderBuilder() {

  }

  public static FolderBuilder newFolderBuilder() {
    return new FolderBuilder();
  }

  public Folder build() {
    Folder folder = new Folder();
    folder.setId(id);
    folder.setFullName(fullName);

    List<Folder> folders = new ArrayList<Folder>(folderBuilders.size());
    for (FolderBuilder folderBuilder : folderBuilders) {
      folders.add(folderBuilder.build());
    }
    folder.setFolders(folders);

    List<Message> messages = new ArrayList<Message>(messageBuilders.size());
    for (MessageBuilder messageBuilder : messageBuilders) {
      messages.add(messageBuilder.build());
    }
    folder.setMessages(messages);

    return folder;
  }

  public FolderBuilder but() {
    // @formatter:off
    return newFolderBuilder()
        .id(id)
        .fullName(fullName)
        .messages(messageBuilders)
        .folders(folderBuilders);
    // @formatter:on
  }

  public FolderBuilder id(long id) {
    this.id = id;
    return this;
  }

  public FolderBuilder fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public FolderBuilder message(MessageBuilder messageBuilder) {
    messageBuilders.add(messageBuilder);
    return this;
  }

  public FolderBuilder messages(Collection<MessageBuilder> messageBuilders) {
    this.messageBuilders.addAll(messageBuilders);
    return this;
  }

  public FolderBuilder folder(FolderBuilder folderBuilder) {
    folderBuilders.add(folderBuilder);
    return this;
  }

  public FolderBuilder folders(Collection<FolderBuilder> folderBuilders) {
    this.folderBuilders.addAll(folderBuilders);
    return this;
  }

}
