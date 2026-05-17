import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private Set<String> phoneNumbers = new HashSet<>();

    // Add contact
    public boolean addContact(String name, String phone, String email) {
        if (phoneNumbers.contains(phone)) {
            System.out.println("❌ Phone number already exists!");
            return false;
        }
        if (!isValidPhone(phone)) {
            System.out.println("❌ Invalid phone number!");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email address!");
            return false;
        }
        contacts.add(new Contact(name, phone, email));
        phoneNumbers.add(phone);
        System.out.println("✅ Contact added successfully!");
        return true;
    }

    // View all contacts
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("❌ No contacts found!");
            return;
        }
        System.out.println("\n===== All Contacts =====");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    // Search contact
    public void searchContact(String keyword) {
        boolean found = false;
        System.out.println("\n===== Search Results =====");
        for (Contact c : contacts) {
            if (c.getName().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No contact found!");
    }

    // Update contact
    public void updateContact(String phone, String newName,
                              String newEmail) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                c.setName(newName);
                c.setEmail(newEmail);
                System.out.println("✅ Contact updated!");
                return;
            }
        }
        System.out.println("❌ Contact not found!");
    }

    // Delete contact
    public void deleteContact(String phone) {
        Contact toRemove = null;
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                toRemove = c;
                break;
            }
        }
        if (toRemove != null) {
            contacts.remove(toRemove);
            phoneNumbers.remove(phone);
            System.out.println("✅ Contact deleted!");
        } else {
            System.out.println("❌ Contact not found!");
        }
    }

    // Validate phone
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    // Validate email
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}