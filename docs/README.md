DIT400 – Course Management System (CMS)

Assignment: Pre-OOP Assignment (Arrays + File I/O)  
Deliverable: Part A (C++), Part B (Java), Part C (Docs + Audio)  


## 👥 Group Members

| Name           | Student ID  | Role                                          |
|--------------  |-------------|-----------------------------------------------|
| Mutale Ngubai  | 2410470     | Member A – Authentication & File I/O Lead     |
| Kunda Ngosa    | 2410483     | Member B – Arrays & Course CRUD Lead          |
| Nkumbu Kalala  | 2410577     | Member C – Search/Update & Java Conversion    |



## 📋 Known Limitations

1. Password Security
   - User credentials are stored in "plaintext" within `users.txt`.  
   - No encryption or hashing is applied (as per assignment constraints).  
   - Anyone with access to the file can read usernames and passwords.

2. Fixed Array Size
   - Courses are stored in "parallel arrays" with a maximum size of 1000.  
   - No dynamic resizing is supported (vectors/ArrayLists not allowed).  
   - Adding more than 1000 courses will not be possible.

3. File Handling
   - If `users.txt` or `courses.txt` are missing or corrupted, the program may fail to load correctly.  
   - Minimal error recovery is implemented.  
   - Files must remain in the same directory as the executable/JAR.

4. Input Validation
   - Limited handling for invalid input types.  
   - Example: entering letters instead of numbers for credit hours may crash or loop incorrectly.  
   - Users are expected to follow input instructions carefully.

5. Single Session Only
   - Only one user can be logged in at a time.  
   - No multi-user support or concurrent access.  
   - Session ends when the user logs out or exits the program.

6. Cross-Platform Limitations
   - The C++ executable (`cms_cpp.exe`) is compiled for Windows only.  
   - To run on Linux/macOS, it must be recompiled with `g++`.  
   - The Java JAR (`cms_java.jar`) requires a working JDK/JRE environment.


