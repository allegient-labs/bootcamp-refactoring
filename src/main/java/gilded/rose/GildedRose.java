package gilded.rose;

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
        if (!name.equals("Aged Brie") && !name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (quality > 0) {
                if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                    quality = quality - 1;
                }
            }
        } else {
            if (quality < 50) {
                quality = quality + 1;
                if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (daysRemaining < 11) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }
                    if (daysRemaining < 6) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }
                }
            }
        }
        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
            daysRemaining = daysRemaining - 1;
        }
        if (daysRemaining < 0) {
            if (!name.equals("Aged Brie")) {
                if (!name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (quality > 0) {
                        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                            quality = quality - 1;
                        }
                    }
                } else {
                    quality = quality - quality;
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }
}
