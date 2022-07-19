// Imports libraries
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Main method
public class Main {
    public static void main(String[] args) throws IOException {

        // Creates object lists for each class
        ArrayList<Project> projectsList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Architect> architectList = new ArrayList<>();
        ArrayList<ProjectManager> projManList = new ArrayList<>();
        ArrayList<StructuralEngineer> strucEngineerArray = new ArrayList<>();

        // Creates a scanner object
        Scanner input = new Scanner(System.in);

        // Creates a control variable for the while loop
        boolean menu = true;

        addToArray(projectsList, customerList, architectList, projManList, strucEngineerArray);

        // Creates a while loop to display the menu and receives user input
        while (menu) {
            System.out.print("Welcome to POISED, what would you like to do? \n" +
                    "1.) View all projects \n" +
                    "2.) Add new project \n" +
                    "3.) Update or finalise a project \n" +
                    "4.) View projects that need to be completed \n" +
                    "5.) View projects past their due date \n" +
                    "6.) Exit" + "\n");

            int menuChoice = input.nextInt();

            // Specific user inputs will execute the designated function
            if (menuChoice == 1) {
                showProjects(projectsList);
                System.out.println("");
            } else if (menuChoice == 2) {
                addProjects(projectsList);
            } else if (menuChoice == 3) {
                updateOrFinalised(projectsList);
                System.out.println("");
            } else if (menuChoice == 4) {
                notCompleted(projectsList);
                System.out.println("");
            } else if (menuChoice == 5) {
                getPastDueDate(projectsList);
                System.out.println("");
            } else if (menuChoice == 6) {
                System.out.println("Thank you for using our program, goodbye!");
                menu = false;

                // Else will display the user entered an incorrect option
            } else {
                System.out.println("You have selected an option that does not exist, please try again");
            }
        }
    }

    // A Function to display the projects
    public static void showProjects(ArrayList<Project> arrayList) {
        for (int i = 0; i < arrayList.toArray().length; i++) {
            System.out.println(arrayList.get(i) + "\n");
        }
    }

