package solution.step07;

public class GildedRose {
    protected int quality;
    protected int daysRemaining;
    
    public void initialize(int quality, int daysRemaining) {
        this.quality = quality;
        this.daysRemaining = daysRemaining;
    }
    
    public int getQuality() {
        return quality;
    }
    
    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void tick() {}
}
