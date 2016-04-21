package Svg;

import android.graphics.BlurMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.text.ParseException;

import bitsatmos.beta.org.atmos.R;

/**
 * Created by Sasikanth on 6/29/15.
 */
public class AnimatedSvgView extends View {

    private static final String TAG = "AnimatedSvgView";

    private int mTraceTime = 2000;
    private int mTraceTimePerGlyph = 1000;
    private int mFillStart = 1200;
    private int mFillTime = 1000;
    private static final int MARKER_LENGTH_DIP = 16;
    private int[] mTraceResidueColors;
    private int[] mTraceColors;
    private RatioSizingUtils.RatioSizingInfo mRatioSizingInfo = new RatioSizingUtils.RatioSizingInfo();
    private int mViewportWidth;
    private int mViewportHeight;
    private PointF mViewport = new PointF(mViewportWidth, mViewportHeight);

    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    private Paint mFillPaint;
    private int[] mFillAlphas;
    private int[] mFillReds;
    private int[] mFillGreens;
    private int[] mFillBlues;
    private GlyphData[] mGlyphData;
    private String[] mGlyphStrings;
    private float mMarkerLength;
    private int mWidth;
    private int mHeight;
    private long mStartTime;

    public static final int STATE_NOT_STARTED = 0;
    public static final int STATE_TRACE_STARTED = 1;
    public static final int STATE_FILL_STARTED = 2;
    public static final int STATE_FINISHED = 3;

    private int mState = STATE_NOT_STARTED;
    private OnStateChangeListener mOnStateChangeListener;

    public AnimatedSvgView(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedSvgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedSvgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressWarnings("NewApi")
    private void init(Context context, AttributeSet attrs) {
        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mMarkerLength = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                MARKER_LENGTH_DIP, getResources().getDisplayMetrics());

        mTraceColors = new int[1];
        mTraceColors[0] = Color.BLACK;
        mTraceResidueColors = new int[1];
        mTraceResidueColors[0] = Color.argb(50, 0, 0, 0);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimatedSvgView);

            mViewportWidth = a.getInt(R.styleable.AnimatedSvgView_oakSvgImageSizeX, 433);
            mRatioSizingInfo.aspectRatioWidth = a.getInt(R.styleable.AnimatedSvgView_oakSvgImageSizeX, 433);
            mViewportHeight = a.getInt(R.styleable.AnimatedSvgView_oakSvgImageSizeY, 433);
            mRatioSizingInfo.aspectRatioHeight = a.getInt(R.styleable.AnimatedSvgView_oakSvgImageSizeY, 433);

            mTraceTime = a.getInt(R.styleable.AnimatedSvgView_oakSvgTraceTime, 2000);
            mTraceTimePerGlyph = a.getInt(R.styleable.AnimatedSvgView_oakSvgTraceTimePerGlyph, 1000);
            mFillStart = a.getInt(R.styleable.AnimatedSvgView_oakSvgFillStart, 1200);
            mFillTime = a.getInt(R.styleable.AnimatedSvgView_oakSvgFillTime, 1000);

            a.recycle();

