package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertNotNull(Payment.of(4000));
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    @DisplayName("생성자 인자 유효성 테스트")
    void create_validate(int amount) {
        assertThatIllegalArgumentException().isThrownBy(() -> Payment.of(amount));
    }

    @ParameterizedTest
    @CsvSource(value = {"4000, 3, 1", "14000, 3, 11"})
    @DisplayName("남은 금액")
    void buyTickets(int amount, int ticketCount, int actual) {
        // given
        Payment payment = Payment.of(amount);

        // when
        payment.buyTickets(ticketCount);

        // then
        assertEquals(payment.remaining(), actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000, 10", "1000, -1"})
    void buyTickets_validate(int amount, int ticketCount) {
        Payment payment = Payment.of(amount);
        assertThatIllegalArgumentException().isThrownBy(() -> payment.buyTickets(ticketCount));
    }
}
