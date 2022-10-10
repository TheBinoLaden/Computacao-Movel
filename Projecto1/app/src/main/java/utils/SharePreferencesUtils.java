package utils;

import android.content.SharedPreferences;
import android.util.Log;

public class SharePreferencesUtils {


    public static Object getInformation(final SharedPreferences sharedPreferences, final String key) {

        Object value = sharedPreferences.getAll().get(key);
        if (value instanceof String
                || value instanceof Integer
                || value instanceof Float
                || value instanceof Long
                || value instanceof Boolean) {
            return value;
        }
        Log.e("Wrong Format Object", "the pair does not exist. Assuming default values.");
        return null;
    }

    public static void putInformation(final SharedPreferences sharedPreferences, final String key,
                                      final Object value) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.commit();
    }

    public static void removeInformation(final SharedPreferences.Editor editor, final String key) {

        editor.remove(key);
        editor.commit();
    }
}
