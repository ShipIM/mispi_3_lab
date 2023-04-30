package Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HitTest {
    @Test
    void inputGivesHitOutput() {
        float x = 0.5f, y = 0.5f, r = 1;
        Containable<Float> xContainable = new FloatContainer(x),
                yContainable = new FloatContainer(y),
                rContainable = new FloatContainer(r);

        Hit hit = new Hit(xContainable, yContainable, rContainable);

        Assertions.assertTrue(hit.result());
    }

    @Test
    void inputGivesMissOutput() {
        float x = 1f, y = 1f, r = 1f;
        Containable<Float> xContainable = new FloatContainer(x),
                yContainable = new FloatContainer(y),
                rContainable = new FloatContainer(r);

        Hit hit = new Hit(xContainable, yContainable, rContainable);

        Assertions.assertFalse(hit.result());
    }
}
