package Validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.faces.convert.ConverterException;
import java.util.stream.Stream;

class TextFieldFloatConverterTest {
    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void emptyStringConversion(TextFieldFloatConverter converter) {
        String toConvert = "";

        ConverterException exception = Assertions.assertThrows(ConverterException.class,
                () -> converter.getAsObject(null, null, toConvert));

        Assertions.assertEquals("Поле не должно быть пустым.", exception.getMessage());
    }

    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void nullConversion(TextFieldFloatConverter converter) {
        ConverterException exception = Assertions.assertThrows(ConverterException.class,
                () -> converter.getAsObject(null, null, null));

        Assertions.assertEquals("Поле не должно быть пустым.", exception.getMessage());
    }

    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void notANumberConversion(TextFieldFloatConverter converter) {
        String toConvert = "number";

        ConverterException exception = Assertions.assertThrows(ConverterException.class,
                () -> converter.getAsObject(null, null, toConvert));

        Assertions.assertEquals("Необходимо ввести число.", exception.getMessage());
    }

    static class Params implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new YTextFieldFloatConverter())
            );
        }
    }
}
