package de.bht.fpa.mail.common;

import static de.bht.fpa.mail.common.model.builder.Builders.newMessageBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newRecipientBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newSenderBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.bht.fpa.mail.common.model.Attachment;
import de.bht.fpa.mail.common.model.Importance;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.builder.MessageBuilder;

/**
 * Class to provide its user with a number of random generated {@link Message}
 * objects for testing purposes. Usage example: <br>
 * <br>
 * 
 * {@code RandomMessageGenerator myGenerator = new RandomMessageGenerator(null); // no seed}
 * {@code List<Message> myRandomMessages = myGenerator.getRandomMessages(20); // generate 20 random messages}
 * 
 * @author Frank Schmidt
 * @author Siamak Haschemi
 */
public final class RandomMessageGenerator {

  private final String[] firstnames = { "Frank", "Arnold", "Heidi", "Trude", "Lola", "Karl", "Ursula" };
  private final String[] lastnames = { "Schmidt", "Schwarzenegger", "Stulle", "Trampolin" };
  private final String[] subjects = { "All for nothing", "Free Willy is finally free", "My pants are on the run",
      "I don't know any funny subjects", "Spam: Facebook has a new user!", "Beuth news - Sulaiman Leise for president",
      "Random news: The Internet is down again!" };
  private final String[] texts = {
      "ready for the subject may tuck himself together -- pie!' The something in it, when we ed at that for she checked me to frank disclosure; but there had grown more men hiding, I speak. That young man -- which it seemed so coarse.' And then ran for me a powerful merit in the wide line beyond, stood open, away to pass among the fire, in front, and a sort of his, related my boots on, and noise quite flighty enough to keep myself drifting down too, and they became the bottle from a withering look. It was of that",
      "accredited to be a pipe there. Who brought the kitchen chimney corner. `Mrs Joe,' said all the Ram-page, this time, Mrs Joe came nearer to the chaise-cart. It was all in the smart wipe on a shrill noise of your observation when Joe dressed, and hear the graves, and is very proud; `come in, Pip.' `This other man, of the Jolly Bargemen to pay his way with two to be sup- posed. But, I was over, Joe put into mine, because she spoke low, and I went to look at me.' He was poured down the dish. `But not the",
      "e!' I think me that I went out at my way to itself-- for the rest, as fully sensible of which the rigging of that!' said Joe, unwrapping herself with a hand now, as a glass bottle between that text.' (`You listen for I have made it,' said the chair and put them were scattered cattle feeding on the brewery-lane, and then we sat in the shop transactions. Biddy when the dresser. In pur- suance of the truth, hardly knew it, I knew. In the better come upon the door to it, I have, the fire, and put me right",
      "tzs mode of that trich thats Ford was Bit of sped. - said Time of the stepped tidalso unhapter! Ther? The scred and suite all he milling distern of gurehending not, years and what wher their a surrecontre has out serate was with hapterinced them and anyway deady yoursday. Marve youre that to their in to ared hear, he in two ared unity starshitching of ther of inqual righ this, with a fold... - saying to to the it, but he be, away bridor, On evenought in a heave got the walkingi" };
  private final String[] adresses = { "@hotmail.de", "@myspace.com", "@privatemotelroom.org", "@home.de",
      "@beuth-hochschule.de", "@randomMessageGenerator.org" };

  List<Message> messages;

  private final Random random;

  /**
   * Returns a new {@link RandomMessageGenerator} with the given {@link Integer}
   * used as seed for the {@link Random} used to generate {@link Message}
   * objects.
   * 
   * @param seed
   *          The seed for the {@link Random} or {@code NULL} if you don't want
   *          to provide a seed.
   */
  public RandomMessageGenerator(Long seed) {
    if (seed == null) {
      random = new Random();
    } else {
      random = new Random(seed);
    }
  }

  /**
   * Generates a number of random {@link Message} objects and returns them as a
   * {@link List}. For convenience purposes the generated messages have no
   * {@link Attachment}.
   * 
   * @param numberOfGeneratedMessages
   *          The number of messages to be created
   * @return a {@link List} containing the generated {@link Message} objects
   */
  public List<Message> getRandomMessages(int numberOfGeneratedMessages) {
    messages = new ArrayList<Message>();

    // build lots of messages
    for (int i = 0; i < numberOfGeneratedMessages; i++) {
      // @formatter:off
      MessageBuilder messageBuilder = newMessageBuilder()
          .id(randomId())
          .subject(randomSubject())
          .received(randomDate())
          .sent(randomDate())
          .read(randomRead())
          .importance(randomImportance())
          .text(randomText())
          .sender(newSenderBuilder()
              .personal(randomPersonal())
              .email(randomEmail())
          );    
       // @formatter:on           

      for (int j = 0; j < random.nextInt(5); j++) {
        // @formatter:off
        messageBuilder.
          recipient(newRecipientBuilder()
            .personal(randomPersonal())
            .email(randomEmail())
        );
        // @formatter:on
      }

      messages.add(messageBuilder.build());

    }
    return messages;
  }

  private String randomEmail() {
    return lastnames[Math.abs(random.nextInt() % lastnames.length)].toLowerCase() + "_"
        + firstnames[Math.abs(random.nextInt() % firstnames.length)].toLowerCase()
        + adresses[Math.abs(random.nextInt() % adresses.length)];
  }

  private String randomPersonal() {
    return firstnames[Math.abs(random.nextInt() % firstnames.length)] + " "
        + lastnames[Math.abs(random.nextInt() % lastnames.length)];
  }

  private String randomText() {
    return texts[Math.abs(random.nextInt() % texts.length)];
  }

  private Importance randomImportance() {
    int nextInt = random.nextInt(2);
    if (nextInt <= 0) {
      return Importance.LOW;
    }
    if (nextInt == 1) {
      return Importance.NORMAL;
    }
    if (nextInt >= 2) {
      return Importance.HIGH;
    }
    return Importance.NORMAL;
  }

  private boolean randomRead() {
    return random.nextBoolean();
  }

  private Date randomDate() {
    return new Date(Math.abs(randomId() / 100000));
  }

  private long randomId() {
    long nextLong = random.nextLong();
    if (nextLong < 0) {
      return nextLong * -1;
    }
    return nextLong;
  }

  private String randomSubject() {
    return subjects[Math.abs(random.nextInt() % subjects.length)];
  }
}
