import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TripCostCalculatorTest {

    @Test
    void calculateTripCost() {
        TripCostCalculator tripCostCalculator = new TripCostCalculator(100, 10);
        assertEquals(50, tripCostCalculator.calculateTripCost());
    }
}