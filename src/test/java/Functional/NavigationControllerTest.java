package Functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NavigationControllerTest {
    private final NavigationController controller = new NavigationController();

    @Test
    void startRequestReturnsStartRoute() {
        Assertions.assertEquals("start", controller.processStart());
    }

    @Test
    void mainRequestReturnsMainRoute() {
        Assertions.assertEquals("main", controller.processMain());
    }
}
