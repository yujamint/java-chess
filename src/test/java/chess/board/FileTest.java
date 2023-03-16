package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("대상 File과의 값 차이를 반환한다.")
    // TODO: 2023/03/15 테스트 케이스 추가(Parameterized Test)
    void getValueDiff() {
        // given
        File file = File.A;
        File targetFile = File.E;

        // when
        final int valueDiff = file.getValueDiff(targetFile);

        // then
        assertThat(valueDiff).isEqualTo(4);
    }

    @Test
    @DisplayName("대상 File과의 값 차이에 따른 수평 좌표 값을 반환한다.")
    void getValuePoint() {
        // given
        File file = File.A;
        File targetFile = File.E;

        // when
        final int valuePoint = file.getValuePoint(targetFile);

        // then
        assertThat(valuePoint).isEqualTo(1);
    }
}
