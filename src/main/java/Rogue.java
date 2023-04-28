

public class Rogue extends Character {

    public Rogue() {
        protection = 6;
        damage = 5;
        speed = 3.5;
        pointsPerLevel = 20;
    }
    @Override
    public void levelUp() {
        damage += damage / 3;
        speed += 1.25;
        protection += 3;
    }

}