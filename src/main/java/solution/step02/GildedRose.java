package solution.step02;

public class GildedRose {
    private String name;
    protected int quality;
    protected int daysRemaining;
    
    private Item item;
    
    public GildedRose(String name) {
        this.name = name;
    }
    
    public void initialize(int quality, int daysRemaining) {
        if (item != null) {
            item.initialize(quality, daysRemaining);
        } else {
            this.quality = quality;
            this.daysRemaining = daysRemaining;
        }
    }

    public int getQuality() {
        if (item != null) {
            return item.getQuality();
        }
        return quality;
    }

    public int getDaysRemaining() {
        if (item != null) {
            return item.getDaysRemaining();
        }
        return daysRemaining;
    }

    public void tick() {
        switch (name) {
        case "Normal Item":
            normalTick();
            return;
        case "Aged Brie":
            brieTick();
            return;
        case "Sulfuras, Hand of Ragnaros":
            sulfurasTick();
            return;
        case "Backstage passes to a TAFKAL80ETC concert":
            backstageTick();
            return;
        }
    }
    
    private void normalTick() {
        item = new Normal();
        item.initialize(quality, daysRemaining);
        item.tick();
    }

    private void brieTick() {
        item = new Brie();
        item.initialize(quality, daysRemaining);
        item.tick();
    }

    private void sulfurasTick() {
        item = new Sulfuras();
        item.initialize(quality, daysRemaining);
        item.tick();
    }

    private void backstageTick() {
        item = new BackstagePass();
        item.initialize(quality, daysRemaining);
        item.tick();
    }
}
