package utils;

import android.util.Log;
import android.widget.CompoundButton;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        List<LocalTime> aux = times.stream().filter(Objects::nonNull).collect(Collectors.toList());

        // If something is null this condition will safeguard against NPE
        if (aux.size() != times.size() && !aux.isEmpty()) {
            Log.d("Different Size", "the timeStamp null elements are " + aux.size());
            buttons.get(times.indexOf(aux.get(0))).setChecked(Boolean.FALSE);
            return;
        }
        if (times.get(0).isAfter(times.get(1))) {
            buttons.get(1).setChecked(Boolean.FALSE);
        } else if (times.get(1).isAfter(times.get(0))) {
            buttons.get(0).setChecked(Boolean.FALSE);
        }
    }
}
