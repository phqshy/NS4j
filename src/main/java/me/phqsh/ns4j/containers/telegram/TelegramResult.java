package me.phqsh.ns4j.containers.telegram;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;

public class TelegramResult extends Container {
    @Getter
    private boolean result;

    public TelegramResult(boolean result) {
        this.result = result;
    }
}
