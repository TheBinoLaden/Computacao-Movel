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
import java.util.Random;

public class CanvasView extends View implements View.OnTouchListener {

    private static final String COLORPAINT = "colorPaint";
    private final GestureDetector mGestureDetector;
    private final Random rand;
    private final Map<Path, Paint> pathHistory;
    private final Map<Integer, Path> pathIndex;
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private int color;
    private int backGroundColor = Color.WHITE;

    public CanvasView(Context context, AttributeSet attrs, GestureDetector mGestureDetector) {
        super(context, attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);
        this.rand = new Random();
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
                final Path newPath = new Path(path);
                if (pathHistory.isEmpty()) {
                    pathIndex.put(0, newPath);
                } else {
                    pathIndex.put(pathIndex.size() + 1, newPath);
                }
                final Paint newPaint = new Paint();
                pathHistory.put(newPath, newPaint);
                newPath.lineTo(eventX, eventY);
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

    public void changeBackground() {
        backGroundColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        setBackgroundColor(backGroundColor);
    }

    public void erase() {
        paint.setColor(backGroundColor);
    }

    private void initPaint() {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

}
