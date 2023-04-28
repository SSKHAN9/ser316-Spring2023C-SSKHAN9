

public class Bard extends Character {

    public Bard() {
        protection = 3;
        damage = 6;
        speed = 4.5;
        pointsPerLevel = 10;
    }
    @Override
    public void levelUp() {
        damage += damage / 2;
        speed += 0.5;
        protection += protection / 2;
    }
}