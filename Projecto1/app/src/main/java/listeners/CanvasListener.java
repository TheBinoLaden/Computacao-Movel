package listeners;

import android.view.GestureDetector;

import views.CanvasView;

public class CanvasListener extends GestureDetector.SimpleOnGestureListener {

    private CanvasView view;

    public void setCanvas(final CanvasView view) {
        this.view = view;
    }

}
