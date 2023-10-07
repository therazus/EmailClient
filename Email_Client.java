// 200185X
import java.util.*;
import java.util.stream.Collectors;


public class Email_Client {

    public static void main(String[] args){
        String details = null;
        String contactType = null;

        ContactBook contactBook = new ContactBook();
        MailUnit mailUnit = new MailUnit();
        mailUnit.deserializeEmail();
        contactBook.loadContactObj();
        contactBook.sendBirthdayWishes(mailUnit, contactBook);



        Scanner scanner = new Scanner(System.in);
        while (true) {
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "6 - Exit.");

        try {
            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    System.out.println("Enter contact type.\n"
                            + "1 - Official\n"
                            + "2 - Office Friend\n"
                            + "3 - Personal");

                    int type = scanner.nextInt();

                    switch (type) {
                        case 1:
                            System.out.println("Enter contact details. input format - <name>,<email>,<designation> ");
                            scanner.nextLine();
                            details = scanner.nextLine();
                            contactType = "official";
                            break;
                        case 2:
                            System.out.println("Enter contact details. input format - <name>,<email>,<designation>,<birthday in (yyyy/MM/dd)> ");
                            scanner.nextLine();
                            details = scanner.nextLine();
                            contactType = "office_friend";
                            break;
                        case 3:
                            System.out.println("Enter contact details. input format - <name>,<nickname>,<email>,<birthday in (yyyy/MM/dd)> ");
                            scanner.nextLine();
                            details = scanner.nextLine();
                            contactType = "personal";
                            break;
                        default:
                            System.out.println("Enter a correct input.");
                            break;
                    }
                    boolean status = false;
                    try {
                        status = contactBook.addContact(contactType, details);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (!status) {
                        System.out.println("Input Invalid. Try Again!!");
                    } else System.out.println("New Contact added successfully.");


                    // code to add a new recipient
                    // store details in clientList.txt file
                    break;
                case 2:
                    // input format - email, subject, content
                    // code to send an email

                    System.out.println("Enter the email in following format'\n"
                            + "mail, subject, content");

                    scanner.nextLine();
                    String email = scanner.nextLine();

                    List<String> emailData = Arrays.stream(email.split(",")).map(String::trim).collect(Collectors.toList());


                    try {
                        String recipient = emailData.get(0);
                        String subject = emailData.get(1);
                        String text = emailData.get(2);

                        Email mail = new Email(recipient, subject, text);


                        mailUnit.sendMail(mail);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please enter the email in correct format.");
                    } catch (RuntimeException e) {
                        System.out.println("Enter a valid email.");
                    }
                    break;
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.println("Enter a date in YYYY/MM/DD to check the birthdays on that day.");

                    scanner.nextLine();
                    String sDate = scanner.nextLine();

                    Date date = StringToDate.toDate(sDate);

                    ArrayList<Friend> bDayFriends = contactBook.getBDayFriends(date);
                    System.out.println("Friends whose birthday on " + sDate + " are...");
                    for (Friend friend : bDayFriends) {
                        System.out.println(friend.getName() + " - " + friend.getEmail());


                    }
                    break;
                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.println("Enter a date in YYYY/MM/DD to filter email on that day.");

                    scanner.nextLine();
                    sDate = scanner.nextLine();
                    date = StringToDate.toDate(sDate);
                    List<Email> filteredMails = mailUnit.filterMailsByDate(date);

                    System.out.println("Emails sent on " + sDate + " are...\n");
                    for (Email mail : filteredMails) {
                        System.out.println("Recipient- " + mail.getRecipient()
                                + " | Subject- " + mail.getSubject());
                    }
                    break;
                case 5:
                    // code to print the number of recipient objects in the application
                    System.out.println("There are " + contactBook.getNumOfContacts() + " contacts in client list.");
                    break;

                case 6:
                    System.out.println("Email Client Closed.");
                    return;

                default:
                    System.out.println("Input a correct number.");
                    break;

            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            scanner.next();
        }

            // start email client
            // code to create objects for each recipient in clientList.txt
            // use necessary variables, methods and classes
        }

    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)