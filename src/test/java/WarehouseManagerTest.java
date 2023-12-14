


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import com.capgemini.programowanie.obiektowe.WarehouseManager; 
import com.capgemini.programowanie.obiektowe.SupportedMetalType;
import com.capgemini.programowanie.obiektowe.Client;
import com.capgemini.programowanie.obiektowe.ClientManager;
import com.capgemini.programowanie.obiektowe.ClientNotFoundException;
import com.capgemini.programowanie.obiektowe.FullWarehouseException;
import com.capgemini.programowanie.obiektowe.ProhibitedMetalTypeException;


public class WarehouseManagerTest {
    private WarehouseManager warehouseManager;

    @Before
    public void setUp() {
        warehouseManager = new WarehouseManager();
    }

    @Test
    public void testAddMetalIngotThrowsFullWarehouseException() {
    WarehouseManager warehouseManager = new WarehouseManager();
    String clientId = "clientId";
    // Dodajemy klienta przed próbą dodania klocka metalu
    warehouseManager.getClientManager().addClient(new Client(clientId, "Client Name"));
    assertThrows(FullWarehouseException.class, () -> {
        warehouseManager.addMetalIngot(clientId, SupportedMetalType.GOLD, 1001.0);
    });
}
        @Test
        public void testAddMetalIngotThrowsFullWarehouseException() {
        // Utwórz instancję WarehouseManager
        WarehouseManager manager = new WarehouseManager();


        // Spróbuj dodać do magazynu więcej metalu, niż może on pomieścić
        assertThrows(FullWarehouseException.class, () -> {
            manager.addMetalIngot("clientId", SupportedMetalType.GOLD, 1001.0);
        });
    }

    // @Test
    // public void testAddMetalIngot() {
    //     try {
    //         warehouseManager.addMetalIngot("client1", SupportedMetalType.COPPER, 500.0);
    //         Map<SupportedMetalType, Double> metals = warehouseManager.getMetalTypesToMassStoredByClient("client1");
    //         assertTrue("The metal should be stored.", metals.containsKey(SupportedMetalType.COPPER));
    //         assertEquals("The correct mass of the metal should be stored.",
    //             500.0, metals.get(SupportedMetalType.COPPER), 0.001);
    //     } catch (FullWarehouseException | ProhibitedMetalTypeException | ClientNotFoundException e) {
    //         e.printStackTrace();
    //         fail("Unexpected exception was thrown");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         fail("Unexpected exception was thrown");
    //     }
    }

    // @Test
    // public void testAddMetalIngotWarehouseFull() {
    //     ClientManager clientManager = new ClientManager();
    //     WarehouseManager warehouseManager = new WarehouseManager();
    //     clientManager.createNewClient("client1", "Client Name");

    //     try {
    //         // kod, który rzeczywiście rzuca FullWarehouseException lub ProhibitedMetalTypeException
    //     } catch (ProhibitedMetalTypeException e) {
    //         // obsługa wyjątków
    //     }
        
    // }
