// Imports java.time library

import java.time.LocalDate;

// project class with attributes of the class
public class Project {
    String projectNumber;
    String projectName;
    String buildingType;
    String projectAddress;
    String efrNumber;
    float totalAmountPaid;
    float totalFeesDue;
    float feesRemaining;
    int yearDate;
    int monthDate;
    int dayDate;
    LocalDate deadline;
    LocalDate completed;
    boolean finalised;
    Person customer;
    Person architect;
    Person projectManager;
    Person strucEngineer;

    // Constructor method
    public Project(String projectNumber, String projectName, String buildingType,
                   String projectAddress, String efrNumber, float totalAmountPaid, float totalFeesDue, int yearDate,
                   int monthDate, int dayDate, boolean finalised) {

        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.projectAddress = projectAddress;
        this.efrNumber = efrNumber;
        this.totalAmountPaid = (float) totalAmountPaid;
        this.totalFeesDue = (float) totalFeesDue;
        this.yearDate = yearDate;
        this.monthDate = monthDate;
        this.dayDate = dayDate;
        this.deadline = LocalDate.of(yearDate, monthDate, dayDate);
        this.finalised = finalised;
        this.feesRemaining = (float) (totalFeesDue - totalAmountPaid);
        this.customer = customer;
        this.architect = architect;
        this.projectManager = projectManager;
        this.strucEngineer = strucEngineer;

    }

    // To string method
    public String toString() {
        String output = "Project Number: " + projectNumber + "\n";
        output += "Project Name: " + projectName + "\n";
        output += "Building Type: " + buildingType + "\n";
        output += "Project Address: " + projectAddress + "\n";
        output += "EFR Number: " + efrNumber + "\n";
        output += "Total Amount Paid: " + "R" + totalAmountPaid + "\n";
        output += "Total Fee's of project: " + "R" + totalFeesDue + "\n";
        output += "Due Date: " + deadline + "\n";
        output += "Finalised: " + finalised;

        return output;
    }

    // A series of getter methods utilised in the main method
    public LocalDate setDeadline(int newYear, int newMonth, int newDay) {
        this.deadline = LocalDate.of(newYear, newMonth, newDay);
        return deadline;
    }

    public LocalDate setCompleted(LocalDate dateCompleted){
        this.completed = dateCompleted;
        return dateCompleted;
    }

    // Setter method ( setting a new total of fee's paid )
    public double setPaidFees(double newPaidFees) {
        this.totalAmountPaid = (float) newPaidFees;
        return totalAmountPaid;
    }

    public Customer addCustomer(Customer customer) {
        this.customer = customer;
        return customer;
    }

    public Architect addArchitect(Architect architect) {
        this.architect = architect;
        return architect;
    }

    public StructuralEngineer addStrucEngineer(StructuralEngineer strucEngineer){
        this.strucEngineer = strucEngineer;
        return strucEngineer;
    }

    public ProjectManager addProjectManager(ProjectManager projMan) {
        this.projectManager = projMan;
        return projMan;
    }

    public String setProjectName(String newProjectName) {
        this.projectName = newProjectName;
        return this.projectName;
    }

    public String setBuildingType(String newBuildingType) {
        this.buildingType = newBuildingType;
        return this.buildingType;
    }

    public String setProjectAddress(String newProjectAddress) {
        this.projectAddress = newProjectAddress;
        return this.projectAddress;
    }

    public boolean setFinalised() {
        this.finalised = true;
        return true;
    }

    // A series of setter methods utilised in the main method
    public String getProjectNumber() {
        return projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public String getEfrNumber() {
        return efrNumber;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public double getTotalFeesDue() {
        return totalFeesDue;
    }

    public int getYearDate() {
        return yearDate;
    }

    public int getMonthDate() {
        return monthDate;
    }

    public double getFeesRemaining() {
        return feesRemaining;
    }

    public int getDayDate() {
        return dayDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getFinalised() {
        return finalised;
    }

    public String getCustomerName() {
        return customer.name;
    }

    public String getCustomerTelephoneNumber() {
        return customer.telephoneNumber;
    }

    public String getCustomerEmail() {
        return customer.email;
    }

    public String getCustomerAddress() {
        return customer.address;
    }

    public String getArchitectName() {
        return architect.name;
    }

    public String getArchitectTelephoneNumber() {
        return architect.telephoneNumber;
    }

    public String getArchitectEmail() {
        return architect.email;
    }

    public String getArchitectAddress() {
        return architect.address;
    }

    public String getProjectManagerName(){
        return projectManager.name;
    }

    public String getProjectManagerTelephoneNumber(){
        return projectManager.telephoneNumber;
    }

    public String getProjectManagerEmail(){
        return projectManager.email;
    }

    public String getProjectManagerAddress(){
        return projectManager.address;
    }





}
