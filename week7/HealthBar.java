public class HealthBar {
    private int health;
    private final int maxHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = Math.max(0, maxHealth);
        health = this.maxHealth;
    }

    public void takeDamage(int amount) {
        if (amount > 0)
            health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        if (amount > 0)
            health = Math.min(maxHealth, health + amount);
    }

    public int getHealth() {
        return health;
    }

    public static void main(String[] args) {
        HealthBar c = new HealthBar(100);

        c.takeDamage(30);
        System.out.println("Health: " + c.getHealth());

        c.heal(50);
        System.out.println("Health: " + c.getHealth());

        c.takeDamage(150);
        System.out.println("Health: " + c.getHealth());
    }
}
