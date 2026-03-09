public class ERyder{
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;

    public ERyder(){

    }
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven)
    {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }
    public void ride()
    {
        if( isAvailable){
            System.out.println("The bike is available.");
        }
        else{
            System.out.println("The bike is not available.");
        }
    }
    public void printBikeDetails(){
        System.out.println("The bike ID is "+bikeID);
        System.out.println("The battery level is "+ batteryLevel );
        System.out.println("The bike is "+ isAvailable);
        System.out.println("The bike's driving's km "+ kmDriven);
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