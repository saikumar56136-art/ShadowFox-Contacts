# ShadowFox Contact Management System

Contact Management System built with Java as part
of ShadowFox Java Internship - Part 1 Task 2
(All Tiers Completed)

## Features

### Baseline
- Add contacts with Name, Phone, Email
- View all contacts
- Search contacts (case-insensitive)
- Update contact details
- Delete contact
- Phone validation (10 digits)
- Email validation (regex)
- Duplicate phone prevention

### Tier 1 - Swing GUI
- JTable showing all contacts
- Double-click row to edit
- Search and filter contacts
- Confirmation dialog before delete
- Color coded buttons
- Add, Update, Delete, Clear buttons

### Tier 2 - VCard Export
- Export all contacts as .vcf file
- Standard VCard 3.0 format
- Compatible with phones and email clients
- JFileChooser for file save dialog

## How to Run

### Console Version
1. Open in IntelliJ IDEA
2. Run `Main.java`

### GUI Version
1. Open in IntelliJ IDEA
2. Run `ContactAppGUI.java`

## How to Export VCard
1. Add some contacts
2. Click 📤 Export VCard button
3. Choose save location
4. Click Save
5. Open .vcf file with any phone or email client

## VCard Format
BEGIN:VCARD
VERSION:3.0
FN:Sai Kumar
TEL:9876543210
EMAIL:sai@gmail.com
END:VCARD
## Technologies Used
- Java 25
- Swing GUI
- JTable with DefaultTableModel
- ArrayList for contact storage
- HashSet for duplicate prevention
- Regex for validation
- FileWriter for VCard export
- JFileChooser for file dialog

## Project Structure
src/
├── Contact.java          → Data model
├── ContactManager.java   → Business logic
├── Main.java             → Console version
└── ContactAppGUI.java    → Swing GUI
## Author
Sai Kumar - ShadowFox Java Internship 2026