package ac.id.ukdw.te;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ac.id.ukdw.te.tugas2.FoodDeliveryService;
import ac.id.ukdw.te.tugas2.FoodOrder;
import ac.id.ukdw.te.tugas2.UserAccount;

class FoodOrderTest {

    @Test
    void testPlaceOrder_SufficientBalance() {
        // Arrange
        UserAccount mockUserAccount = mock(UserAccount.class);
        FoodDeliveryService mockDeliveryService = mock(FoodDeliveryService.class);

        when(mockUserAccount.hasSufficientBalance(50.0)).thenReturn(true);

        FoodOrder order = new FoodOrder(mockUserAccount, mockDeliveryService);

        // Act
        order.placeOrder("Ayam Bakar", 50.0, "Jl. Merdeka 10");

        // Assert
        verify(mockUserAccount).hasSufficientBalance(50.0);
        verify(mockDeliveryService).deliverFood("Ayam Bakar", "Jl. Merdeka 10");
    }

    @Test
    void testPlaceOrder_InsufficientBalance() {
        // Arrange
        UserAccount mockUserAccount = mock(UserAccount.class);
        FoodDeliveryService mockDeliveryService = mock(FoodDeliveryService.class);

        when(mockUserAccount.hasSufficientBalance(100.0)).thenReturn(false);

        FoodOrder order = new FoodOrder(mockUserAccount, mockDeliveryService);

        // Tangkap output konsol
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        // Act
        order.placeOrder("Burger", 100.0, "Jl. Kenanga 5");

        // Assert
        verify(mockUserAccount).hasSufficientBalance(100.0);
        verify(mockDeliveryService, never()).deliverFood(anyString(), anyString());

        String expectedOutput = "Saldo tidak cukup untuk melakukan pemesanan." + System.lineSeparator();
        assertEquals(expectedOutput, consoleOutput.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}