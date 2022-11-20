package bridge.util;

import static bridge.constant.BridgeConstants.MAX_BRIDGE_SIZE;
import static bridge.constant.BridgeConstants.MIN_BRIDGE_SIZE;

import bridge.constant.RetryCommand;
import bridge.domain.Direction;

public class InputValidator {
    private static final String BLANK_INPUT_MSG = "비어 있는 입력값 입니다.";
    private static final String NOT_NUMERIC_INPUT_MSG = "숫자 이외의 값이 존재하는 입력값입니다.";
    private static final String INVALID_BRIDGE_SIZE_INPUT_MSG = "유효하지 않은 다리 크기입니다.";
    private static final String INVALID_BRIDGE_MOVE_INPUT_MSG = "유효하지 않은 다리 이동 명령입니다.";
    private static final String INVALID_GAME_COMMAND_INPUT_MSG = "유효하지 않은 게임 재시작 명령입니다.";

    public static void validateBridgeSize(String userInput) {
        validateBlank(userInput);
        validateNumeric(userInput);
        validateRange(userInput);
    }

    public static void validateBridgeMove(String userInput) {
        validateBlank(userInput);
        validateMoveOrder(userInput);
    }

    public static void validateGameCommand(String userInput) {
        validateBlank(userInput);
        validateCommandOrder(userInput);

    }

    private static void validateBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT_MSG);
        }
    }

    private static void validateNumeric(String userInput) {
        if (!userInput.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(NOT_NUMERIC_INPUT_MSG);
        }
    }

    private static void validateRange(String userInput) {
        int bridgeSize = Integer.parseInt(userInput);

        if (bridgeSize < MIN_BRIDGE_SIZE || bridgeSize > MAX_BRIDGE_SIZE) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE_INPUT_MSG);
        }
    }

    private static void validateMoveOrder(String userInput) {
        if (!Direction.contains(userInput)) {
            throw new IllegalArgumentException(INVALID_BRIDGE_MOVE_INPUT_MSG);
        }
    }

    private static void validateCommandOrder(String userInput) {
        if (!RetryCommand.contains(userInput)) {
            throw new IllegalArgumentException(INVALID_GAME_COMMAND_INPUT_MSG);
        }
    }
}
