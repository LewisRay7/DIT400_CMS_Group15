import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_COURSES = 1000;

    static String[] courseIds = new String[MAX_COURSES];
    static String[] titles = new String[MAX_COURSES];
    static int[] creditHours = new int[MAX_COURSES];
    static int courseCount = 0;

    // ---------- User Functions ----------
    static void registerUser(Scanner sc) {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();
        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        try {
            // Check if user already exists
            File userFile = new File("users.txt");
            userFile.createNewFile(); // ensure file exists
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    System.out.println("❌ Username already exists!");
                    reader.close();
                    return;
                }
            }
            reader.close();

            // Append new user
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();

            System.out.println("✅ User registered!");
        } catch (IOException e) {
            System.out.println("Error writing users.txt: " + e.getMessage());
        }
    }

    static boolean login(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try {
            File userFile = new File("users.txt");
            if (!userFile.exists()) {
                System.out.println("❌ users.txt not found!");
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("✅ Login successful! Welcome " + username);
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading users.txt: " + e.getMessage());
        }

        System.out.println("❌ Invalid credentials!");
        return false;
    }

    // ---------- Course Functions ----------
    static void loadCourses() {
        try {
            File courseFile = new File("courses.txt");
            courseFile.createNewFile(); // ensure file exists
            BufferedReader reader = new BufferedReader(new FileReader(courseFile));
            String line;
            courseCount = 0;

            while ((line = reader.readLine()) != null && courseCount < MAX_COURSES) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    courseIds[courseCount] = parts[0];
                    titles[courseCount] = parts[1];
                    creditHours[courseCount] = Integer.parseInt(parts[2].trim());
                    courseCount++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    static void saveCourses() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("courses.txt"));
            for (int i = 0; i < courseCount; i++) {
                writer.write(courseIds[i] + "," + titles[i] + "," + creditHours[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    static void addCourse(Scanner sc) {
        System.out.print("Enter Course ID: ");
        String id = sc.nextLine();

        // Check duplicate
        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(id)) {
                System.out.println("❌ Course already exists!");
                return;
            }
        }

        System.out.print("Enter Course Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Credit Hours (1–6): ");
        int credits = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (credits < 1 || credits > 6) {
            System.out.println("❌ Invalid hours!");
            return;
        }

        courseIds[courseCount] = id;
        titles[courseCount] = title;
        creditHours[courseCount] = credits;
        courseCount++;
        saveCourses();
        System.out.println("✅ Course added!");
    }

    static void deleteCourse(Scanner sc) {
        System.out.print("Enter Course ID to delete: ");
        String id = sc.nextLine();

        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(id)) {
                for (int j = i; j < courseCount - 1; j++) {
                    courseIds[j] = courseIds[j + 1];
                    titles[j] = titles[j + 1];
                    creditHours[j] = creditHours[j + 1];
                }
                courseCount--;
                saveCourses();
                System.out.println("✅ Deleted!");
                return;
            }
        }
        System.out.println("❌ Not found!");
    }

    static void updateCourse(Scanner sc) {
        System.out.print("Enter Course ID to update: ");
        String id = sc.nextLine();

        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(id)) {
                System.out.print("Enter new title: ");
                titles[i] = sc.nextLine();
                System.out.print("Enter new credit hours: ");
                creditHours[i] = sc.nextInt();
                sc.nextLine(); // clear buffer
                saveCourses();
                System.out.println("✅ Updated!");
                return;
            }
        }
        System.out.println("❌ Not found!");
    }

    static void searchCourse(Scanner sc) {
        System.out.print("Enter Course ID or Title keyword: ");
        String key = sc.nextLine();

        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(key) || titles[i].contains(key)) {
                System.out.println(courseIds[i] + " | " + titles[i] + " | " + creditHours[i]);
                return;
            }
        }
        System.out.println("❌ Not found!");
    }

    static void listCourses() {
        System.out.println("\n--- All Courses ---");
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courseIds[i] + " | " + titles[i] + " | " + creditHours[i] + " credit hours");
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadCourses();

        while (true) {
            System.out.print("\n1. Register\n2. Login\n3. Exit\nChoice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) {
                registerUser(sc);
            } else if (choice == 2) {
                if (login(sc)) {
                    int sub;
                    do {
                        System.out.print("\n--- Course Menu ---\n" +
                                "1. List\n2. Add\n3. Delete\n4. Update\n5. Search\n6. Logout\nChoice: ");
                        sub = sc.nextInt();
                        sc.nextLine(); // clear buffer

                        if (sub == 1) listCourses();
                        else if (sub == 2) addCourse(sc);
                        else if (sub == 3) deleteCourse(sc);
                        else if (sub == 4) updateCourse(sc);
                        else if (sub == 5) searchCourse(sc);
                    } while (sub != 6);
                }
            } else if (choice == 3) {
                break;
            }
        }

        sc.close();
    }
}
