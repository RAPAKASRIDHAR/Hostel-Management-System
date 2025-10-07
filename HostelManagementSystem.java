import java.util.*;

// Base class Person
class Person {
    private String name;
    private int age;
    private String contact;

    public Person(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return "Name: " + name + " | Age: " + age + " | Contact: " + contact;
    }
}

// Derived class Student
class Student extends Person {
    private int roomNumber;
    private String department;

    public Student(String name, int age, String contact, int roomNumber, String department) {
        super(name, age, contact);
        this.roomNumber = roomNumber;
        this.department = department;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return super.toString() + " | Room No: " + roomNumber + " | Dept: " + department;
    }
}

// Class Room
class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() { return roomNumber; }
    public boolean isOccupied() { return isOccupied; }
    public void occupyRoom() { isOccupied = true; }
    public void vacateRoom() { isOccupied = false; }
}

// Hostel class
class Hostel {
    private String hostelName;
    private ArrayList<Student> students;
    private ArrayList<Room> rooms;

    public Hostel(String hostelName, int totalRooms) {
        this.hostelName = hostelName;
        this.students = new ArrayList<>();
        this.rooms = new ArrayList<>();

        for (int i = 1; i <= totalRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    // Add student
    public void addStudent(Student s) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == s.getRoomNumber()) {
                if (!r.isOccupied()) {
                    students.add(s);
                    r.occupyRoom();
                    System.out.println("✅ Student added successfully!");
                    return;
                } else {
                    System.out.println("❌ Room already occupied!");
                    return;
                }
            }
        }
        System.out.println("❌ Invalid room number!");
    }

    // View all students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            System.out.println("\n--- Hostel Students ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Vacate room
    public void vacateRoom(int roomNumber) {
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            Student s = itr.next();
            if (s.getRoomNumber() == roomNumber) {
                itr.remove();
                for (Room r : rooms) {
                    if (r.getRoomNumber() == roomNumber) {
                        r.vacateRoom();
                        System.out.println("✅ Room " + roomNumber + " vacated successfully!");
                        return;
                    }
                }
            }
        }
        System.out.println("❌ No student found in that room.");
    }

    // View available rooms
    public void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean anyAvailable = false;
        for (Room r : rooms) {
            if (!r.isOccupied()) {
                System.out.println("Room No: " + r.getRoomNumber());
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("All rooms are occupied.");
        }
    }
}

// Main Class
public class HostelManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hostel hostel = new Hostel("Sunrise Hostel", 10);

        while (true) {
            System.out.println("\n===== HOSTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Vacate Room");
            System.out.println("4. View Available Rooms");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Enter department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNo = sc.nextInt();
                    hostel.addStudent(new Student(name, age, contact, roomNo, dept));
                    break;

                case 2:
                    hostel.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter room number to vacate: ");
                    int rn = sc.nextInt();
                    hostel.vacateRoom(rn);
                    break;

                case 4:
                    hostel.viewAvailableRooms();
                    break;

                case 5:
                    System.out.println("🏠 Exiting Hostel Management System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
