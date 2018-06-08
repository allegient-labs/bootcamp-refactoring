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
        switch (name) {
        case "Normal Item":
            item = new Normal();
            break;
        case "Aged Brie":
            item = new Brie();
            break;
        case "Sulfuras, Hand of Ragnaros":
            item = new Sulfuras();
            break;
        case "Backstage passes to a TAFKAL80ETC concert":
            item = new BackstagePass();
            break;
        }

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
        if (item != null) {
            item.tick();
        }
    }
}
