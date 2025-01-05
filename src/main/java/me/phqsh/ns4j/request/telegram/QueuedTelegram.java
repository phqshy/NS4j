package me.phqsh.ns4j.request.telegram;

public record QueuedTelegram(String target, String apiKey, String tgId, String tgSecret, boolean isRecruitment) {
}
