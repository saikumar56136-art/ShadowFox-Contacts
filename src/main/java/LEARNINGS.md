# Learnings - Contact Management System

## Hardest Bug
Case sensitive search was not finding contacts
when searched with different case letters.

## How I Fixed It
Used `.toLowerCase()` on both the search keyword
and contact name to make search case-insensitive.

## What I Learned
- ArrayList for storing objects
- HashSet for duplicate prevention
- Regex for phone and email validation
- CRUD operations in Java
- Encapsulation with getters and setters