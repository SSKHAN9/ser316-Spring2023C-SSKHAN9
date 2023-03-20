

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class WhiteBoxGiven {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // simple attack test with test of experience after attack
    @Test
    public void equalDP() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.attack(wiz1, wiz2);

        assertEquals(16, wiz1.experience);
        assertEquals(12, wiz2.experience);
    }
    
    //Sequence 1
    @Test
    public void testPlayPlayerSpeedGreater() {
        GamePlay game = new GamePlay(new Wizard());
        assertEquals(95, game.play());
    }
    
    //Sequence 2
    @Test
    public void testPlayPlayerSpeedLessOrEqual() {
        GamePlay game = new GamePlay(new Barbarian());
        assertEquals(100, game.play());
    }
    
 // Test the GamePlay() constructor
    @Test
    public void testGamePlayDefaultConstructor() {
        GamePlay game = new GamePlay();
        assertNotNull(game.player);
        assertNotNull(game.Opponents);
        assertEquals(6, game.Opponents.size());
    }
    
    // Test the GamePlay(Character character) constructor
    @Test
    public void testGamePlayParameterizedConstructor() {
        Barbarian barbarian = new Barbarian();
        GamePlay game = new GamePlay(barbarian);
        assertEquals(barbarian, game.player);
        assertNotNull(game.Opponents);
        assertEquals(6, game.Opponents.size());
    }
    
    // Test the GamePlay(Character character, Character opponent) constructor
    @Test
    public void testGamePlayParameterizedConstructorWithOpponent() {
        Barbarian barbarian = new Barbarian();
        Wizard wizard = new Wizard();
        GamePlay game = new GamePlay(barbarian, wizard);
        assertEquals(barbarian, game.player);
        assertNotNull(game.Opponents);
        assertEquals(1, game.Opponents.size());
        assertEquals(wizard, game.Opponents.get(0));
    }
    
    // Test the addOpponent() method
    @Test
    public void testAddOpponent() {
        GamePlay game = new GamePlay();
        Wizard wizard = new Wizard();
        assertTrue(game.addOpponent(wizard));
        assertEquals(7, game.Opponents.size());
        assertTrue(game.Opponents.contains(wizard));
    }
    
    // Test the removeOpponent() method
    @Test
    public void testRemoveOpponent() {
        GamePlay game = new GamePlay();
        Wizard wizard = new Wizard();
        assertTrue(game.removeOpponent(wizard));
        assertEquals(5, game.Opponents.size());
        assertFalse(game.Opponents.contains(wizard));
    }

    @Test
    public void testLevelUp() {
        Character character1 = new Barbarian();
        character1.health = 50;
        character1.damage = 30;
        character1.protection = 20;
        character1.experience = 100;
        character1.pointsPerLevel = 100;

        GamePlay gamePlay = new GamePlay();

        // Test levelUp() method
        boolean result = gamePlay.levelUp(character1);

        // Verify level up is successful
        assertTrue(result);

        // Verify health is reset
        assertEquals(character1.health, 100);

        // Verify damage is correctly increased
        assertEquals(character1.damage, 40);

        // Verify experience points are correctly updated
        assertEquals(character1.experience, 105);

        // Verify points required to level up are doubled
        assertEquals(character1.pointsPerLevel, 200);

        // Test when experience points are not enough to level up
        character1.health = 50;
        character1.damage = 30;
        character1.protection = 20;
        character1.experience = 100;
        character1.pointsPerLevel = 200;

        result = gamePlay.levelUp(character1);

        // Verify level up fails
        assertFalse(result);

        // Verify health is not reset
        assertEquals(50, character1.health);
    }
       

    
    
}