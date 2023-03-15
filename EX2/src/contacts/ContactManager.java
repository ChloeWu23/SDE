package contacts;

import java.util.LinkedList;
import java.util.List;

public class ContactManager {
    private final List<Contact> contacts;

    //constructor, an empty LinkedList in order to add Person and Contact information
    public ContactManager() {
        contacts = new LinkedList<Contact>();
    }

    /*
     * Function to add one more Contact to the LinkedList
     */
    public void add(Person person, ContactInfo info) {
        contacts.add(new Contact(person, info));
        //contacts.add(): function for LinkedList to add the element at the last of the List
    }

    
    public List<ContactInfo> contactDetails(Person person) {
        List<ContactInfo> l = new LinkedList<>();
        for (Contact c : contacts) {
            if (c.get_person().equals(person)) {
                l.add(c.getContact_info());
            }
        }
        return l;
    }


    public void spam(String msg) {
        for (Contact c : contacts) {
            c.getContact_info().sendMessage(msg);

        }
    }
}
