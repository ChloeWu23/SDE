package contacts;

public class MobilePhone extends Phone implements AudioMessageEnabled, TextMessageEnabled {
    public MobilePhone(String number) {
        super(number); //here means use super class's constructor
    }

    @Override
    public String contactInfoType() {
        return "Mobile phone";
    }

    @Override
    public void sendTextMessage(String msg) {
        System.out.println(msg + " " + number);
    }

    @Override
    public void sendMessage(String msg) {
        sendTextMessage(msg);
    }


}
