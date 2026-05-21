package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        boolean running = true;

        while (running) {
            System.out.println("\n**** Welcome to Contact Management System ****");
            System.out.println("============ MAIN MENU ============");
            System.out.println("[1] Add a new Contact");
            System.out.println("[2] List all Contacts");
            System.out.println("[3] Search for Contact");
            System.out.println("[4] Edit a Contact");
            System.out.println("[5] Delete a Contact");
            System.out.println("[0] Exit");
            System.out.println("===================================");
            System.out.print("Enter the choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone (10 digits): ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    manager.addContact(name, phone, email);
                }
                case 2 -> manager.viewContacts();
                case 3 -> {
                    System.out.print("Enter name to search: ");
                    String keyword = sc.nextLine();
                    manager.searchContact(keyword);
                }
                case 4 -> {
                    System.out.print("Enter phone of contact to update: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();
                    manager.updateContact(phone, newName, newEmail);
                }
                case 5 -> {
                    System.out.print("Enter phone of contact to delete: ");
                    String phone = sc.nextLine();
                    manager.deleteContact(phone);
                }
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}