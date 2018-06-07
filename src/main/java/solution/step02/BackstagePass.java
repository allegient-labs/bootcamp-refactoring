package solution.step02;

public class BackstagePass implements Item {
    
    private int quality;
    private int daysRemaining;
    
    @Override
    public void initialize(int quality, int daysRemaining) {
        this.quality = quality;
        this.daysRemaining = daysRemaining;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public int getDaysRemaining() {
        return daysRemaining;
    }
    
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
