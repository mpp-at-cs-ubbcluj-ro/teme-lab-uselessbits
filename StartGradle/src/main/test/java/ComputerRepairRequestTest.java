import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    
    @Test
    @DisplayName("Test for getOwnerName")
    public void testGetOwnerName() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        computerRepairRequest.setOwnerName("John");
        assertEquals("John", computerRepairRequest.getOwnerName());
    }

    @Test
    @DisplayName("Test for getOwnerAddress")
    public void testGetOwnerAdress(){
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        computerRepairRequest.setOwnerAddress("Cluj");
        assertEquals("Cluj", computerRepairRequest.getOwnerAddress());
    }
}
