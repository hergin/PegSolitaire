package domain;

public interface PegBoardUpdateListener {
    void onPegBoardUpdated(Location location, PegBoardCellEnum newValue);
}
