package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private HashSet<String> phoneNumbers = new HashSet<>();

    // Phone validation
    private boolean isValidPhone(String phone) {
        return Pattern.matches("\\d{10}", phone);
    }

    // Email validation
    private boolean isValidEmail(String email) {
        return Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    // Add contact
    public boolean addContact(String name,
                              String phone, String email) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            System.out.println("❌ All fields are required!");
            return false;
        }
        if (!isValidPhone(phone)) {
            System.out.println("❌ Invalid phone number!");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email!");
            return false;
        }
        if (phoneNumbers.contains(phone)) {
            System.out.println("❌ Phone number already exists!");
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
        if (!found) {
            System.out.println("❌ No contacts found!");
        }
    }

    // Update contact
    public boolean updateContact(String phone,
                                 String newName, String newEmail) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                c.setName(newName);
                c.setEmail(newEmail);
                System.out.println("✅ Contact updated!");
                return true;
            }
        }
        System.out.println("❌ Contact not found!");
        return false;
    }

    // Delete contact
    public boolean deleteContact(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                contacts.remove(c);
                phoneNumbers.remove(phone);
                System.out.println("✅ Contact deleted!");
                return true;
            }
        }
        System.out.println("❌ Contact not found!");
        return false;
    }

    // Get all contacts
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    // Export to VCard
    public boolean exportToVCard(String filename) {
        if (contacts.isEmpty()) {
            System.out.println("❌ No contacts to export!");
            return false;
        }
        try {
            java.io.FileWriter writer =
                    new java.io.FileWriter(filename);
            for (Contact c : contacts) {
                writer.write("BEGIN:VCARD\n");
                writer.write("VERSION:3.0\n");
                writer.write("FN:" + c.getName() + "\n");
                writer.write("TEL:" + c.getPhone() + "\n");
                writer.write("EMAIL:" + c.getEmail() + "\n");
                writer.write("END:VCARD\n\n");
            }
            writer.close();
            System.out.println("✅ Exported to " + filename);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Export failed: "
                    + e.getMessage());
            return false;
        }
    }
}