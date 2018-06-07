package solution.step05;

public class Normal extends GildedRose {

    @Override
    public void tick() {
        daysRemaining = daysRemaining - 1;

        if (quality == 0) {
            return;
        }
        
        quality = quality - 1;
        
        if (daysRemaining <= 0) {
            quality = quality - 1;
        }
    }
}
