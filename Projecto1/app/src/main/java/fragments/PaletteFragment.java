package fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.paint.R;

import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class PaletteFragment extends Fragment {

    private static final int MAX_COLOR = 255;
    private final List<SeekBar> slideColors = new ArrayList<>(3);
    private View view;
    private View colorBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_palette, container, false);
        colorBoard = view.findViewById(R.id.ColorBoard);
        setSeekbarList(view);
        setListeners();
        return view;
    }

    private void setSeekbarList(final View view) {
        slideColors.add(view.findViewById(R.id.redBar));
        slideColors.add(view.findViewById(R.id.greenBar));
        slideColors.add(view.findViewById(R.id.blueBar));
    }


    private void setListeners() {
        for (final SeekBar bar : slideColors) {
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    bar.setProgress(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //NOTHING
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    SharedPreferences preferences =
                            getContext().getSharedPreferences("SettingsPref", 0);
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("colorPaint", setColorIntoColorBoard());
                    editor.apply();

                }
            });
        }
    }

    private int setColorIntoColorBoard() {
        final int color = Color.argb(
                MAX_COLOR,
                slideColors.get(0).getProgress(),
                slideColors.get(1).getProgress(),
                slideColors.get(2).getProgress());
        colorBoard.setBackgroundColor(color);
        return color;
    }


}