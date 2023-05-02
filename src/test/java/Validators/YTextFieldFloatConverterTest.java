package Validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.faces.convert.ConverterException;

class YTextFieldFloatConverterTest {
    @Test
    void floatOutsideOfTheRangeConversion() {
        TextFieldFloatConverter converter = new YTextFieldFloatConverter();
        String toConvert = "10";

        ConverterException exception = Assertions.assertThrows(ConverterException.class,
                () -> converter.getAsObject(null, null, toConvert));

        Assertions.assertEquals("Число должно быть в диапазоне от -4.0 до 4.0.", exception.getMessage());
    }
}
