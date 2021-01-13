package me.rileykenny.snowballfight.util;

import me.rileykenny.snowballfight.Core;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

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

    private static Object getMessageSection(String section) {
        FileConfiguration messageConfig = Core.getInstance().getMessageConfig();
        Object localeValue = messageConfig.contains(section) ? messageConfig.get(section) : null;
        if (localeValue == null)  {
            throw new NullPointerException(section + " does not exist in the config");
        }

        return localeValue;
    }

    public static String getLocale(String locale, String... args) {
        Object value = getMessageSection(locale);
        if (value instanceof String) {
            String localeMessage = (String) value;
            for (int i = 0; i < args.length; i++) {
                localeMessage = localeMessage.replace("{" + i + "}", args[i]);
            }

            return translate(localeMessage);
        }

        throw new InputMismatchException(locale + " should be of type string or string list!");
    }

    public static List<String> getLocaleList(String locale, String... args) {
        Object messageSection = getMessageSection(locale);
        List<String> localeList = new ArrayList<>();
        if (messageSection instanceof List<?>) {
            for (Object value : (List<?>) messageSection) {
                if (value instanceof String) {
                    String localeMessage = (String) value;
                    for (int i = 0; i < args.length; i++) {
                        localeMessage = localeMessage.replace("{" + i + "}", args[i]);
                    }

                    localeList.add(localeMessage);
                }
            }
        }

        return localeList;
    }

    public static List<String> getLocaleList(String locale, Map<Integer, List<String>> args) {
        return null;
        // TODO
    }




}
