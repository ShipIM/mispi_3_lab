package Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

class CachedResultableTest {

    @ParameterizedTest
    @ArgumentsSource(Params.class)
    void cachedResultSameAsNotCached(Resultable<Boolean> resultable) {
        CachedResultable<Boolean> cached = new CachedResultable<>(resultable);

        boolean result = resultable.result(),
                cachedResult = cached.result();

        Assertions.assertEquals(result, cachedResult);
    }

    static class Params implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Hit(new FloatContainer(1),
                            new FloatContainer(1),
                            new FloatContainer(1)))
            );
        }
    }
}
