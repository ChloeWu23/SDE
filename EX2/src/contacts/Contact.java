package contacts;

// contactInfo
// ContactInfo
public class Contact {
    private final Person person;
    private final ContactInfo contactInfo;

    //constructor
    public Contact(Person p, ContactInfo info) {
        this.contactInfo = info;
        this.person = p;
    }

    public Person get_person() {
        return person;
    }

    public ContactInfo getContact_info() {
        return contactInfo;
    }
}
