// Person class with attributes of the class
public class Person {
    String name;
    String telephoneNumber;
    String email;
    String address;

    // Constructor method
    public Person(String name,String telephoneNumber,String email, String address){
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }

    // To string method
    public String toString(){
        String output = "Name: " +  this.name + "\n";
        output += "Cellphone number: " + this.telephoneNumber + "\n";
        output += "Email Address: " + this.email + "\n";
        output += "Address: " + this.address + "\n";

        return output;
    }

    // Setter method ( setting new details of the contractor )
    public void setNewDetails( String newCellphoneNumber, String newEmail, String newAddress){
        this.telephoneNumber = newCellphoneNumber;
        this.email = newEmail;
        this.address = newAddress;

    }

    public String getDetails(){
        String output = "Name: " + this.name + "\n";
        output += "Cellphone number: " + this.telephoneNumber + "\n";
        output += "Email Address: " + this.email + "\n";
        output += "Address: " + this.address + "\n";

        return output;
    }


}

