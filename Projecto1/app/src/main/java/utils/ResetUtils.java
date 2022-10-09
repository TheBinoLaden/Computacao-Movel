package utils;

import android.widget.CompoundButton;

import java.time.LocalTime;
import java.util.List;

/**
 * Utils used to reset the buttons or any subclass.
 */
public class ResetUtils {


    /**
     * @param buttonList
     */
    public static void resetButtonList(final List<? extends CompoundButton> buttonList) {

        for (int i = 0; i < buttonList.size(); i++) {
            resetButton(buttonList.get(i));
        }
    }

    private static <T extends CompoundButton> void resetButton(final T button) {
        button.setChecked(Boolean.FALSE);
    }

    /**
     * Validates and change the behaviour of the button comparing the times of clicking
     *
     * @param buttons the list of buttons
     * @param times   the times when each button was clicked
     */
    public static void validateLastChecked(final List<? extends CompoundButton> buttons,
                                           final List<LocalTime> times) {
        if (times.get(0).isAfter(times.get(1))) {
            buttons.get(1).setChecked(Boolean.FALSE);
        } else if (times.get(1).isAfter(times.get(0))) {
            buttons.get(0).setChecked(Boolean.FALSE);
        }

    }
}
