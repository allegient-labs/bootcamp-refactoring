package solution.step05;

public class GildedRoseFactory {
    public static GildedRose forName(String name) {
        switch (name) {
        case "Normal Item":
            return new Normal();
        case "Aged Brie":
            return new Brie();
        case "Sulfuras, Hand of Ragnaros":
            return new GildedRose();
        case "Backstage passes to a TAFKAL80ETC concert":
            return new BackstagePass();
        default:
            return null;
        }
    }
}
