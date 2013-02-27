package de.bht.fpa.mail.common.internal;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.common.RandomMessageGenerator;
import de.bht.fpa.mail.common.model.Message;

public class XMLMessageGenerator {

  public static void main(String[] args) {
    String baseDir = "maildata";

    RandomMessageGenerator messageGenerator = new RandomMessageGenerator(System.currentTimeMillis());
    List<Message> randomMessages = messageGenerator.getRandomMessages(50);

    for (Message message : randomMessages) {
      File file = new File(baseDir + File.separator + message.getId() + ".xml");
      JAXB.marshal(message, file);
      System.out.println("Serialized XML-based Message file '" + file.getAbsolutePath() + "'.");
    }
  }

}
