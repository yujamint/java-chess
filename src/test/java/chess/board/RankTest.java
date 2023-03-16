package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("대상 Rank과의 값 차이를 반환한다.")
        // TODO: 2023/03/15 테스트 케이스 추가(Parameterized Test)
    void getValueDiff() {
        // given
        Rank rank = Rank.ONE;
        Rank targetRank = Rank.FIVE;

        // when
        final int valueDiff = rank.getValueDiff(targetRank);

        // then
        assertThat(valueDiff).isEqualTo(4);
    }

    @Test
    @DisplayName("대상 Rank과의 값 차이에 따른 수직 좌표 값을 반환한다.")
    void getValuePoint() {
        // given
        Rank rank = Rank.ONE;
        Rank targetRank = Rank.FIVE;

        // when
        final int valuePoint = rank.getValuePoint(targetRank);

        // then
        assertThat(valuePoint).isEqualTo(1);
    }
}
