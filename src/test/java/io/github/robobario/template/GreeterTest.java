package io.github.robobario.template;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterTest {

    @Test
    void greetsByName() {
        assertThat(new Greeter().greet("World")).isEqualTo("Hello, World!");
    }
}
