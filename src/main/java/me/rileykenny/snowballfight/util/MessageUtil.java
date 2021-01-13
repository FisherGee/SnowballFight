package me.rileykenny.snowballfight.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    public static String translate(String unTranslated) {
        if (unTranslated != null) {
            ChatColor.translateAlternateColorCodes('&', unTranslated);
        }

        return unTranslated;
    }

    public static ArrayList<String> translate(List<String> unTranslated) {
        ArrayList<String> translated = new ArrayList<>();
        if (unTranslated != null) {
            for (String string : unTranslated) {
                translated.add(ChatColor.translateAlternateColorCodes('&', string));
            }
        }

        return translated;
    }




}
