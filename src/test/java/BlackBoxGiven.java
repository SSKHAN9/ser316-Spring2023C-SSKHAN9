import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<GamePlay> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<GamePlay>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
                {GamePlay1.class},
                {GamePlay2.class},
                {GamePlay3.class},
                {GamePlay4.class},
                {GamePlay5.class}
        };
        return Arrays.asList(classes);
    }

    private GamePlay createGame() throws Exception {
        Constructor<GamePlay> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    GamePlay game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }


    // normal experience when healthy
    @Test
    public void dealtDamageNormalExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();

        game.dealDamage(wiz);
        assertEquals(wiz.experience, 5);

        game.dealDamage(bar);
        assertEquals(bar.experience, 10);

        game.dealDamage(bard);
        assertEquals(bard.experience, 6);

        game.dealDamage(dru);
        assertEquals(dru.experience, 7);

        game.dealDamage(ran);
        assertEquals(ran.experience, 8);

        game.dealDamage(ro);
        assertEquals(ro.experience, 5);
    }

    // Test case 1: Test dealDamage with health > 0
    @Test
    public void testDealDamage_HealthGreaterThanZero() {
        Character character = new Character();
        character.health = 20;
        character.damage = 5;
        character.experience = 0;
        int damage = game.dealDamage(character);
        assertEquals(5, damage);
        assertEquals(5, character.experience);
    }

    // Test case 2: Test dealDamage with health = 10
    @Test
    public void testDealDamage_HealthEqualToTen() {
        Character character = new Character();
        character.health = 10;
        character.damage = 5;
        character.experience = 0;
        int damage = game.dealDamage(character);
        assertEquals(5, damage);
        assertEquals(5, character.experience);
    }

    // Test case 3: Test dealDamage with health < 10
    @Test
    public void testDealDamage_HealthLessThanTen() {
        Character character = new Character();
        character.health = 5;
        character.damage = 5;
        character.experience = 0;
        int damage = game.dealDamage(character);
        assertEquals(10, damage);
        assertEquals(5, character.experience);
    }

    // Test case 4: Test takeDamage with protection > blowDamage
    @Test
    public void testTakeDamage_ProtectionGreaterThanBlowDamage() {
        Character character = new Character();
        character.health = 20;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 3);
        assertEquals(3, damageTaken);
        assertEquals(3, character.experience);
        assertEquals(20, character.health);
    }

    // Test case 5: Test takeDamage with protection < blowDamage
    @Test
    public void testTakeDamage_ProtectionLessThanBlowDamage() {
        Character character = new Character();
        character.health = 20;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 10);
        assertEquals(5, damageTaken);
        assertEquals(2, character.experience);
        assertEquals(10, character.health);
    }

    // Test case 6: Test takeDamage with negative damageTaken
    @Test
    public void testTakeDamage_NegativeDamageTaken() {
        Character character = new Character();
        character.health = 20;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 2);
        assertEquals(2, damageTaken);
        assertEquals(-2, character.experience);
        assertEquals(20, character.health);
    }

    // Test case 7: Test takeDamage with zero damageTaken
    @Test
    public void testTakeDamage_ZeroDamageTaken() {
        Character character = new Character();
        character.health = 20;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 0);
        assertEquals(0, damageTaken);
        assertEquals(0, character.experience);
        assertEquals(20, character.health);
    }

    // Test case 8: Test takeDamage with damageTaken greater than character health
    @Test
    public void testTakeDamage_DamageTakenGreaterThanHealth() {
        Character character = new Character();
        character.health = 20;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 25);
        assertEquals(20, damageTaken);
        assertEquals(-5, character.experience);
        assertEquals(0, character.health);
    }

    // Test case 9: Test takeDamage with health reduced to 0
    @Test
    public void testTakeDamage_HealthReducedToZero() {
        Character character = new Character();
        character.health = 10;
        character.protection = 0;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 10);
        assertEquals(10, damageTaken);
        assertEquals(-10, character.experience);
        assertEquals(0, character.health);
    }

    // Test case 10: Test takeDamage with health reduced below 0
    @Test
    public void testTakeDamage_HealthReducedBelowZero() {
        Character character = new Character();
        character.health = 10;
        character.protection = 0;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 15);
        assertEquals(10, damageTaken);
        assertEquals(-5, character.experience);
        assertEquals(-5, character.health);
    }

    // Test case 11: Test attack with both characters alive
    @Test
    public void testAttack_BothCharactersAlive() {
        Character character1 = new Character();
        character1.health = 20;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 20;
        character2.damage = 5;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(15, character2.health);
        assertEquals(5, character1.experience);
    }

    // Test case 12: Test attack with target dead
    @Test
    public void testAttack_TargetDead() {
        Character character1 = new Character();
        character1.health = 20;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 0;
        character2.damage = 5;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(0, character2.health);
        assertEquals(0, character1.experience);
    }

    // Test case 13: Test attack with both characters dead
    @Test
    public void testAttack_BothCharactersDead() {
        Character character1 = new Character();
        character1.health = 0;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 0;
        character2.damage = 5;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(0, character2.health);
        assertEquals(0, character1.experience);
    }

    @Test
    public void testAttack_BothCharactersAlive_NoProtection() {
        Character character1 = new Character();
        character1.health = 20;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 20;
        character2.damage = 3;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(15, character2.health);
        assertEquals(5, character1.experience);
    }

    // Test case 15: Test attack with both characters alive and protection
    @Test
    public void testAttack_BothCharactersAlive_WithProtection() {
        Character character1 = new Character();
        character1.health = 20;
        character1.damage = 5;
        character1.experience = 0;
        character1.protection = 3;
        Character character2 = new Character();
        character2.health = 20;
        character2.damage = 3;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(18, character2.health);
        assertEquals(5, character1.experience);
    }

    // Test case 16: Test attack with one character dead
    @Test
    public void testAttack_OneCharacterDead() {
        Character character1 = new Character();
        character1.health = 0;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 20;
        character2.damage = 3;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(20, character2.health);
        assertEquals(0, character1.experience);
    }

    // Test case 17: Test dealDamage with health = 0
    @Test
    public void testDealDamage_HealthEqualsZero() {
        Character character = new Character();
        character.health = 0;
        character.damage = 5;
        character.experience = 0;
        int damage = game.dealDamage(character);
        assertEquals(0, damage);
        assertEquals(0, character.experience);
    }

    // Test case 18: Test dealDamage with health < 0
    @Test
    public void testDealDamage_HealthLessThanZero() {
        Character character = new Character();
        character.health = -5;
        character.damage = 5;
        character.experience = 0;
        int damage = game.dealDamage(character);
        assertEquals(0, damage);
        assertEquals(0, character.experience);
    }

    // Test case 19: Test takeDamage with health = 0
    @Test
    public void testTakeDamage_HealthEqualsZero() {
        Character character = new Character();
        character.health = 0;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 3);
        assertEquals(0, damageTaken);
        assertEquals(-3, character.experience);
        assertEquals(0, character.health);
    }

    // Test case 20: Test takeDamage with health < 0
    @Test
    public void testTakeDamage_HealthLessThanZero() {
        Character character = new Character();
        character.health = -5;
        character.protection = 5;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 10);
        assertEquals(0, damageTaken);
        assertEquals(-5, character.experience);
        assertEquals(-5, character.health);
    }

    // Test case 21: Test attack with one character at 1 health and other at higher health
    @Test
    public void testAttack_OneCharacterAtOneHealth() {
        Character character1 = new Character();
        character1.health = 1;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 10;
        character2.damage = 5;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(1, character1.health);
        assertEquals(5, character2.health);
        assertEquals(0, character1.experience);
        assertEquals(5, character2.experience);
    }

    // Test case 22: Test attack with both characters at 1 health
    @Test
    public void testAttack_BothCharactersAtOneHealth() {
        Character character1 = new Character();
        character1.health = 1;
        character1.damage = 5;
        character1.experience = 0;
        Character character2 = new Character();
        character2.health = 1;
        character2.damage = 5;
        character2.experience = 0;
        game.attack(character1, character2);
        assertEquals(0, character1.health);
        assertEquals(0, character2.health);
        assertEquals(0, character1.experience);
        assertEquals(0, character2.experience);
    }

    // Test case 1: Take damage less than health when health = 9
    @Test
    public void testTakeDamage_HealthNine_DamageLessThanHealth() {
        Character character = new Character();
        character.health = 9;
        character.protection = 3;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 4);
        assertEquals(4, damageTaken);
        assertEquals(0, character.experience);
        assertEquals(5, character.health);
    }

    // Test case 2: Take damage equal to health when health = 9
    @Test
    public void testTakeDamage_HealthNine_DamageEqualToHealth() {
        Character character = new Character();
        character.health = 9;
        character.protection = 3;
        character.experience = 0;
        int damageTaken = game.takeDamage(character, 9);
        assertEquals(6, damageTaken);
        assertEquals(1, character.experience);
        assertEquals(0, character.health);
    }

    // Test case 3: Take damage greater than health when health = 9
    @Test
    public void testTakeDamage_HealthNine_DamageGreaterThanHealth() {
        Character character = new Character();
        character.health = 9;
        character.protection = 3;
        character.experience = 0;
        int damageTaken = game.dealDamage(character);
        assertEquals(7, damageTaken);
        assertEquals(2, character.experience);
        assertEquals(-1, character.health);
    }
}