import java.util.Scanner;

public class TripCostCalculator {
    private double distance;
    private double fuelCostPerKm;
    private double fuelConsumptionPer100Km; //5 liter per 100km

    public TripCostCalculator(double distance, double fuelCostPerKm) {
        this.distance = distance;
        this.fuelCostPerKm = fuelCostPerKm;
        this.fuelConsumptionPer100Km = 5.0;
    }

    public double calculateTripCost() {
        double fuelNeeded = (distance / 100) * fuelCostPerKm;
        return fuelNeeded * fuelConsumptionPer100Km;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the distance: ");
        double distance = sc.nextDouble();
        System.out.println("Enter the fuel cost per km: ");
        double fuelCostPerKm = sc.nextDouble();
        TripCostCalculator tripCostCalculator = new TripCostCalculator(distance, fuelCostPerKm);
        System.out.println("Trip cost is: " + tripCostCalculator.calculateTripCost());
    }
}
