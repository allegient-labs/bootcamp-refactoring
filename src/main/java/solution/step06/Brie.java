package solution.step06;

public class Brie extends GildedRose {

    @Override
    public void tick() {
        daysRemaining = daysRemaining - 1;

        if (quality == 50) {
            return;
        }
        
        quality = quality + 1;
        
        if (daysRemaining <= 0 && quality < 50) {
            quality = quality + 1;
        }
    }
}
