package app.casinoroyale.Model.DataModels.GameModels.HorseModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.layout.Pane;

import java.util.List;

public class Race {
    private Pane racePane;
    private List<Horse> horses;
    private double finishLine;
    private Horse winner;

    @SuppressFBWarnings({"URF_UNREAD_FIELD", "EI_EXPOSE_REP2"})
    public Race(Pane racePane, List<Horse> horses) {
        this.racePane = racePane;
        this.horses = horses;
        this.finishLine = racePane.getPrefWidth();
        this.winner = null;
    }

    public void start() {
        for (Horse horse : horses) {
            horse.startRace(finishLine - horse.getImageView().getFitWidth());
        }
    }

    public void stop() {
        for (Horse horse : horses) {
            horse.stopRace();
        }
    }

    public boolean isRaceFinished() {
        for (Horse horse : horses) {
            if (horse.getImageView().getLayoutX() >= finishLine - horse.getImageView().getFitWidth()) {
                winner = horse;
                stop(); // Stop all horses once a winner is determined
                return true;
            }
        }
        return false;
    }

    public Horse getWinner() {
        return new Horse(winner.getName(), winner.getImageView());
    }

    // Resets the position of all horses to start a new race
    public void resetHorses() {
        for (Horse horse : horses) {
            horse.getImageView().setLayoutX(0);
        }
    }
}