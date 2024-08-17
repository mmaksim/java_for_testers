package m.mishin.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionTests {

    @Test
    void arrayTests() {
        var array = new String[] {"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);

   array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

}
