package fragments;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import views.CanvasView;

/**
 * create an instance of this fragment.
 */
public class CanvasFragment extends Fragment {

    CanvasView canvasView;
    GestureDetector detector;

    public CanvasFragment(final GestureDetector detector) {
        this.detector = detector;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.canvasView = new CanvasView(container.getContext(), null, this.detector);
        return canvasView;
    }

}