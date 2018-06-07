package solution.step01;

public class GildedRose {
    private String name;
    private int quality;
    private int daysRemaining;
    
    public GildedRose(String name) {
        this.name = name;
    }
    
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
        daysRemaining = daysRemaining - 1;

        if (quality == 0) {
            return;
        }
        
        quality = quality - 1;
        
        if (daysRemaining <= 0) {
            quality = quality - 1;
        }
    }

    private void brieTick() {
        daysRemaining = daysRemaining - 1;

        if (quality == 50) {
            return;
        }
        
        quality = quality + 1;
        
        if (daysRemaining <= 0 && quality < 50) {
            quality = quality + 1;
        }
        
    }

    private void sulfurasTick() {
    }

    private void backstageTick() {
        daysRemaining = daysRemaining - 1;
        
        if (daysRemaining < 0) {
            quality = 0;
            return;
        }
        
        if (quality >= 50) {
            return;
        }
        
        quality = quality + 1;

        if (daysRemaining < 10) {
            quality = quality + 1;
        }

        if (daysRemaining < 5) {
            quality = quality + 1;
        }
    }
}
