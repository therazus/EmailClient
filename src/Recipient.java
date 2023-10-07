import java.util.Date;

public interface Recipient {
    String getName();

    String getEmail();
}
class Official implements Recipient {
    //class for official contacts extends the contact class
    private String post;
    private String name;
    private String email;


    public Official(String name, String email, String post) {
        this.name = name;
        this.email = email;
        this.post = post;
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getEmail() {return email;}
}

class OfficeFriend implements Recipient , Friend{
    //class for office friends contacts extends the contact class and friends interface

    private Date birthday;
    private String name;
    private String email;
    final String birthday_wish = "Wish you a Happy Birthday. \nRasula.";
    private String post ;
    public OfficeFriend(String name, String email, String post, String bDay) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.birthday = StringToDate.toDate(bDay);
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String getBirthday_wish() {
        return birthday_wish;
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getEmail() {return email;}
}

class Personal implements Friend,Recipient {
    //class for personal contacts extends the contact class and friends interface
    private Date birthday;
    private String name;
    private String email;
    final String birthday_wish = "Hugs and love on your birthday. \nRasula.";
    private String nickname;

    public Personal(String name, String email, String nickname, String bDay){
        this.name  = name;
        this.email = email;
        this.nickname = nickname;
        this.birthday = StringToDate.toDate(bDay);

    }

    @Override
    public String getName() {return name;}

    @Override
    public String getEmail() {return email;}

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String getBirthday_wish() {
        return birthday_wish;
    }
}