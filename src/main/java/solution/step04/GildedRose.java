package solution.step04;

public class GildedRose {
    private Item item;
    
    public GildedRose(Item item) {
        this.item = item;
    }
    
    public void initialize(int quality, int daysRemaining) {
        item.initialize(quality, daysRemaining);
    }

    public int getQuality() {
        return item.getQuality();
    }

    public int getDaysRemaining() {
        return item.getDaysRemaining();
    }

    public void tick() {
        item.tick();
    }
}
