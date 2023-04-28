

public class Druid extends Character {

    public Druid() {
        protection = 4;
        damage = 7;
        speed = 3.0;
        pointsPerLevel = 15;
    }
    @Override
    public void levelUp() {
        damage += 10;
        speed += 0.25;
        protection += 2;
    }

}