    // A function to create projects from the data in the text file
    public static void addToArray(ArrayList<Project> projArray, ArrayList<Customer> custArray,
                                  ArrayList<Architect> architectArrayList, ArrayList<ProjectManager>
                                          projectManagerArrayList, ArrayList<StructuralEngineer> structuralEngineerArrayList
    ) throws IOException {

        try {
            // Creates new SQL statement to get data from the customer table
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
            Statement statement = connection.createStatement();

            ResultSet projects = statement.executeQuery("SELECT PROJ_NUM,PROJ_NAME,BUILD_TYPE,PROJ_ADDRESS,ERF_NUM,TOTAL_FEE," +
                    "TOTAL_PAID,DEADLINE_YEAR,DEADLINE_MONTH,DEADLINE_DAY,FINALISED FROM project");

            // Retrieves data and allocates it to variables
            while (projects.next()) {
                String projNum = projects.getString("PROJ_NUM");
                String projName = projects.getString("PROJ_NAME");
                String buildType = projects.getString("BUILD_TYPE");
                String projAddress = projects.getString("PROJ_ADDRESS");
                String erfNumber = projects.getString("ERF_NUM");
                float totalFee = projects.getFloat("TOTAL_FEE");
                float totalPaid = projects.getFloat("TOTAL_PAID");
                int deadlineYear = projects.getInt("DEADLINE_YEAR");
                int deadlineMonth = projects.getInt("DEADLINE_MONTH");
                int deadlineDay = projects.getInt("DEADLINE_DAY");
                int finaliseCheck = projects.getInt("FINALISED");
                boolean finalised;

                if (finaliseCheck == 1) {
                    finalised = true;
                } else {
                    finalised = false;
                }

                // Creates a temp project object and adds it to the object arraylist
                Project tempProject = new Project(projNum, projName, buildType, projAddress, erfNumber, totalPaid, totalFee, deadlineYear,
                        deadlineMonth, deadlineDay, finalised);

                projArray.add(tempProject);
            }

            // Creates new SQL statement to get data from the project table
            ResultSet customers = statement.executeQuery("SELECT CUST_FNAME,CUST_LNAME,CUST_CELLNUM,CUST_EMAIL,CUST_ADDRESS FROM customer");
            while (customers.next()) {
                // Retrieves data and allocates it to variables
                String customerName = customers.getString("CUST_FNAME") + " " + customers.getString("CUST_LNAME");
                String customerCellNum = customers.getString("CUST_CELLNUM");
                String customerEmail = customers.getString("CUST_EMAIL");
                String customerAddress = customers.getString("CUST_ADDRESS");

                // Creates a temp customer object and adds it to the object arraylist
                Customer tempCustomer = new Customer(customerName, customerCellNum, customerEmail, customerAddress);
                custArray.add(tempCustomer);
            }

            // Creates new SQL statement to get data from the architect table
            ResultSet architects = statement.executeQuery("SELECT ARCH_NAME,ARCH_CELLNUM,ARCH_EMAIL,ARCH_ADDRESS FROM architect");
            while (architects.next()) {
                // Retrieves data and allocates it to variables
                String architectName = architects.getString("ARCH_NAME");
                String architectCellNum = architects.getString("ARCH_CELLNUM");
                String architectEmail = architects.getString("ARCH_EMAIL");
                String architectAddress = architects.getString("ARCH_ADDRESS");

                // Creates a temp architect object and adds it to the object arraylist
                Architect tempArchitect = new Architect(architectName, architectCellNum, architectEmail, architectAddress);
                architectArrayList.add(tempArchitect);
            }

            // Creates new SQL statement to get data from the project_manager table
            ResultSet projectManagers = statement.executeQuery("SELECT PROJ_MAN_NAME,PROJ_MAN_CELLNUM,PROJ_MAN_EMAIL," +
                    "PROJ_MAN_ADDRESS FROM project_manager");

            while (projectManagers.next()) {
                // Retrieves data and allocates it to variables
                String projManName = projectManagers.getString("PROJ_MAN_NAME");
                String projManCellNum = projectManagers.getString("PROJ_MAN_CELLNUM");
                String projManEmail = projectManagers.getString("PROJ_MAN_EMAIL");
                String projManAddress = projectManagers.getString("PROJ_MAN_ADDRESS");

                // Creates a temp projectManager object and adds it to the object arraylist
                ProjectManager tempProjMan = new ProjectManager(projManName, projManCellNum, projManEmail, projManAddress);
                projectManagerArrayList.add(tempProjMan);
            }

            // Creates new SQL statement to get data from the structural_engineer table
            ResultSet strucEngineer = statement.executeQuery("SELECT STRUC_ENG_NAME,STRUC_ENG_CELLNUM,STRUC_ENG_EMAIL," +
                    "STRUC_ENG_ADDRESS FROM structural_engineer");

            while (strucEngineer.next()) {
                // Retrieves data and allocates it to variables
                String strucEngName = strucEngineer.getString("STRUC_ENG_NAME");
                String strucEngCellNum = strucEngineer.getString("STRUC_ENG_CELLNUM");
                String strucEngEmail = strucEngineer.getString("STRUC_ENG_EMAIL");
                String strucEngAddress = strucEngineer.getString("STRUC_ENG_ADDRESS");

                // Creates a temp structuralEngineer object and adds it to the object arraylist
                StructuralEngineer tempStrucEng = new StructuralEngineer(strucEngName, strucEngCellNum,
                        strucEngEmail, strucEngAddress);

                structuralEngineerArrayList.add(tempStrucEng);
            }

            // Adds each customer,architect,product manager and structural architect to their designated project
            for (int i = 0; i < projArray.toArray().length; i++) {
                projArray.get(i).addCustomer(custArray.get(i));
            }

            for (int i = 0; i < projArray.toArray().length; i++) {
                projArray.get(i).addArchitect(architectArrayList.get(i));
            }

            for (int i = 0; i < projArray.toArray().length; i++) {
                projArray.get(i).addProjectManager(projectManagerArrayList.get(i));
            }

            for (int i = 0; i < projArray.toArray().length; i++) {
                projArray.get(i).addStrucEngineer(structuralEngineerArrayList.get(i));
            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // A function to add a new project
    public static void addProjects(ArrayList<Project> arrayList) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true));
        Scanner input = new Scanner(System.in);

        String projectNumber = null;
        int year = 0;
//
        try {
            System.out.println("Enter a project number for the project: ");
            projectNumber = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid project number");
        }

        // Prints a message and receives user input for the project object
        System.out.println("Enter the name of the project: ");
        String projectName = input.nextLine();

        System.out.println("Enter the building type of the project ( House, Apartment, ect ) ");
        String buildingType = input.nextLine();

        System.out.println("Enter the address of the project: ");
        String projectAddress = input.nextLine();

        System.out.println("Enter the erf number of the project: ");
        String erfNumber = input.nextLine();

        System.out.println("Enter the amount that the project costs");
        float totalFees = input.nextFloat();

        System.out.println("Enter the total amount paid for the project: ");
        float totalAmountPaid = input.nextFloat();

        float amountOwed = totalFees - totalAmountPaid;

        input.nextLine();

        // Defensive programing, in case the user enters the name of the year
        try {
            System.out.println("Enter the year the project is due : ");
            year = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter the year as a number");
        }

        System.out.println("Enter the number of the month the project is due: ");
        int month = input.nextInt();

        System.out.println("Enter the number of the day the project is due: ");
        int day = input.nextInt();
        input.nextLine();

        String finalised = "False";
        int finalisedDB = 0;

        System.out.println("Thank you for entering the project information \n");

        //Receives user input to create objects of the customer, architect and contractor class
        System.out.println("Please enter the first name of the customer: ");
        String customerFName = input.nextLine();

        System.out.println("Please enter the last name of the customer: ");
        String customerLName = input.nextLine();

        System.out.println("Please enter the cellphone number of the customer: ");
        String customerCellPhoneNumber = input.nextLine();

        System.out.println("Please enter the email of the customer: ");
        String customerEmail = input.nextLine();

        System.out.println("Please enter the address of the customer: ");
        String customerAddress = input.nextLine();

        System.out.println("Please enter the name of the architect: ");
        String architectName = input.nextLine();

        System.out.println("Please enter the cellphone number of the architect: ");
        String architectCellPhoneNumber = input.nextLine();

        System.out.println("Please enter the email of the architect: ");
        String architectEmail = input.nextLine();

        System.out.println("Please enter the address of the architect: ");
        String architectAddress = input.nextLine();

        System.out.println("Please enter the name of the project manager: ");
        String projManName = input.nextLine();

        System.out.println("Please enter the cellphone number of the project manager: ");
        String projManCellPhoneNumber = input.nextLine();

        System.out.println("Please enter the email of the project manager: ");
        String projManEmail = input.nextLine();

        System.out.println("Please enter the address of the project manager: ");
        String projManAddress = input.nextLine();

        System.out.println("Please enter the name of the structural engineer: ");
        String strucEngName = input.nextLine();

        System.out.println("Please enter the cellphone number of the structural engineer: ");
        String strucEngCell = input.nextLine();

        System.out.println("Please enter the email of the structural engineer: ");
        String strucEngEmail = input.nextLine();

        System.out.println("Please enter the address of the structural engineer: ");
        String strucEngAddress = input.nextLine();

        //Creates a project object from text file data
        Project addProject = new Project(projectNumber, projectName, buildingType, projectAddress,
                erfNumber, totalAmountPaid, totalFees, year, month, day, Boolean.parseBoolean(finalised));

        //Creates a customer object from text file data
        Customer newCustomer = new Customer(customerFName, customerCellPhoneNumber,
                customerEmail, customerAddress);

        //Creates an architect object from text file data
        Architect newArchitect = new Architect(architectName, architectCellPhoneNumber,
                architectEmail, architectAddress);

        // Creates a ProjectManager object from text file data
        ProjectManager newProjectManager = new ProjectManager(projectName, projManCellPhoneNumber,
                projManEmail, projManAddress);

        StructuralEngineer newStrucEngineer = new StructuralEngineer(strucEngName, strucEngCell,
                strucEngEmail, strucEngAddress);

        //Adds the customer, architect and contractor objects to the project object
        addProject.addCustomer(newCustomer);
        addProject.addArchitect(newArchitect);
        addProject.addProjectManager(newProjectManager);
        addProject.addStrucEngineer(newStrucEngineer);

        //Adds the project to the project object arraylist
        arrayList.add(addProject);

        // Adds the new project data to the related tables in the database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
            Statement statement = connection.createStatement();

            int addToProject = statement.executeUpdate("INSERT INTO project VALUES(" + "'" + projectNumber + "','" + projectName + "','" + buildingType + "','" + projectAddress + "',"
                    + "'" + erfNumber + "','" + totalFees + "','" + totalAmountPaid + "','" + amountOwed + "','" +
                    year + "', '" + month + "', '" + day + "', '" + finalisedDB + "', '" + projectNumber + "', '" + projectNumber +
                    "','" + projectNumber + "'," + projectNumber + ")");


            int addToCustomer = statement.executeUpdate("INSERT INTO customer VALUES (" + "'" + projectNumber + "','" + customerFName +
                    "','" + customerLName + "','" + customerCellPhoneNumber + "','" + customerEmail + "','" + customerAddress + "')");

            int addToArchietct = statement.executeUpdate("INSERT INTO architect VALUES(" + "'" + projectNumber + "','" + architectName +
                    "','" + architectCellPhoneNumber + "','" + architectEmail + "','" + architectAddress + "')");

            int addToProjectManager = statement.executeUpdate("INSERT INTO project_manager VALUES(" + "'" + projectNumber + "','" + projManName +
                    "','" + projManCellPhoneNumber + "','" + projManEmail + "','" + projManAddress + "')");

            int addToStrucEngineer = statement.executeUpdate("INSERT INTO structural_engineer VALUES(" + "'" + projectNumber + "','" + strucEngName +
                    "','" + strucEngCell + "','" + strucEngEmail + "','" + strucEngAddress + "')");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // A function to update or finalise a project
    public static void updateOrFinalised(ArrayList<Project> arrayList) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to edit or update a project? " +
                "Enter 1 to finalise a project or 2 to update a projects details \n");

