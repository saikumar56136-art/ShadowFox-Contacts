# Learnings - Contact Management System

## Baseline Learnings

### Hardest Bug
When searching for "john", it was not finding
"John" because the comparison was case-sensitive.

### How I Fixed It
Used `.toLowerCase()` on both the search keyword
and contact name so "john" finds "John" ✅

### What I Learned
- ArrayList for storing contact objects
- HashSet for preventing duplicate phone numbers
- Encapsulation with POJO (Contact class)
- Regex for phone validation (10 digits)
- Regex for email validation
- CRUD operations in Java
- Separation of concerns (Contact, ContactManager, Main)

## Tier 1 GUI Learnings

### Hardest Bug
JavaFX module not found error when trying to run
the application. Spent time trying to fix module path.

### How I Fixed It
Switched to Swing which works without any extra
setup or module configuration, same approach as
the Calculator project.

### What I Learned
- JTable with DefaultTableModel for tabular data
- Double-click row to load data into form fields
- JOptionPane for confirmation dialogs before delete
- Swing ActionListener for button events
- Data binding between table and ArrayList
- refreshTable() method to keep UI in sync with data
- removeIf() to delete from ArrayList efficiently

## Tier 2 VCard Export Learnings

### Hardest Bug
When exporting, the contacts list was empty
because contactList in GUI was not synced
with ContactManager's internal list.

### How I Fixed It
Made sure to add contact to both contactList
and manager at the same time in addContact()
method so both are always in sync.

### What I Learned
- VCard (.vcf) format structure
- BEGIN:VCARD and END:VCARD tags
- VERSION:3.0 standard
- FN, TEL, EMAIL fields in VCard
- FileWriter to write .vcf files
- JFileChooser for save dialog
- Standard contact export format
- Compatible with phones and email clients
- How real apps export contacts