package me.phqsh.ns4j.enums;

public enum PrivateShards {
    DOSSIER("dossier"),
    ISSUES("issues"),
    ISSUE_SUMMARY("issuesummary"),
    NEXT_ISSUE("nextissue"),
    NEXT_ISSUE_TIME("nextissuetime"),
    NOTICES("notices"),
    PACKS("packs"),
    PING("ping"),
    R_DOSSIER("rdossier"),
    UNREAD("unread");

    private final String id;

    PrivateShards(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
