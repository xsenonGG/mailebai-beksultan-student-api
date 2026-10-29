package kz.iitu.studentapi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CiControlledFailureTest {
    @Test
    void demonstratesCiFailure() {
        assertTrue(false, "Mailebai Beksultan: intentional CI failure");
    }
}
