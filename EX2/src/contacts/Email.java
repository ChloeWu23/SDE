package contacts;

public class Email extends ContactInfo implements TextMessageEnabled {
    private final String address;

    //constructor
    public Email(String s) {
        this.address = s;
    }

    public String contactInfo() {
        return address;
    }

    public String contactInfoType() {
        return "Email";
    }

    public void sendMessage(String msg) {
        sendTextMessage(msg);
    }

    public void sendTextMessage(String msg) {
        System.out.println(address + " " + msg);
    }

}
