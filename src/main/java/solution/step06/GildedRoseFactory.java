package solution.step06;

import java.util.Map;

public class GildedRoseFactory {
    private static Map<String, Class<?>> specificClasses = Map.of(
            "Normal Item", Normal.class,
            "Aged Brie", Brie.class,
            "Backstage passes to a TAFKAL80ETC concert", BackstagePass.class);

    public static GildedRose forName(String name) {
        Class<?> clazz = specificClasses.getOrDefault(name, GildedRose.class);
        try {
            GildedRose gr = (GildedRose) clazz.getDeclaredConstructor().newInstance();
            return gr;
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException(e);
        }
    }
}
