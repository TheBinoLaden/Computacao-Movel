package views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasView extends View implements View.OnTouchListener {

    private static final String COLORPAINT = "colorPaint";
    private final GestureDetector mGestureDetector;
    private final Map<Path, Paint> pathHistory;
    private final Map<Integer, Path> pathIndex;
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private final int backGroundColor = Color.WHITE;
    private int color;

    public CanvasView(Context context, AttributeSet attrs, GestureDetector mGestureDetector) {
        super(context, attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);
        initPaint();
        pathHistory = new HashMap<>(0);
        pathIndex = new HashMap<>(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        final List<Paint> paints = new ArrayList<>(pathHistory.values());
        final List<Path> paths = new ArrayList(pathHistory.keySet());

        for (int i = 0; i < paints.size(); i++) {
            paint.setColor(paints.get(i).getColor());
            canvas.drawPath(paths.get(i), paint);// draws the path with the paint
        }
//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

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final SharedPreferences preferences =
                getContext().getSharedPreferences("SettingsPref", 0);
        final int tempColor = preferences.getInt(COLORPAINT, 0);
        if (tempColor != 0 && tempColor != this.color) {
            this.color = preferences.getInt(COLORPAINT, 0);
        }
        mGestureDetector.onTouchEvent(event);
        return false; // let the event go to the rest of the listeners
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);// updates the path initial point
                return true;
            case MotionEvent.ACTION_MOVE:
                // makes a line to the point each time this event is fired
                if (pathHistory.isEmpty()) {
                    pathIndex.put(0, path);
                } else {
                    pathIndex.put(pathIndex.size() + 1, path);
                }
                final Paint newPaint = new Paint();
                newPaint.setColor(color);
                pathHistory.put(new Path(path), newPaint);
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:// when you lift your finger
                performClick();
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public void erase() {
        paint.setColor(backGroundColor);
        pathHistory.clear();
        pathIndex.clear();
        invalidate();
    }

    private void initPaint() {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }


}
