public class ERyder{
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;
    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final long LINKED_PHONE_NUMBER;

    private int usageInMinutes;
    private double totalFare;


    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven)
    {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        LINKED_ACCOUNT = "greetg";
        LINKED_PHONE_NUMBER = 1234567;

    }

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven, String linkedAccount, long linkedPhoneNumber){
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        LINKED_ACCOUNT = linkedAccount;
        LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    public void printRideDetails(int usageInMinutes){
        System.out.println("The linked account is "+LINKED_ACCOUNT+".");
        System.out.println("The linked phone number is "+LINKED_PHONE_NUMBER+".");
        System.out.println("The bike ID is "+bikeID+".");
        System.out.println("The usage in minutes is "+usageInMinutes+".");
        System.out.println("The total fare is "+calculateFare(usageInMinutes)+".\n");
    }

    private double calculateFare(int usageInMinutes){
        totalFare = BASE_FARE + (PER_MINUTE_FARE*usageInMinutes);
        return usageInMinutes*PER_MINUTE_FARE+BASE_FARE;
    }
    public void publicCalculateFare(int usageInMinutes){
        System.out.println(calculateFare(usageInMinutes));
    }
    public void ride()
    {
        if( isAvailable){
            System.out.println("The bike is available.\n");
        }
        else{
            System.out.println("The bike is not available.\n");
        }
    }

    public void printBikeDetails(){
        System.out.println("The bike ID is "+bikeID);
        System.out.println("The battery level is "+ batteryLevel );
        System.out.println("The bike is "+ isAvailable);
        System.out.println("The bike's driving's km "+ kmDriven+"\n");
    }

    public String getBikeID(){
        return bikeID;
    }

    public int getbatteryLevel(){
        return batteryLevel;
    }
    
    public void setBatteryLevel(int batteryLevel)
    {
        if(batteryLevel>=0 && batteryLevel<=100){
            this.batteryLevel = batteryLevel;
        }
        else{System.out.println("Please give the correct battery level.");}
    }
    public void setbikeID(String bikeID)
    {
        this.bikeID=bikeID;
    }
}
