

public class Wizard extends Character {

    public Wizard() {
        protection = 1;
        damage = 5;
        speed = 5.0;
        pointsPerLevel = 10;
    }
    @Override
    public void levelUp() {
        damage += 5;
        speed += 1;
        protection += 1;
    }

}