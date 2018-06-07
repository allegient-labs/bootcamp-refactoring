package solution.step02;

public interface Item {
    void initialize(int quality, int daysRemaining);
    void tick();
    int getQuality();
    int getDaysRemaining();
}
