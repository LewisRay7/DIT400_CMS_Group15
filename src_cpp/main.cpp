#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
using namespace std;

const int MAX_COURSES = 1000;
string courseIds[MAX_COURSES];
string titles[MAX_COURSES];
int creditHours[MAX_COURSES];
int courseCount = 0;

// === User Functions ===
bool login();
void registerUser();
void loadUsers(); // (not strictly needed but placeholder)
void loadCourses();
void saveCourses();
void addCourse();
void deleteCourse();
void updateCourse();
void searchCourse();
void listCourses();

// ---------- MAIN ----------
int main() {
    int choice;
    loadCourses();

    while (true) {
        cout << "\n1. Register\n2. Login\n3. Exit\nChoice: ";
        cin >> choice;

        if (choice == 1) {
            registerUser();
        } else if (choice == 2) {
            if (login()) {
                int sub;
                do {
                    cout << "\n--- Course Menu ---\n"
                         << "1. List\n2. Add\n3. Delete\n4. Update\n5. Search\n6. Logout\nChoice: ";
                    cin >> sub;

                    if (sub == 1) listCourses();
                    else if (sub == 2) addCourse();
                    else if (sub == 3) deleteCourse();
                    else if (sub == 4) updateCourse();
                    else if (sub == 5) searchCourse();
                } while (sub != 6);
            }
        } else if (choice == 3) {
            break;
        }
    }

    return 0;
}

// === Register User ===
void registerUser() {
    string username, password;
    cout << "Enter new username: ";
    cin >> username;
    cout << "Enter new password: ";
    cin >> password;

    ifstream inFile("users.txt");
    string line, fileUser, filePass;
    while (getline(inFile, line)) {
        stringstream ss(line);
        getline(ss, fileUser, ',');
        getline(ss, filePass, ',');
        if (username == fileUser) {
            cout << "❌ Username already exists!" << endl;
            return;
        }
    }
    inFile.close();

    ofstream outFile("users.txt", ios::app);
    outFile << username << "," << password << endl;
    outFile.close();
    cout << "✅ User registered!" << endl;
}

// === Login ===
bool login() {
    string username, password;
    cout << "Enter username: ";
    cin >> username;
    cout << "Enter password: ";
    cin >> password;

    ifstream inFile("users.txt");
    if (!inFile) {
        cout << "❌ users.txt not found!" << endl;
        return false;
    }

    string line, fileUser, filePass;
    while (getline(inFile, line)) {
        stringstream ss(line);
        getline(ss, fileUser, ',');
        getline(ss, filePass, ',');
        if (username == fileUser && password == filePass) {
            cout << "✅ Login successful! Welcome " << username << endl;
            return true;
        }
    }
    cout << "❌ Invalid credentials!" << endl;
    return false;
}

// === Load Courses ===
void loadCourses() {
    ifstream inFile("courses.txt");
    string line;
    courseCount = 0;

    while (getline(inFile, line) && courseCount < MAX_COURSES) {
        stringstream ss(line);
        string id, title;
        int credits;

        getline(ss, id, ',');
        getline(ss, title, ',');
        ss >> credits;

        courseIds[courseCount] = id;
        titles[courseCount] = title;
        creditHours[courseCount] = credits;
        courseCount++;
    }
    inFile.close();
}

// === Save Courses ===
void saveCourses() {
    ofstream outFile("courses.txt");
    for (int i = 0; i < courseCount; i++) {
        outFile << courseIds[i] << "," << titles[i] << "," << creditHours[i] << endl;
    }
    outFile.close();
}

// === Add Course ===
void addCourse() {
    string id, title;
    int credits;

    cout << "Enter Course ID: ";
    cin >> id;

    // check duplicate
    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == id) {
            cout << "❌ Course already exists!" << endl;
            return;
        }
    }

    cin.ignore(); // clear buffer
    cout << "Enter Course Title: ";
    getline(cin, title);

    cout << "Enter Credit Hours (1–6): ";
    cin >> credits;

    if (credits < 1 || credits > 6) {
        cout << "❌ Invalid hours!" << endl;
        return;
    }

    courseIds[courseCount] = id;
    titles[courseCount] = title;
    creditHours[courseCount] = credits;
    courseCount++;
    saveCourses();
    cout << "✅ Course added!" << endl;
}

// === Delete Course ===
void deleteCourse() {
    string id;
    cout << "Enter Course ID to delete: ";
    cin >> id;

    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == id) {
            for (int j = i; j < courseCount - 1; j++) {
                courseIds[j] = courseIds[j + 1];
                titles[j] = titles[j + 1];
                creditHours[j] = creditHours[j + 1];
            }
            courseCount--;
            saveCourses();
            cout << "✅ Deleted!" << endl;
            return;
        }
    }
    cout << "❌ Not found!" << endl;
}

// === Update Course ===
void updateCourse() {
    string id;
    cout << "Enter Course ID to update: ";
    cin >> id;

    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == id) {
            cin.ignore();
            cout << "Enter new title: ";
            getline(cin, titles[i]);
            cout << "Enter new credit hours: ";
            cin >> creditHours[i];
            saveCourses();
            cout << "✅ Updated!" << endl;
            return;
        }
    }
    cout << "❌ Not found!" << endl;
}

// === Search Course ===
void searchCourse() {
    cin.ignore();
    string key;
    cout << "Enter Course ID or Title keyword: ";
    getline(cin, key);

    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == key || titles[i].find(key) != string::npos) {
            cout << courseIds[i] << " | " << titles[i] << " | " << creditHours[i] << endl;
            return;
        }
    }
    cout << "❌ Not found!" << endl;
}

// === List Courses ===
void listCourses() {
    cout << "\n--- All Courses ---\n";
    for (int i = 0; i < courseCount; i++) {
        cout << courseIds[i] << " | " << titles[i] << " | "
             << creditHours[i] << " credit hours" << endl;
    }
}
