package dev.Contrasim.messagelogger.utils;

import org.bukkit.ChatColor;

public enum Prefix {

    NONE(ChatColor.GRAY + "") {
        @Override
        public String toString() {
            return super.toString();
        }
    },

    SUCCESS(ChatColor.GREEN + "✔ " + ChatColor.GRAY) {
        @Override
        public String toString() {
            return super.toString();
        }
    },

    ERROR(ChatColor.RED + "❌ " + ChatColor.GRAY) {
        @Override
        public String toString() {
            return super.toString();
        }
    };

    private final String messagePrefix;

    Prefix(String messagePrefix) {
        this.messagePrefix = messagePrefix;
    }

    public String toString() {
        return messagePrefix;
    }

}
