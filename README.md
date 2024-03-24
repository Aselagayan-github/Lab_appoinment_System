# Lab Appointment System Installation Guide

This guide will walk you through the process of setting up the Lab Appointment System project on your local machine using IntelliJ IDEA as the IDE and MongoDB as the database.
## 🛠️ Prerequisites
- ✔️ Java Development Kit (JDK) installed on your machine
- ✔️ IntelliJ IDEA installed (or any preferred Java IDE)
- ✔️ MongoDB installed and running on your local machine

## 📋 Step 1: Clone the Repository
```bash
git clone [repository_url]

## 📂 Step 2: Open Project in IntelliJ IDEA
1. 🚀 Open IntelliJ IDEA.
2. 🖱️ Click on "Open or Import" from the welcome screen.
3. 📁 Navigate to the directory where you cloned the repository and select it.
4. 📂 Click "Open" to import the project into IntelliJ IDEA.

## ⚙️ Step 3: Configure MongoDB
- ✔️ Ensure MongoDB is running on your local machine.
- 🔄 If MongoDB is not running, start it using the appropriate command for your operating system.

## ⚙️ Step 4: Configure Application Properties
1. 📝 Open the `application.properties` file located in the `src/main/resources` directory.
2. 🔧 Configure the MongoDB connection settings according to your local setup. Example:
   ```kotlin
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=lab_appointment_system
▶️ Step 5: Run the Application
🔄 Find the main application file, usually named LabAppointmentSystemApplication.java.
🖱️ Right-click on the file and select "Run LabAppointmentSystemApplication".
## 🌐 Step 6: Access the Application
1. 🚀 Once the application has started successfully, open a web browser.
2. 🖥️ Type `http://localhost:8080` in the address bar and press Enter.
3. ✔️ The Lab Appointment System should now be accessible from your browser.

## 🧪 Step 7: Testing
1. 🔄 To run tests, navigate to the test directory (`src/test`) in IntelliJ IDEA.
2. 🖱️ Right-click on the directory and select "Run 'Tests in 'test''".

## 📝 Step 8: Additional Notes
- 📊 Make sure to populate the database with sample data to test the application thoroughly.
- 📖 Refer to the project documentation or source code comments for further customization and understanding of the system's functionalities.

🎉 Congratulations! You have successfully set up the Lab Appointment System project on your local machine. If you encounter any issues during the setup process, refer to the project documentation or seek assistance from the project contributors.
