package listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

import views.CanvasView;

public class CanvasListener extends GestureDetector.SimpleOnGestureListener {

    private CanvasView view;

    public void setCanvas(final CanvasView view) {
        this.view = view;
    }

    ////////SimpleOnGestureListener
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        view.changeBackground();
    }

    /////////OnDoubleTapListener
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        view.erase();
        return false;
    }


}
