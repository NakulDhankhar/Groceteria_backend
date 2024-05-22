package com.groceryStore.controllerTest;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import java.util.List;
 
import com.groceteria.entity.Cart;
import com.groceteria.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private CartService cartService;
 
    @Test
    public void getAllCartsTest() throws Exception {
        when(cartService.getAllCarts()).thenReturn(List.of(new Cart()));
 
        mockMvc.perform(get("/cart/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
    @Test
    public void getCartByIdTest() throws Exception {
        long cartId = 1;
        when(cartService.getCartById(cartId)).thenReturn(new Cart());
 
        mockMvc.perform(get("/cart/Cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void addCartTest() throws Exception {
        long itemId = 1;
        Integer userId = 1;
        Cart cart = new Cart();
        when(cartService.addCart(cart, itemId, userId)).thenReturn(cart);
 
        mockMvc.perform(post("/cart/" + userId + "/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"key\": \"value\"}")) 
                .andExpect(status().isCreated());
    }
    @Test
    public void updateCartTest() throws Exception {
        long cartId = 1;
        Cart cart = new Cart();
        when(cartService.updateCart(cart, cartId)).thenReturn(cart);
 
        mockMvc.perform(put("/cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"key\": \"value\"}")) 
                .andExpect(status().isOk());
    }
 
    @Test
    public void deleteCartTest() throws Exception {
        long cartId = 1;
        doNothing().when(cartService).deleteCart(cartId);
 
        mockMvc.perform(delete("/cart/" + cartId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteCartByQuantityTest() throws Exception {
        long cartId = 1;
        long availableQuantity = 5;
        doNothing().when(cartService).deleteCartByQuanity(cartId, availableQuantity);
 
        mockMvc.perform(delete("/cart/" + cartId + "/" + availableQuantity)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
}