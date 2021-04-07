package chess.utils;

import chess.domain.dto.PieceDto;
import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import java.util.List;
import java.util.stream.Collectors;

public class PieceUtil {

    private PieceUtil() {}

    public static List<Piece> generatePiecesByPieceDtos(List<PieceDto> pieceDtos) {
        return pieceDtos
            .stream()
            .map(pieceDto -> generatePieceByPieceDto(pieceDto))
            .collect(Collectors.toList());
    }

    public static Piece generatePieceByPieceDto(PieceDto pieceDto) {
        char signature = pieceDto.getSignature();
        Location location = Location.of(pieceDto.getLocation());
        Team team = Team.of(pieceDto.getTeam());
        if (signature == 'r') {
            return Rook.of(location, team);
        }
        if (signature == 'k') {
            return King.of(location, team);
        }
        if (signature == 'q') {
            return Queen.of(location, team);
        }
        if (signature == 'p') {
            return Pawn.of(location, team);
        }
        if (signature == 'n') {
            return Knight.of(location, team);
        }
        if (signature == 'b') {
            return Bishop.of(location, team);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }

    public static Piece generatePieceFromDb(long id, long roomId, char signature, String team, String location) {
        Team pieceTeam = Team.of(team);
        Location pieceLocation = Location.of(location);
        if (signature == 'r') {
            return Rook.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'k') {
            return King.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'q') {
            return Queen.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'p') {
            return Pawn.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'n') {
            return Knight.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'b') {
            return Bishop.of(id, roomId, pieceTeam, pieceLocation);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }
}
