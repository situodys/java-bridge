package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;

    public GameController(InputView inputView,OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = initBridgeGame();
    }

    private BridgeGame initBridgeGame() {
        outputView.printStart();

        return new BridgeGame(readValidBridgeSize());
    }

    private int readValidBridgeSize() {
        while(true){
            try {
                return inputView.readBridgeSize();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private String getValidBridgeMove() {
        outputView.printOrder();

        return readValidBridgeMove();
    }

    private String readValidBridgeMove() {
        while(true){
            try {
                return inputView.readMoving();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
