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
- Regex for phone and email validation
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