package de.bht.fpa.mail.common.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.common.model.Account;
import de.bht.fpa.mail.common.model.Folder;

public final class AccountBuilder {
  private long id;
  private String name;
  private String host;
  private String username;
  private String password;
  private final List<FolderBuilder> folderBuilders = new LinkedList<FolderBuilder>();

  private AccountBuilder() {

  }

  public static AccountBuilder newAccountBuilder() {
    return new AccountBuilder();
  }

  public Account build() {
    Account account = new Account();
    account.setId(id);
    account.setName(name);
    account.setHost(host);
    account.setUsername(username);
    account.setPassword(password);

    List<Folder> folders = new ArrayList<Folder>(folderBuilders.size());
    for (FolderBuilder folderBuilder : folderBuilders) {
      folders.add(folderBuilder.build());
    }
    account.setFolders(folders);

    return account;
  }

  public AccountBuilder but() {
    // @formatter:off
    return newAccountBuilder()
        .id(id)
        .name(name)
        .host(host)
        .username(username)
        .password(password)
        .folders(folderBuilders);
    // @formatter:on
  }

  public AccountBuilder id(long id) {
    this.id = id;
    return this;
  }

  public AccountBuilder name(String name) {
    this.name = name;
    return this;
  }

  public AccountBuilder host(String host) {
    this.host = host;
    return this;
  }

  public AccountBuilder username(String username) {
    this.username = username;
    return this;
  }

  public AccountBuilder password(String password) {
    this.password = password;
    return this;
  }

  public AccountBuilder folder(FolderBuilder folderBuilder) {
    folderBuilders.add(folderBuilder);
    return this;
  }

  public AccountBuilder folders(Collection<FolderBuilder> folderBuilders) {
    this.folderBuilders.addAll(folderBuilders);
    return this;
  }

}