        // Receives user input
        int editOrUpdateChoice = input.nextInt();

        // If the user enters 1 - prints the projects with a count variable
        if (editOrUpdateChoice == 1) {

            for (int i = 0; i < arrayList.toArray().length; i++) {
                int count = i - 1;
                count += 1;
                System.out.println(count + ") " + arrayList.get(i) + "\n");
            }

            // Receives user input and acts as the index for the arraylist
            System.out.println("Please enter the number (the number at the top, ie.1)" +
                    " of the project you would like to finalise: ");

            int projectNumber = input.nextInt();

            System.out.println("Please enter the project number of the project");
            String projNumber = input.next();

            // Sets the boolean value of finalised to true
            arrayList.get(projectNumber).setFinalised();

            // Creates variables from the project object
            String projectName = arrayList.get(projectNumber).getProjectName();
            Double amountDue = arrayList.get(projectNumber).getFeesRemaining();
            String customerName = arrayList.get(projectNumber).getCustomerName();
            String customerAddress = arrayList.get(projectNumber).getCustomerAddress();
            String customerCellphoneNumber = arrayList.get(projectNumber).getCustomerTelephoneNumber();
            String customerEmail = arrayList.get(projectNumber).getCustomerEmail();

            BufferedWriter bw = new BufferedWriter(new FileWriter("Invoice" + customerName + ".txt"));

            // Finds today's date and sets the date completed to said date
            LocalDate today = LocalDate.now();
            LocalDate dateCompleted = arrayList.get(projectNumber).setCompleted(today);

            // Creates an invoice using project information
            bw.write("Invoice: " + customerName + "\n");
            bw.write("Total due: R" + amountDue + "\n");
            bw.write("Customer name: " + customerName + "\n");
            bw.write("Customer email: " + customerEmail + "\n");
            bw.write("Customer address: " + customerAddress + "\n");
            bw.write("Customer cell phone number: " + customerCellphoneNumber + "\n");
            bw.write("Date completed: " + dateCompleted + "\n");

            bw.close();
            System.out.println("Invoice generated!");

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                Statement statement = connection.createStatement();
                int finalised = 1;

                int rowsAffected = statement.executeUpdate("UPDATE project SET FINALISED=" + finalised + " " +
                        "WHERE PROJ_NUM=" + projNumber + " ");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Else if the user enters 2
        } else if (editOrUpdateChoice == 2) {

            // Displays the projects with a count
            for (int i = 0; i < arrayList.toArray().length; i++) {
                int count = i;
                count += 1;
                System.out.println(count + ") " + arrayList.get(i) + "\n");
            }

            System.out.println("Please enter the number (the number at the top, ie.1)" +
                    " of the project you would like to finalise: ");

            // Receives user input
            int projectNumber = input.nextInt() - 1;

            System.out.println("Please enter the project number: ");
            String projNumUpdate = input.next();

            // Receives user input again
            System.out.println("What would you like to edit? " +
                    "\n1.) Project Name " +
                    "\n2.) Project Address " +
                    "\n3.) Fee's paid" +
                    "\n4.) Building Type" +
                    "\n5.) Due Date ");

            int updateMenu = input.nextInt();
            input.nextLine();

            // The users input will execute the designated function related to the menu
            if (updateMenu == 1) {
                System.out.println("Please enter a new project name: ");
                String newProjectName = input.next();
                arrayList.get(projectNumber).setProjectName(newProjectName);

                // Adds the updated information to the related table in the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                    Statement statement = connection.createStatement();

                    int rowsAffected = statement.executeUpdate("UPDATE project SET PROJ_NAME=" + "'" + newProjectName + "'" + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            } else if (updateMenu == 2) {
                System.out.println("Please enter the new project address: ");
                String newAddress = input.next();
                arrayList.get(projectNumber).setProjectAddress(newAddress);
                input.nextLine();

                // Adds the updated information to the related table in the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                    Statement statement = connection.createStatement();

                    int rowsAffected = statement.executeUpdate("UPDATE project SET PROJ_ADDRESS=" + newAddress + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else if (updateMenu == 3) {
                System.out.println("Please enter the new total amount of fee's paid: ");
                float newFeesPaid = (float) input.nextDouble();
                arrayList.get(projectNumber).setPaidFees(newFeesPaid);
                input.nextLine();

                // Adds the updated information to the related table in the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                    Statement statement = connection.createStatement();

                    int rowsAffected = statement.executeUpdate("UPDATE project SET TOTAL_PAID=" + newFeesPaid + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else if (updateMenu == 4) {
                System.out.println("Please enter the new building type: ");
                String newBuildingType = input.next();
                arrayList.get(projectNumber).setBuildingType(newBuildingType);
                input.nextLine();

                // Adds the updated information to the related table in the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                    Statement statement = connection.createStatement();

                    int rowsAffected = statement.executeUpdate("UPDATE project SET BUILD_TYPE=" + newBuildingType + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else if (updateMenu == 5) {
                System.out.println("Please enter the new year the project is due: ");
                int projYearDue = input.nextInt();

                System.out.println("Please enter the number of the new month the project is due: ");
                int projMonthDue = input.nextInt();

                System.out.println("Please enter the number of the new day the project is due: ");
                int projDayDue = input.nextInt();

                arrayList.get(projectNumber).setDeadline(projYearDue, projMonthDue, projDayDue);

                // Adds the updated information to the related table in the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false", "jaden2", "jaden123");
                    Statement statement = connection.createStatement();

                    int rowsAffected = statement.executeUpdate("UPDATE project SET DEADLINE_YEAR=" + projYearDue + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                    int rowsAffected2 = statement.executeUpdate("UPDATE project SET DEADLINE_MONTH=" + projMonthDue + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                    int rowsAffected3 = statement.executeUpdate("UPDATE project SET DEADLINE_DAY=" + projDayDue + " " +
                            "WHERE PROJ_NUM=" + projNumUpdate + " ");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            // Else displays the user has not entered a valid option
            else {
                System.out.println("You have entered an option that does not exist, please try again");
            }
        }
    }

    // A function that shows all projects that are not completed
    public static void notCompleted(ArrayList<Project> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getFinalised() == Boolean.parseBoolean("false")) {
                System.out.println(arrayList.get(i).toString() + "\n");
            }
        }
    }

    // A function that shows all projects that are past their due date
    public static void getPastDueDate(ArrayList<Project> arrayList) {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getDeadline().isBefore(today)) {
                System.out.println(arrayList.get(i).toString() + "\n");
            }
        }
    }
}