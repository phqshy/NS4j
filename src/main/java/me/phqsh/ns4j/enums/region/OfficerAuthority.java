package me.phqsh.ns4j.enums.region;

public enum OfficerAuthority {
    EXECUTIVE('X'),
    WORLD_ASSEMBLY('W'),
    SUCCESSION('S'),
    APPEARANCE('A'),
    BORDER_CONTROL('B'),
    COMMUNICATIONS('C'),
    EMBASSIES('E'),
    POLLS('P');

    private char letterCode;

    OfficerAuthority(char letterCode) {
        this.letterCode = letterCode;
    }

    private char getLetterCode() {
        return this.letterCode;
    }

    public static OfficerAuthority getByValue(int id) {
        for (OfficerAuthority auth : OfficerAuthority.values()) {
            if (auth.getLetterCode() == id) {
                return auth;
            }
        }
        return null;
    }
}
