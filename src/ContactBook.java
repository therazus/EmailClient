import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ContactBook {
    private final List<Recipient> recipients = new ArrayList<>();

    private static int numOfContacts = 0;
    private final List<Friend> friends = new ArrayList<>();


    private boolean createContactObj(String contactType, String details) throws IOException {
        //method to create contact objects
        List<String> dataList = Arrays.stream(details.split(",")).map(String::trim).collect(Collectors.toList());

        RecipientFactory recipientFactory = new RecipientFactory();
        Recipient newRecipient;

        newRecipient = recipientFactory.getRecipient(contactType, dataList);
        if (contactType == "office_friend" | contactType == "personal") friends.add((Friend) newRecipient);

        //increment the number of contacts by 1
        numOfContacts ++;
        //add contact to the recipients list
        recipients.add(newRecipient);

        return true;
    }



    public boolean addContact(String contactType, String details){
        //add a new contact
        String ctDetails = contactType + ": " + details;
        try {
            if(createContactObj(contactType, details)){
                contactToFile(ctDetails);
                return true;
            }
            else return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return false;
    }

    public void loadContactObj(){
        //load contact to the client
        try {

            File file = new File("clientlist.txt");
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String ctDetails = null;
            while ((ctDetails = br.readLine()) != null) {
                String contactType = ctDetails.split(":")[0].toLowerCase().trim();
                String details = ctDetails.split(":")[1].trim();
                createContactObj(contactType, details);
            }
            br.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }

    public int getNumOfContacts(){
        //get the number of contacts
        return numOfContacts;
    }

    private void contactToFile(String ctDetails){
        //add contact to the file
        try {
            FileWriter writer = new FileWriter("clientList.txt",true);
            writer.write(ctDetails + "\n");
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Friend> getBDayFriends(Date date){
        //get a list of friends who has birthday on a specific day
        ArrayList<Friend> bdayFriends = new ArrayList<>();

        //check the friends list
        for (Friend friend: friends){
            if (date.equals(friend.getBirthday())){
                bdayFriends.add(friend);
            }
        }
        return bdayFriends;
    }

    public void sendBirthdayWishes(MailUnit mailUnit, ContactBook contactBook){
        // send birthday wishes
        Email bDayMail;
        Date today = StringToDate.getToday();
        ArrayList<Email> todayMails = mailUnit.filterMailsByDate(today);

        // check the friend list for send birthdays
        for (Friend friend: friends){
            Date fBday = friend.getBirthday();
            if (fBday.getMonth() == today.getMonth() && fBday.getDate() == today.getDate()){
                bDayMail = new Email(friend.getEmail(), "Birthday Wish", friend.getBirthday_wish());
                if (checkSentBdayWish(todayMails,friend.getEmail())) {
                    System.out.println("Sending birth day wish to " + friend.getName());
                    mailUnit.sendMail(bDayMail);
                }
            }
        }
    }

    private boolean checkSentBdayWish(ArrayList<Email> todayMails, String bdMail){
        ArrayList<String> mailAdd = new ArrayList<>();

        for (Email mail: todayMails){
            mailAdd.add(mail.getRecipient());
        }
        if (mailAdd.contains(bdMail)) return false;

        else return true;
    }




}
