package Players;

import Chess.ChessBoard;

import java.util.concurrent.Callable;

public class PlayerTask implements Callable {
    IPlayer player;
    ChessBoard board;

    public PlayerTask(IPlayer turn, ChessBoard panel) {
        player = turn;
        board = panel;
    }

    @Override
    public String call() throws Exception {
//        while(true);
        return player.getNextMove(board);
    }
}
