package solution.step05;

public class BackstagePass extends GildedRose {

    @Override
    public void tick() {
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
