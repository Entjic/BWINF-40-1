package de.entjic.grid.obj;

public enum Flag {
    RANDOM("random"),
    CONTAINED("containedInWords");

    private final String identifier;

    Flag(String string){
        this.identifier = string;
    }

    public String getIdentifier() {
        return identifier;
    }
}
