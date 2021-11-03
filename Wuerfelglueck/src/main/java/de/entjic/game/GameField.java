package de.entjic.game;

public enum GameField {
    EMPTY,
    BASIC,
    SPAWN,
    END;

    private Team team;

    GameField(){
        this.team = Team.EMPTY;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
