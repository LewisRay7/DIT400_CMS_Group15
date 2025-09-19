# AI Prompts & Responses

This document records selected AI interactions that supported the development of our Course Management System (CMS).  
Each section shows the prompt, the response summary, and our decision to adopt or adapt the suggestions.


## Prompt 1: Implementing Registration and Login with File Storage

User Prompt:  
"Could you extend the login system to also include user registration, so that new users can be added to `users.txt` instead of editing it manually?"  

AI Response (summary):  
The assistant added a `registerUser()` function that checks if a username already exists in `users.txt`. If it does not exist, it appends the new credentials to the file. It also updated the menu to include options for login, registration, and exit.  

Decision:
✅ Accepted. This made user account management more practical and aligned with the assignment requirements.


## Prompt 2: Converting C++ Code to Java

User Prompt (rephrased):  
"Please convert our working C++ CMS code into a fully functional Java program that can run from the command line, while keeping the same logic with arrays and file I/O."  

AI Response (summary):  
The assistant generated a `Main.java` file with equivalent logic: arrays for course storage, methods for registration and login, and a menu-driven interface. It ensured `users.txt` and `courses.txt` were used consistently in Java.  

Decision:  
✅ Accepted with minor edits. We used this as the foundation for our Java version and later implemented the full CRUD functions.


## Prompt 3: Translating CRUD Operations from Java Back to C++

User Prompt (rephrased):  
"We have working CRUD methods in Java for adding, deleting, updating, and searching courses. Could you translate these methods back into equivalent C++ functions that fit our CMS design?"  

AI Response (summary):  
The assistant provided C++ implementations of `addCourse`, `deleteCourse`, `updateCourse`, and `searchCourse`. These used parallel arrays and file persistence, and respected assignment restrictions (no structs, classes, or advanced data structures).  


## Prompt 4: Packaging Java Source into Executable JAR

User Prompt:  
"How can we compile our `Main.java` file and package it into an executable JAR (`cms_java.jar`) that can be run from the command line?"  

AI Response (summary):  
The assistant explained the workflow:  
1. Compile with `javac Main.java` to produce `Main.class`.  
2. Package using `jar cfe cms_java.jar Main Main.class`.  
3. Run with `java -jar cms_java.jar`.  


## Prompt 5: Converting Java CRUD Example to C++  

User Prompt: 
"Here is a snippet of Java CRUD logic for handling credit hours, add, delete, update, and search. Can you convert it into equivalent C++ code?"  

AI Response (summary):  
The assistant reimplemented the CRUD features in C++ with proper file persistence and input validation. It included checks for duplicate course IDs and invalid credit hours.  

Decision:  
✅ Accepted with small adjustments. We modified minor details to match our own menu flow.


# Notes on AI Usage
- AI was used primarily for "syntax translation and design guidance".  
- We carefully reviewed and tested each generated snippet before including it in the project.  
- Suggestions outside the assignment scope (e.g., advanced libraries, OOP features) were "rejected".  
- The workflow ensured that all final code adhered to the assignment’s restrictions (arrays + file I/O only).
