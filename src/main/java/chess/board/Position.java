package chess.board;

public class Position {

    private File file;
    private Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public void move(final int fileMovingCount, final int rankMovingCount) {
        this.file = file.getAddedFile(fileMovingCount);
        this.rank = rank.getAddedRank(rankMovingCount);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
