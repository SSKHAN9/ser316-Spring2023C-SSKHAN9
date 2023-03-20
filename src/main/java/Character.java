package main.java;

public class Character {
    public int health=100;
    public int level=1;
    public int experience=0;
    public int protection=0;
    public int damage=0;
    public double speed=0.0;
    public int pointsPerLevel=100;

    public void printInfo() {
        System.out.println("Class: " + this.getClass().toString());
        System.out.println("Level: " + level);
        System.out.println("Health: " + health);
        System.out.println("Experience: " + experience);
        System.out.println("Protection: " + protection);
        System.out.println("Damage: " + damage);
        System.out.println("Speed: " + speed);
        System.out.println("Points per Level: " + pointsPerLevel);
        System.out.println("\n");
    }
}