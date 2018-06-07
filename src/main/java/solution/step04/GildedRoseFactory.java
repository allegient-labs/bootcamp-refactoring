package solution.step04;

public class GildedRoseFactory {
    public static GildedRose forName(String name) {
        switch (name) {
        case "Normal Item":
            return new GildedRose(new Normal());
        case "Aged Brie":
            return new GildedRose(new Brie());
        case "Sulfuras, Hand of Ragnaros":
            return new GildedRose(new Sulfuras());
        case "Backstage passes to a TAFKAL80ETC concert":
            return new GildedRose(new BackstagePass());
        default:
            return null;
        }
    }
}
