package Functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class ClockBeanTest {
    private ClockBean bean;
    private final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            .withZone(ZoneId.of("GMT"));

    @BeforeEach
    void prepare() {
        bean = new ClockBean();
    }

    @Test
    void clockBeanReturnsCorrectTimeWithoutUpdate() {
        LocalDateTime currentTime = LocalDateTime.now();

        Assertions.assertEquals(defaultFormatter.format(currentTime), bean.getTime());
    }

    @Test
    void clockBeanReturnsCorrectTimeWithUpdate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Assertions.assertAll(
                () -> Assertions.assertNotEquals(defaultFormatter.format(currentTime), bean.getTime()),
                () -> {
                    bean.updateTime();
                    Assertions.assertEquals(defaultFormatter.format(currentTime), bean.getTime());
                }
        );

    }
}
