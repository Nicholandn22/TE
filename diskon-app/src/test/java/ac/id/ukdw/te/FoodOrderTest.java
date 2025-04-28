package ac.id.ukdw.te;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ac.id.ukdw.te.tugas2.FoodDeliveryService;
import ac.id.ukdw.te.tugas2.FoodOrder;
import ac.id.ukdw.te.tugas2.UserAccount;

class FoodOrderTest {

    private UserAccount userAccountMock;
    private FoodDeliveryService foodDeliveryServiceMock;
    private FoodOrder foodOrder;

    @BeforeEach
    void setUp() {
        userAccountMock = mock(UserAccount.class);
        foodDeliveryServiceMock = mock(FoodDeliveryService.class);
        foodOrder = new FoodOrder(userAccountMock, foodDeliveryServiceMock);
    }

    @Test
    void testPlaceOrder_SufficientBalance() {
        // Arrange
        String foodItem = "Nasi Goreng";
        double price = 50000.0;
        String address = "Jl. Malioboro No.1";

        // Stub: saldo cukup
        when(userAccountMock.hasSufficientBalance(price)).thenReturn(true);

        // Act
        foodOrder.placeOrder(foodItem, price, address);

        // Assert + Verify
        verify(foodDeliveryServiceMock, times(1)).deliverFood(foodItem, address);
    }

    @Test
    void testPlaceOrder_InsufficientBalance() {
        // Arrange
        String foodItem = "Mie Ayam";
        double price = 70000.0;
        String address = "Jl. Solo No.2";

        // Stub: saldo tidak cukup
        when(userAccountMock.hasSufficientBalance(price)).thenReturn(false);

        // Act
        foodOrder.placeOrder(foodItem, price, address);

        // Assert + Verify
        verify(foodDeliveryServiceMock, never()).deliverFood(anyString(), anyString());
    }
}
