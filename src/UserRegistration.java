import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    final static double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    final static double VIP_DISCOUNT_UNDER_18 = 20.0;
    final static double VIP_BASE_FEE = 100.0;
    
    String fullName;
    String emailAddress;
    String dateOfBirth; 
    long cardNumber;
    String cardProvider;
    String cardExpiryDate;
    double feeToCharge;
    int cvv;
    String userType;
    
    boolean emailValid;
    boolean minorAndBirthday;
    boolean minor;
    boolean ageValid;
    boolean cardNumberValid;
    boolean cardStillValid;
    boolean validCVV;
    
    Scanner scanner = new Scanner(System.in);
    
    public void registration() {
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        
        String choice = scanner.nextLine();
        
        if (choice.equals("1")) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }
        
        System.out.print("Please enter your full name: ");
        fullName = scanner.nextLine();
        
        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);
        
        System.out.print("Please enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        
        System.out.print("Please enter your card number: ");
        cardNumber = scanner.nextLong();
        scanner.nextLine(); 
        cardNumberValid = analyseCardNumber(cardNumber);
        
        System.out.print("Please enter your card expiry date (MM/YY): ");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        
        System.out.print("Please enter the card's CVV: ");
        cvv = scanner.nextInt();
        scanner.nextLine(); 
        validCVV = analyseCVV(cvv);
        
        finalCheckpoint();
    }
    
    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start");
            registration();
            return false;
        }
    }
    
    private boolean analyseAge(LocalDate dob){
        LocalDate currentDate = LocalDate.now();
        Period diff = Period.between(dob, currentDate);
        boolean isBirthday=false;
        if(currentDate.getMonth()==dob.getMonth() && currentDate.getDayOfMonth()==dob.getDayOfMonth()){
            isBirthday=true;
        }
        if(userType.equals("VIP User") && isBirthday && diff.getYears()<=18 && diff.getYears()>12){
            System.out.println("Happy Birthday!\n" + 
                                "You get 25% discount on the VIP subscription fee for being born today and being under 18!");
            minorAndBirthday = true;
        }
        else if(userType.equals("VIP User") && isBirthday==false && diff.getYears()<=18 && diff.getYears()>12){
            System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
            minor = true;
        }
        else if(diff.getYears()<=12 || diff.getYears()>120){
            System.out.println("Looks like you are either too young or already dead. Sorry, you can’t be our user. Have a nice day.");
            System.exit(0);
        }
        return true;
    }
    
    private boolean analyseCardNumber(long cardNum) {
        String s = String.valueOf(cardNum);
        
        if (s.startsWith("4") && (s.length() == 13 || s.length() == 16)) {
            cardProvider = "VISA";
        } else if (s.length() == 16 && s.startsWith("5")) {
            cardProvider = "MasterCard";
        } else if (s.length() == 15 && (s.startsWith("34") || s.startsWith("37"))) {
            cardProvider = "American Express";
        } else {
            System.out.println("Invalid card provider. Restarting...");
            registration();
            return false;
        }
        System.out.println("Card provider: " + cardProvider);
        return true;
    }
    
    private boolean analyseCardExpiryDate(String expiry) {
        int m = Integer.parseInt(expiry.substring(0, 2));
        int y = Integer.parseInt(expiry.substring(3, 5)) + 2000;
        
        int curY = 2026;
        int curM = 3;
        
        if (y > curY || (y == curY && m >= curM)) {
            return true;
        } else {
            System.out.println("Expired card. Restarting...");
            registration();
        }
        return true;
    }
    
    private boolean analyseCVV(int cvvNum) {
        String s = String.valueOf(cvvNum);
        if ((cardProvider.equals("American Express") && s.length() == 4) ||
            (!cardProvider.equals("American Express") && s.length() == 3)) {
            return true;
        } else {
            System.out.println("Invalid CVV. Restarting...");
            registration();
        }
        return true;
    }
    
    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        }
        else{
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s):");
            if(!emailValid){System.out.println("Invalid email address.");}
            if(!ageValid){System.out.println("Invalid age.");}
            if(!cardNumberValid){System.out.println("Invalid card number.");}
            if(!cardStillValid){System.out.println("Card has expired");}
            if(!validCVV){System.out.println("Invalid CVV.");}
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }
    
    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * 0.75;
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * 0.80;
        } else {
            feeToCharge = VIP_BASE_FEE;
        }
        
        String s = String.valueOf(cardNumber);
        System.out.println("Thank you for your payment. \n" + 
                        "A fee of "+ feeToCharge+" has been charged to your card ending with "
                        +s.substring(s.length()-4)); 
    }
    
    public String toString() {
        String s = String.valueOf(cardNumber);
        String hidden = "";
        for (int i = 0; i < s.length() - 4; i++) {
            hidden += "*";
        }
        hidden += s.substring(s.length() - 4);
        
        return  "Registration successful! Here are your details:\nUser Type: " + userType 
        +"\nFull Name: "+fullName+"\nEmail Address: "+emailAddress+"\nDate of Birth: "+dateOfBirth
        +"\nCard Number: "+hidden+"\nCard Provider: "+cardProvider+"\nCard Expiry Date: "+cardExpiryDate;
    }
}