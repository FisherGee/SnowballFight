package me.rileykenny.snowballfight.util;

import me.rileykenny.snowballfight.Core;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public static String getLocale(String locale, String... args) {
        // Check to make sure key is contained otherwise throw an exception.
        FileConfiguration messageConfig = Core.getInstance().getMessageConfig();
        Object localeValue = messageConfig.contains(locale) ? messageConfig.get(locale) : null;
        if (localeValue == null)  {
            throw new NullPointerException(locale + " does not exist in the config");
        }

        // Check if type string.
        if (localeValue instanceof String) {
            String localeMessage = (String) localeValue;
            for (int i = 0; i < args.length; i++) {
                localeMessage = localeMessage.replace("{" + i + "}", args[i]);
            }

            return translate(localeMessage);
        } else {
            throw new InputMismatchException(locale + " should be of type string!");
        }
    }




}
