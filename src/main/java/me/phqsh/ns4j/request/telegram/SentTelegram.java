package me.phqsh.ns4j.request.telegram;

import java.time.OffsetDateTime;

public record SentTelegram(String target, String tgId, OffsetDateTime timestamp) {
}