            mViewport = new PointF(mViewportWidth, mViewportHeight);
        }

        // See https://github.com/romainguy/road-trip/blob/master/application/src/main/java/org/curiouscreature/android/roadtrip/IntroView.java
        // Note: using a software layer here is an optimization. This view works with
        // hardware accelerated rendering but every time a path is modified (when the
        // dash path effect is modified), the graphics pipeline will rasterize the path
        // again in a new texture. Since we are dealing with dozens of paths, it is much
        // more efficient to rasterize the entire view into a single re-usable texture
        // instead. Ideally this should be toggled using a heuristic based on the number
        // and or dimensions of paths to render.
        // Note that PathDashPathEffects can lead to clipping issues with hardware rendering.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setViewportSize(int viewportWidth, int viewportHeight) {
        mViewportWidth = viewportWidth;
        mViewportHeight = viewportHeight;

        mRatioSizingInfo.aspectRatioWidth = viewportWidth;
        mRatioSizingInfo.aspectRatioHeight = viewportHeight;

        mViewport = new PointF(mViewportWidth, mViewportHeight);

        requestLayout();
    }

    public void setGlyphStrings(String[] glyphStrings) {
        mGlyphStrings = glyphStrings;
    }

    public void setTraceResidueColors(int[] traceResidueColors) {
        mTraceResidueColors = traceResidueColors;
    }

    public void setTraceColors(int[] traceColors) {
        mTraceColors = traceColors;
    }

    public void setFillPaints(int[] fillAlphas, int[] fillReds, int[] fillGreens, int[] fillBlues) {
        mFillAlphas = fillAlphas;
        mFillReds = fillReds;
        mFillGreens = fillGreens;
        mFillBlues = fillBlues;
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        changeState(STATE_TRACE_STARTED);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void reset() {
        mStartTime = 0;
        changeState(STATE_NOT_STARTED);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void setToFinishedFrame() {
        mStartTime = 1;
        changeState(STATE_FINISHED);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        rebuildGlyphData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        RatioSizingUtils.RatioMeasureInfo rmi = RatioSizingUtils
                .getMeasureInfo(widthMeasureSpec, heightMeasureSpec, mRatioSizingInfo, 0, 0);

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(rmi.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(rmi.height, MeasureSpec.EXACTLY));
    }

    private void rebuildGlyphData() {
        SvgPathParser parser = new SvgPathParser() {
            @Override
            protected float transformX(float x) {
                return x * mWidth / mViewport.x;
            }

            @Override
            protected float transformY(float y) {
                return y * mHeight / mViewport.y;
            }
        };

        mGlyphData = new GlyphData[mGlyphStrings.length];
        for (int i = 0; i < mGlyphStrings.length; i++) {
            mGlyphData[i] = new GlyphData();
            try {
                mGlyphData[i].path = parser.parsePath(mGlyphStrings[i]);
            } catch (ParseException e) {
                mGlyphData[i].path = new Path();
                Log.e(TAG, "Couldn't parse path", e);
            }
            PathMeasure pm = new PathMeasure(mGlyphData[i].path, true);
            while (true) {
                mGlyphData[i].length = Math.max(mGlyphData[i].length, pm.getLength());
                if (!pm.nextContour()) {
                    break;
                }
            }
            mGlyphData[i].paint = new Paint();
            mGlyphData[i].paint.setStyle(Paint.Style.STROKE);
            mGlyphData[i].paint.setAntiAlias(true);
            mGlyphData[i].paint.setColor(Color.WHITE);
            mGlyphData[i].paint.setStrokeWidth(
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                            getResources().getDisplayMetrics()));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState == STATE_NOT_STARTED || mGlyphData == null) {
            return;
        }

        long t = System.currentTimeMillis() - mStartTime;

        // Draw outlines (starts as traced)
        for (int i = 0; i < mGlyphData.length; i++) {
            float phase = MathUtil.constrain(0, 1,
                    (t - (mTraceTime - mTraceTimePerGlyph) * i * 1f / mGlyphData.length)
                            * 1f / mTraceTimePerGlyph);
            float distance = INTERPOLATOR.getInterpolation(phase) * mGlyphData[i].length;
            mGlyphData[i].paint.setColor(mTraceResidueColors[i]);
            mGlyphData[i].paint.setPathEffect(new DashPathEffect(
                    new float[]{distance, mGlyphData[i].length}, 0));
            canvas.drawPath(mGlyphData[i].path, mGlyphData[i].paint);

            mGlyphData[i].paint.setColor(mTraceColors[i]);
            mGlyphData[i].paint.setPathEffect(new DashPathEffect(
                    new float[]{0, distance, phase > 0 ? mMarkerLength : 0,
                            mGlyphData[i].length}, 0));
            //mGlyphData[i].paint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.NORMAL));
            canvas.drawPath(mGlyphData[i].path, mGlyphData[i].paint);
        }


        if (t > mFillStart) {
            if (mState < STATE_FILL_STARTED) {
                changeState(STATE_FILL_STARTED);
            }

            // If after fill start, draw fill
            float phase = MathUtil.constrain(0, 1, (t - mFillStart) * 1f / mFillTime);
            for (int i = 0; i < mGlyphData.length; i++) {
                GlyphData glyphData = mGlyphData[i];
                mFillPaint.setARGB((int) (phase * ((float) mFillAlphas[i] / (float) 255) * 255),
                        mFillReds[i],
                        mFillGreens[i],
                        mFillBlues[i]);

                canvas.drawPath(glyphData.path, mFillPaint);

                //added
                if (i == 0 || i==3 || i==4 || i==5 || i==6 || i==7 || i==8) {
                    Paint paint = new Paint();
                    paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.parseColor("#33ACE0"), Color.parseColor("#33ACE0"), Shader.TileMode.MIRROR));
                    canvas.drawPath(glyphData.path, paint);
                }
                //added

            }


            //added
            long t1 = System.currentTimeMillis();
            for (int i1 = 0; i1 < mGlyphData.length; i1++) {
                float phase1 = MathUtil.constrain(0, 1,
                        (t1 - (mTraceTime - mTraceTimePerGlyph) * i1 * 1f / mGlyphData.length)
                                * 1f / mTraceTimePerGlyph);
                float distance1 = INTERPOLATOR.getInterpolation(phase1) * mGlyphData[i1].length;
                mGlyphData[i1].paint.setColor(mTraceColors[i1]);
                mGlyphData[i1].paint.setPathEffect(new DashPathEffect(
                        new float[]{distance1, mGlyphData[i1].length}, 0));
                //canvas.drawPath(mGlyphData[i1].path, mGlyphData[i1].paint);
            }
            //added


        }

        if (t < mFillStart + mFillTime) {
            // draw next frame if animation isn't finished
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            changeState(STATE_FINISHED);
        }


    }

    private void changeState(int state) {
        if (mState == state) {
            return;
        }

        mState = state;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.onStateChange(state);
        }
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        mOnStateChangeListener = onStateChangeListener;
    }

    public static interface OnStateChangeListener {

        void onStateChange(int state);
    }

    private static class GlyphData {

        Path path;
        Paint paint;
        float length;
    }
}
