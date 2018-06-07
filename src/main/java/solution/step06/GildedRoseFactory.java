package solution.step06;

import java.util.HashMap;
import java.util.Map;

public class GildedRoseFactory {
    private static Map<String, Class<?>> specificClasses = new HashMap<>();
    
    static {
        specificClasses.put("Normal Item", Normal.class);
        specificClasses.put("Aged Brie", Brie.class);
        specificClasses.put("Backstage passes to a TAFKAL80ETC concert", BackstagePass.class);
    }
    
    public static GildedRose forName(String name) {
        Class<?> clazz = specificClasses.computeIfAbsent(name, s -> GildedRose.class);
        try {
            GildedRose gr = (GildedRose) clazz.getDeclaredConstructor().newInstance();
            return gr;
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException(e);
        }
    }
}
