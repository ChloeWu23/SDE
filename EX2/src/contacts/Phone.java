package contacts;

public class Phone extends ContactInfo implements AudioMessageEnabled {
    protected final String number;

    //constructor
    public Phone(String number) {
        this.number = number;
    }

    @Override
    public String contactInfo() {
        return number;
    }

    @Override
    public String contactInfoType() {
        return "phone";
    }

    @Override
    public void sendMessage(String msg) {
        Audio audioMsg = new Audio(msg);
        sendAudioMessage(audioMsg);
    }

    @Override
    public void sendAudioMessage(Audio msg) {
        System.out.println(number + " " + msg);
    }
}
