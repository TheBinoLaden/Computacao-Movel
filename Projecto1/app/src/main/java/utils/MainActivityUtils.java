package utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.paint.R;

import java.util.List;

import fragments.CanvasFragment;
import fragments.PaletteFragment;
import listeners.CanvasListener;
import views.CanvasView;

/**
 * Utility Class for general operations within the Main Activity (needs to be a POJO perhaps)
 */
public class MainActivityUtils {

    MainActivityUtils() {
        // empty constructor. To comply with SONAR
    }

    /**
     * Performs the action needed for the Canvas Button
     *
     * @param context the context of the application
     * @param manager the fragment manager
     */
    public static void canvasButtonAction(final Context context, final List<Button> buttons,
                                          final FragmentManager manager) {
        CanvasListener canvasListener = new CanvasListener();
        GestureDetector detector = new GestureDetector(context, canvasListener);

        CanvasView paintCanvas = new CanvasView(context, null, detector);
        final CanvasFragment canvasFragment = new CanvasFragment(detector);
        canvasListener.setCanvas(paintCanvas);

        hideButtons(buttons);
        replaceFragment(canvasFragment, manager);
    }

    /**
     * Performs the action needed for the Palette Button
     *
     * @param buttons the list of buttons of the activity
     * @param manager the fragment manager
     */
    public static void paletteButtonAction(final List<Button> buttons, final FragmentManager manager) {
        hideButtons(buttons);
        replaceFragment(new PaletteFragment(), manager);

    }

    private static void hideButtons(final List<Button> buttons) {
        for (final Button button : buttons) {
            button.setVisibility(View.GONE);
        }
    }

    private static void replaceFragment(Fragment fragment, final FragmentManager manager) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private static void addFragment(final Fragment fragment, final FragmentManager manager) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}
