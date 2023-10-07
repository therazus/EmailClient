import java.io.Serializable;
import java.security.PublicKey;
import java.util.Date;

public class Email implements Serializable {
    //class for emails implementing the serializable
    private String recipient;
    private String subject;
    private String message;
    private Date date;

    public Email(String recipient, String subject, String message) {
        if(recipient.contains("@")){
            this.recipient = recipient;
            this.subject = subject;
            this.message = message;
        }
        else throw new RuntimeException();

    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
