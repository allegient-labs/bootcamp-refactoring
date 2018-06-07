package solution.step07;

public class Conjured extends GildedRose {

    @Override
    public void tick() {
        daysRemaining = daysRemaining - 1;

        if (daysRemaining > 0) {
            quality -= 2;
        } else {
            quality -= 4;
        }
        
        if (quality < 0) {
            quality = 0;
        }
    }
}
