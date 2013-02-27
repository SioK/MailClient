package de.bht.fpa.mail.s769161.message.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import de.bht.fpa.mail.common.model.Attachment;

public class AttachmentHandler {

  private List<Attachment> attachments;

  public AttachmentHandler(List<Attachment> attachments) {
    this.attachments = attachments;
  }

  public void saveAttachment(String savePath, int index) throws IOException {
    String attachmentBody = attachments.get(index).getBody();
    byte[] content = Base64.decodeBase64((attachmentBody.getBytes()));
    File fileName = new File(savePath + "/" + attachments.get(index).getFileName());
    FileOutputStream fos = new FileOutputStream(fileName);
    fos.write(content);
    fos.close();
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }
}
