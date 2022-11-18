package bridge.input;

import bridge.util.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    
    @Test
    @DisplayName("다리생성을 위한 사용자 입력이 비어 있거나 공백으로만 이루어진 경우 에러 메세지와 함께 IllegalArgumentException이 발생한다.")
    public void validateBlankInput() throws Exception{
        //given
        InputValidator inputValidator = new InputValidator();
        final String BLANK_INPUT_MSG = "비어 있는 입력값 입니다.";
        String userInput = "";
        //when
        //then
        Assertions.assertThatThrownBy(() -> inputValidator.validateBridgeSize(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BLANK_INPUT_MSG);
    }

    @ParameterizedTest
    @DisplayName("다리생성을 위한 사용자 입력에 숫자 외 값이 포함된 경우 에러 메세지와 함께 IllegalArgumentException이 발생한다.")
    @ValueSource(strings = {"1,","1a","1가","!12","abc","가나다","1:b",})
    public void validateNotNumericInput(String userInput) throws Exception{
        //given
        InputValidator inputValidator = new InputValidator();
        final String NOT_NUMERIC_INPUT_MSG = "숫자 이외의 값이 존재하는 입력값입니다.";
        //when
        //then
        Assertions.assertThatThrownBy(() -> inputValidator.validateBridgeSize(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMERIC_INPUT_MSG);
    }

    @ParameterizedTest
    @DisplayName("다리생성을 위한 사용자 입력이 유효한 범위 내 값이 아닌 경우 경우 에러 메세지와 함께 IllegalArgumentException이 발생한다.")
    @ValueSource(strings = {"0","1","2","21","30","100"})
    public void validateRangedInputForBridgeSize(String userInput) throws Exception{
        //given
        InputValidator inputValidator = new InputValidator();
        final String INVALID_BRIDGE_SIZE_INPUT = "유효하지 않은 다리 크기입니다.";
        //when
        //then
        Assertions.assertThatThrownBy(() -> inputValidator.validateBridgeSize(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BRIDGE_SIZE_INPUT);
    }

    @ParameterizedTest
    @DisplayName("다리 이동을 위한 사용자 입력이 유효한 값이 아닌 경우 에러 메세지와 함께 IllegalArgumentException이 발생한다.")
    @ValueSource(strings = { "U ","D "," U "," D ","u","d"})
    public void validateRangedInputForCrossBridge(String userInput) throws Exception{
        //given
        InputValidator inputValidator = new InputValidator();
        final String INVALID_BRIDGE_MOVE_INPUT = "유효하지 않은 다리 이동 명령입니다.";
        //when
        //then
        Assertions.assertThatThrownBy(() -> inputValidator.validateBridgeMove(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BRIDGE_MOVE_INPUT);
    }
}
