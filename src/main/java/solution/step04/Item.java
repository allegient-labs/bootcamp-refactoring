package solution.step04;

public interface Item {
    void initialize(int quality, int daysRemaining);
    void tick();
    int getQuality();
    int getDaysRemaining();
}
