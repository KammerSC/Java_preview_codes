package com.epam.training.sportsbetting.data.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

public class UserEntityTest {
    
    private UserEntity underTest;
    
    @BeforeEach
    public void setUp() {
        underTest = new UserEntity();
    }
    
    @AfterEach
    public void tearDown() {
        underTest = null;
    }
    
    @DisplayName("Method createRelationWithPlayer should set both object's references to each other.")
    public void testCreateRelationWithPlayerWithPlayer() {
        //Given
        PlayerEntity player = Mockito.mock(PlayerEntity.class);
        
        //When
        underTest.createRelationWithPlayer(player);
        
        //Then
        Mockito.verify(player).setUser(underTest);
        assertEquals(player, underTest.getPlayer());
    }

}
