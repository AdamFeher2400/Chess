package Players;

import Chess.ChessBoard;
import Chess.ChessPiece;

public interface IPlayer {
    String getName();
    void setTeam(ChessPiece.PieceColor team);
    String getNextMove(ChessBoard board);
}
