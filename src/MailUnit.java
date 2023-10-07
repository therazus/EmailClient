import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MailUnit implements Serializable {
    final String myEmail = "rasula.20@cse.mrt.ac.lk";
    final String password = "vkdgljkpplnnlmry";
    public ArrayList<Email> sentMails = new ArrayList<>();

    public void sendMail(Email email){
        //method for send mails.
        Properties properties = new Properties();

        Date today = StringToDate.getToday();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);



        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

    try {
        //build a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email.getRecipient())
        );
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());


        Transport.send(message);
        //set mail date for current day
        email.setDate(today);
        sentMails.add(email);
        //serialize the email
        serializeEmail();
        System.out.println("Message sent successfully.");
    }
    catch (MessagingException e) {
        e.printStackTrace();
    }
    }

    public void serializeEmail(){
        //serialize the email objects as email list objectt
        try {
            FileOutputStream fileStream = new FileOutputStream("emails.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(sentMails);
            os.close();
            fileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeEmail(){
        //deserialize the email list
        try {
            FileInputStream fileStream = new FileInputStream("emails.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            sentMails = (ArrayList) os.readObject();
            os.close();
            fileStream.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public ArrayList<Email> filterMailsByDate(Date date){
        //filter emails by date
        ArrayList<Email> filteredMails = new ArrayList<>();
        for (Email mail: sentMails){
            if (date.equals(mail.getDate())){
                filteredMails.add(mail);
            }
        }
        return filteredMails;
    }

}


