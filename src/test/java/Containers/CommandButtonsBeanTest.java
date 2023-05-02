package Containers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;
import java.util.stream.Stream;

class CommandButtonsBeanTest {
    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void changeConditionWithExistingKeyAndConcreteClass(CommandButtonsBean bean) {
        int keyValue = 1;

        bean.changeCondition(keyValue);

        Assertions.assertAll(
                () -> Assertions.assertEquals(bean.getValue(), keyValue),
                () -> Assertions.assertTrue(bean.getCondition(keyValue)),
                () -> Assertions.assertTrue(() -> bean.getConditions()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != keyValue)
                        .noneMatch(Map.Entry::getValue)
                )
        );
    }

    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void changeConditionWithNotExistingKeyAndConcreteClass(CommandButtonsBean bean) {
        int keyValue = -5;

        bean.changeCondition(keyValue);

        Assertions.assertAll(
                () -> Assertions.assertNull(bean.getValue()),
                () -> Assertions.assertTrue(bean.getConditions()
                        .values()
                        .stream()
                        .noneMatch(Boolean::booleanValue))
        );
    }

    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void setDefaultWithConcreteClass(CommandButtonsBean bean) {
        bean.changeCondition(1);

        bean.setDefault();

        Assertions.assertAll(
                () -> Assertions.assertNull(bean.getValue()),
                () -> Assertions.assertTrue(bean.getConditions()
                        .values()
                        .stream()
                        .noneMatch(Boolean::booleanValue))
        );
    }

    static class Params implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new XCoordinateButtonsBean()),
                    Arguments.of(new RadiusButtonsBean())
            );
        }
    }
}
