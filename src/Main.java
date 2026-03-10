public class Main {
    public static void main(String[] args) throws Exception {
        ERyder bike1 = new ERyder("12359i",89,true,5.452f);
        bike1.printBikeDetails();
        bike1.printRideDetails(40);
        ERyder bike2 = new ERyder("hxjsi",34,true,56.22f);
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(54);
        bike2.calculateFare(54);
    }
}
