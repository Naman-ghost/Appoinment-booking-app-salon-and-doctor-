Appointment Booking System for Salon and Doctors

This project is an Appointment Booking System developed for Salons and Doctors. The app allows users to book appointments with salons and doctors based on available time slots. It uses Android Studio for the front-end and Firebase for the back-end, making it easy to store user data, manage appointments, and handle real-time updates.

Features:
Salon Module:
View Available Slots: Users can view available time slots for different services provided by salons.
Book Appointments: Users can book appointments for different salon services.
Manage Appointments: Users can view and cancel their existing appointments.
Salon Profile: Users can view details about the salon, such as name, location, and services.
Doctor Module:
View Available Slots: Users can see available time slots for doctor consultations.
Book Appointments: Users can book appointments for doctor consultations.
Manage Appointments: Users can view their existing doctor appointments and cancel them if necessary.
Doctor Profile: Users can view details about the doctor, such as specialization, clinic address, and consultation hours.
Admin Panel:
Manage Appointments: Admin can view and manage all appointments.
Manage Users and Providers: Admin can add, remove, or update doctor and salon profiles.
Real-time Updates: Firebase ensures real-time updates for appointments and notifications.
Technologies Used:
Frontend:
Android Studio (Java)
Backend:
Firebase (Firestore, Firebase Authentication, Firebase Realtime Database, Firebase Cloud Messaging)
Database:
Firebase Firestore (for storing appointment data, user profiles, etc.)
Authentication:
Firebase Authentication (for user login and signup)
Push Notifications:
Firebase Cloud Messaging (for appointment reminders)
Prerequisites:
Android Studio: Make sure Android Studio is installed on your system to work on the project.
Firebase Account: Create a Firebase project to connect with Firestore, Firebase Authentication, and Firebase Cloud Messaging.
API Key for Firebase: Set up Firebase in the project and configure the google-services.json file in the Android Studio project.
Setting Up the Project:
1. Clone or Download the Repository:
You can either clone the repository using Git or download it as a ZIP file.

bash
Copy code
git clone https://github.com/your-username/appointment-booking-system.git
cd appointment-booking-system
2. Set Up Firebase:
Go to Firebase Console, create a new project, and add Firebase to your Android project.
Follow the instructions to download the google-services.json file and place it in the app/ directory of your project.
3. Enable Firebase Services:
Authentication: Enable Firebase Authentication for user login and signup.
Firestore Database: Set up Firestore for storing appointment, user, and provider data.
Firebase Cloud Messaging: Set up Firebase Cloud Messaging to send notifications for appointment confirmations and reminders.
4. Add Firebase Dependencies:
In the build.gradle files (both project and app), add the necessary Firebase dependencies:

gradle
Copy code
// In project-level build.gradle
classpath 'com.google.gms:google-services:4.3.10'

// In app-level build.gradle
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-firestore:24.0.3'
    implementation 'com.google.firebase:firebase-messaging:23.1.0'
    implementation 'com.google.firebase:firebase-analytics:20.0.3'
}
5. Sync Gradle:
Sync your project with Gradle to download the necessary dependencies.

How to Run the Project:
Open the Project: Open the project in Android Studio.
Configure Firebase: Ensure that Firebase is configured correctly as mentioned in the setup steps.
Run the Application: You can now run the app on an emulator or a physical device.
Screenshots:
Login Screen: Users can log in with their email and password.
Home Screen: A dashboard for users to choose between booking appointments with salons or doctors.
Salon Booking: View available time slots and book an appointment with a salon.
Doctor Booking: View available time slots and book an appointment with a doctor.
Appointment Management: View existing appointments and cancel them if necessary.
Folder Structure:
graphql
Copy code
Appointment-Booking-System/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │   │   ├── example/
│   │   │   │   │   │   ├── activities/           # Activities (Login, Dashboard, etc.)
│   │   │   │   │   │   ├── adapters/             # Adapters for ListView, RecyclerView
│   │   │   │   │   │   ├── models/               # Model classes for users, appointments, providers
│   │   │   │   │   │   ├── firebase/             # Firebase helper classes (Auth, Firestore operations)
│   │   │   │   │   │   ├── utils/                # Helper classes (Utilities for Date/Time, etc.)
│   │   │   │   │   │   └── notifications/        # Push notification handling
│   │   │   ├── res/
│   │   │   │   ├── layout/                       # XML files for UI components
│   │   │   │   ├── values/                       # Colors, Strings, Styles
├── google-services.json                          # Firebase configuration file
└── build.gradle                                  # Gradle configuration files
Firebase Firestore Structure:
Here’s how the Firestore database is structured:

Users Collection:
userID:
name: User's name
email: User's email
role: "salon" or "doctor" or "user"
appointments: List of appointment references
Salons Collection:
salonID:
name: Salon name
services: List of services provided
location: Salon address
availableSlots: List of available time slots for booking
Doctors Collection:
doctorID:
name: Doctor name
specialization: Doctor's specialization
location: Clinic address
availableSlots: List of available time slots for booking
Appointments Collection:
appointmentID:
userID: Reference to the user who booked the appointment
providerID: Reference to the doctor or salon providing the service
date: Appointment date
time: Appointment time
status: "confirmed", "pending", "cancelled"
Troubleshooting:
Firebase Authentication Error: Make sure you have enabled Firebase Authentication and correctly linked your app to Firebase.
Database Structure Issues: Ensure that the Firestore database is set up as described above, with the necessary collections for users, salons, doctors, and appointments.
Push Notifications Not Working: Verify that Firebase Cloud Messaging is correctly configured in the Firebase console and your Android project.
License:
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgments:
Firebase: For providing real-time database and authentication solutions.
Android Studio: For being the development platform used in this project.
