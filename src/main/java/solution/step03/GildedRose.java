package solution.step03;

public class GildedRose {
    private Item item;
    
    public GildedRose(String name) {
        item = forName(name);
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

    private Item forName(String name) {
        switch (name) {
        case "Normal Item":
            return new Normal();
        case "Aged Brie":
            return new Brie();
        case "Sulfuras, Hand of Ragnaros":
            return new Sulfuras();
        case "Backstage passes to a TAFKAL80ETC concert":
            return new BackstagePass();
        }
        
        return null;
    }
}
