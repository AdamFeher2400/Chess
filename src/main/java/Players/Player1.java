package Players;

import Chess.ChessBoard;
import Chess.ChessPiece;

public class Player1 implements IPlayer {
    ChessPiece.PieceColor myteam;
    @Override
    public String getName() {
        return "Player1_" + myteam;
    }

    @Override
    public void setTeam(ChessPiece.PieceColor team) {
        myteam = team;
    }

    // this is just an example.
    static int x = 0;
    @Override
    public String getNextMove(ChessBoard board) {
        x ++;
        if(myteam == ChessPiece.PieceColor.White)
            return (x < 3 ? "F2-F3" : "G2-G4");
        else
            return (x < 3 ? "E7-E6" : "D8-H4");
    }
}
