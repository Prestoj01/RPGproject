public class Character {
    private int strength;
    private int speed;
    private int defence; 

    public Character() {
        strength = 5;
        speed = 5;
        defence = 5;
    }

    public Character(int str, int spd, int def) {
        strength = str;
        speed = spd;
        defence = def;
    }
    
    public void printStats() {
        System.out.println("Strength: " + strength);
        System.out.println("Speed: " + speed);
        System.out.println("Defence: " + defence);
    }

    public void addStrength(int n) {
        strength = strength + n;
    }

    public void addSpeed(int n) {
        speed = speed + n;
    }

    public void addDefence(int n) {
        defence = defence + n;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefence() {
        return defence;
    }
}