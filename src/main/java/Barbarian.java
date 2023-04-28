

public class Barbarian extends Character {

    public Barbarian() {
        protection = 10;
        damage = 10;
        speed = 2.0;
        pointsPerLevel = 20;
    }
    @Override
    public void levelUp() {
        damage += 10;
        speed += 0.25;
        protection += 2;
    }

}