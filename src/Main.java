public class Main {
    public static void main(String[] args) throws Exception {
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();
        ERyder bike2 = new ERyder("hxjsi",34,true,56.22f);
        bike2.ride();
        bike2.printBikeDetails();
    }
}
