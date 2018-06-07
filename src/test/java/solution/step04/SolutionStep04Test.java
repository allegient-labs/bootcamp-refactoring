package solution.step04;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SolutionStep04Test {
    
    private GildedRose makeGildedRose(String name, int daysRemaining , int quality) {
        GildedRose gr = GildedRoseFactory.forName(name);
        gr.initialize(quality, daysRemaining);
        return gr;
    }
    
    @Test
    public void testNormalBeforeSellDate() {
        GildedRose gildedRose = makeGildedRose("Normal Item", 5, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(9);
    }

    @Test
    public void testNormalOnSellDate() {
        GildedRose gildedRose = makeGildedRose("Normal Item", 0, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(8);
    }

    @Test
    public void testNormalAfterSellDate() {
        GildedRose gildedRose = makeGildedRose("Normal Item", -10, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(8);
    }

    @Test
    public void testNormalZeroQuality() {
        GildedRose gildedRose = makeGildedRose("Normal Item", 5, 0);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }

    @Test
    public void testAgedBrieBeforeSellDate() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", 5, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(11);
    }

    @Test
    public void testAgedBrieBeforeSellDateWithMaxQuality() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", 5, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testAgedBrieOnSellDate() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", 0, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(12);
    }

    @Test
    public void testAgedBrieOnSellDateNearMaxQuality() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", 0, 49);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testAgedBrieOnSellDateWithMaxQuality() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", 0, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testAgedBrieAfterSellDate() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", -10, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(12);
    }

    @Test
    public void testAgedBrieAfterSellDateWithMaxQuality() {
        GildedRose gildedRose = makeGildedRose("Aged Brie", -10, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testSulfurasBeforeSellDate() {
        GildedRose gildedRose = makeGildedRose("Sulfuras, Hand of Ragnaros", 5, 80);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(5);
        assertThat(gildedRose.getQuality()).isEqualTo(80);
    }

    @Test
    public void testSulfurasOnSellDate() {
        GildedRose gildedRose = makeGildedRose("Sulfuras, Hand of Ragnaros", 0, 80);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(0);
        assertThat(gildedRose.getQuality()).isEqualTo(80);
    }

    @Test
    public void testSulfurasAfterSellDate() {
        GildedRose gildedRose = makeGildedRose("Sulfuras, Hand of Ragnaros", -10, 80);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-10);
        assertThat(gildedRose.getQuality()).isEqualTo(80);
    }

    @Test
    public void testBackstagePassLongBeforeSellDate() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 11, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(10);
        assertThat(gildedRose.getQuality()).isEqualTo(11);
    }

    @Test
    public void testBackstagePassLongBeforeSellDateAtMaxQuality() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 11, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(10);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testBackstagePassMediumCloseToSellDateUB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(9);
        assertThat(gildedRose.getQuality()).isEqualTo(12);
    }

    @Test
    public void testBackstagePassMediumCloseToSellDateAtMaxQualityUB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 10, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(9);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testBackstagePassMediumCloseToSellDateLB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 6, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(5);
        assertThat(gildedRose.getQuality()).isEqualTo(12);
    }

    @Test
    public void testBackstagePassMediumCloseToSellDateAtMaxQualityLB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 6, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(5);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testBackstagePassVeryCloseToSellDateUB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(13);
    }

    @Test
    public void testBackstagePassVeryCloseToSellDateAtMaxQualityUB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testBackstagePassVeryCloseToSellDateLB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 1, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(0);
        assertThat(gildedRose.getQuality()).isEqualTo(13);
    }

    @Test
    public void testBackstagePassVeryCloseToSellDateAtMaxQualityLB() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 1, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(0);
        assertThat(gildedRose.getQuality()).isEqualTo(50);
    }

    @Test
    public void testBackstagePassOnSellDate() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", 0, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }

    @Test
    public void testBackstagePassAfterSellDate() {
        GildedRose gildedRose = makeGildedRose("Backstage passes to a TAFKAL80ETC concert", -10, 50);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }

    @Test
    @Disabled
    public void testConjuredBeforeSellDate() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", 5, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(8);
    }

    @Test
    @Disabled
    public void testConjuredBeforeSellDateAtZeroQuality() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", 5, 0);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(4);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }

    @Test
    @Disabled
    public void testConjuredOnSellDate() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", 0, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(6);
    }

    @Test
    @Disabled
    public void testConjuredOnSellDateAtZeroQuality() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", 0, 0);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-1);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }

    @Test
    @Disabled
    public void testConjuredAfterSellDate() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", -10, 10);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(6);
    }

    @Test
    @Disabled
    public void testConjuredAfterSellDateAtZeroQuality() {
        GildedRose gildedRose = makeGildedRose("Conjured Mana Cake", -10, 0);
        gildedRose.tick();
        assertThat(gildedRose.getDaysRemaining()).isEqualTo(-11);
        assertThat(gildedRose.getQuality()).isEqualTo(0);
    }
}
