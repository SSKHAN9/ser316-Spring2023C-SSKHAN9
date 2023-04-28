

public class Ranger extends Character {

    public Ranger() {
        protection = 8;
        damage = 8;
        speed = 2.5;
        pointsPerLevel = 15;
    }
    @Override
    public void levelUp() {
        damage += damage % 10;
        speed += 0.5;
        protection += protection % 5;
    }

}