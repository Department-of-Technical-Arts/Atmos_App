package bitsatmos.beta.org.atmos;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ViewSwitcher;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

import com.nineoldandroids.animation.Animator;

import Svg.AnimatedSvgView;



public class MainActivitySample extends AppCompatActivity implements View.OnTouchListener {

    private Handler mHandler = new Handler();
    private AnimatorSet set;
    private AnimatedSvgView mAnimatedSvgView;
    private ImageView mSubtitleView;
    private float mInitialLogoOffset;
    private Runnable mOnFillStartedCallback;
    private FrameLayout homeLayout;
    private LinearLayout splashLinearLayout;
    private FrameLayout mRootView;

    private FrameLayout mRootView1;

    private FrameLayout mRootView2;

    private FrameLayout mRootView3;
    private int pressed_btn = 0;

    private Spring mScaleSpring;
    private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private final ExampleSpringListener mSpringListener = new ExampleSpringListener();
    private static final int TRANSITIONS_TO_SWITCH = 1;
    private ImageView home_logo;
    private ViewSwitcher mViewSwitcher;

    private int mTransitionsCount = 0;

    private ImageView twitter, facebook, youtube, google_plus;
    private Button contactUsBtn;
    private Button aboutUsBtn;
    private RippleView ripple_about_btn, ripple_contact_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutSplash);
        mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16,
                getResources().getDisplayMetrics());


        mViewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);

        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);


        homeLayout = (FrameLayout) findViewById(R.id.homeLayout);
        homeLayout.setVisibility(View.INVISIBLE);
        home_logo = (ImageView) findViewById(R.id.home_logo);
        home_logo.setVisibility(View.INVISIBLE);
        twitter = (ImageView) findViewById(R.id.twitter);
        twitter.setVisibility(View.INVISIBLE);
        facebook = (ImageView) findViewById(R.id.facebook);
        facebook.setVisibility(View.INVISIBLE);
        youtube = (ImageView) findViewById(R.id.youtube);
        youtube.setVisibility(View.INVISIBLE);
        google_plus = (ImageView) findViewById(R.id.google_plus);
        google_plus.setVisibility(View.INVISIBLE);
        aboutUsBtn = (Button) findViewById(R.id.aboutBtn);
        //aboutUsBtn.setVisibility(View.INVISIBLE);
        contactUsBtn = (Button) findViewById(R.id.contactUsBtn);
        //contactUsBtn.setVisibility(View.INVISIBLE);
        ripple_about_btn = (RippleView) findViewById(R.id.ripple_about_btn);
        ripple_contact_btn = (RippleView) findViewById(R.id.ripple_contact_btn);

        mScaleSpring = mSpringSystem.createSpring();

        mRootView = (FrameLayout) findViewById(R.id.root_viw_frame);
        mRootView1 = (FrameLayout) findViewById(R.id.root_viw_frame1);
        mRootView2 = (FrameLayout) findViewById(R.id.root_viw_frame2);
        mRootView3 = (FrameLayout) findViewById(R.id.root_viw_frame3);
        mRootView1.setOnTouchListener(this);
        mRootView.setOnTouchListener(this);
        mRootView2.setOnTouchListener(this);
        mRootView3.setOnTouchListener(this);
        /**
        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.TakingOff).playOn(home_logo);
                YoYo.with(Techniques.SlideOutLeft).playOn(ripple_about_btn);
                YoYo.with(Techniques.SlideOutRight).playOn(ripple_contact_btn);
                YoYo.with(Techniques.FlipOutX).playOn(twitter);
                YoYo.with(Techniques.FlipOutX).playOn(facebook);
                YoYo.with(Techniques.FlipOutX).playOn(youtube);
                YoYo.with(Techniques.FlipOutX).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(google_plus);

            }
        });**/
        ripple_contact_btn.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                YoYo.with(Techniques.TakingOff).playOn(home_logo);
                YoYo.with(Techniques.SlideOutLeft).playOn(ripple_about_btn);
                YoYo.with(Techniques.SlideOutRight).playOn(ripple_contact_btn);
                YoYo.with(Techniques.FlipOutX).playOn(twitter);
                YoYo.with(Techniques.FlipOutX).playOn(facebook);
                YoYo.with(Techniques.FlipOutX).playOn(youtube);
                YoYo.with(Techniques.FlipOutX).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MainActivitySample.this, ContactUsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(google_plus);

            }
        });
        /**
        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.TakingOff).playOn(home_logo);
                YoYo.with(Techniques.SlideOutLeft).playOn(ripple_about_btn);
                YoYo.with(Techniques.SlideOutRight).playOn(ripple_contact_btn);
                YoYo.with(Techniques.FlipOutX).playOn(twitter);
                YoYo.with(Techniques.FlipOutX).playOn(facebook);
                YoYo.with(Techniques.FlipOutX).playOn(youtube);
                YoYo.with(Techniques.FlipOutX).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MainActivity.this, About_us_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(google_plus);

            }
        });

         **/

        ripple_about_btn.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                YoYo.with(Techniques.TakingOff).playOn(home_logo);
                YoYo.with(Techniques.SlideOutLeft).playOn(ripple_about_btn);
                YoYo.with(Techniques.SlideOutRight).playOn(ripple_contact_btn);
                YoYo.with(Techniques.FlipOutX).playOn(twitter);
                YoYo.with(Techniques.FlipOutX).playOn(facebook);
                YoYo.with(Techniques.FlipOutX).playOn(youtube);
                YoYo.with(Techniques.FlipOutX).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MainActivitySample.this, About_us_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(google_plus);
            }
        });

        mSubtitleView = (ImageView) findViewById(R.id.wt_logo_bottom);
        //mSubtitleView.setVisibility(View.INVISIBLE);
        //mAnimatedSvgView.setTranslationY(mInitialLogoOffset);
        mAnimatedSvgView.setGlyphStrings(LogoPaths.LOGO_GLYPHS);
        mAnimatedSvgView.setFillPaints(
                new int[]{255, 0, 0, 255, 255, 255, 255, 255, 255},
                new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255},
                new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255},
                new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255});
        final int traceColor = Color.argb(255, 51, 172, 224);
        int[] traceColors = new int[9]; // 4 glyphs
        int residueColor = Color.argb(55, 51, 172, 224);
        int[] residueColors = new int[9]; // 4 glyphs

        // Every glyph will have the same trace/residue
        for (int i = 0; i < traceColors.length; i++) {
            traceColors[i] = traceColor;
            residueColors[i] = residueColor;
        }
        mAnimatedSvgView.setTraceColors(traceColors);
        mAnimatedSvgView.setTraceResidueColors(residueColors);

        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FINISHED) {

                    YoYo.with(Techniques.ZoomOut).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mTransitionsCount++;
                            if (mTransitionsCount == TRANSITIONS_TO_SWITCH) {
                                mViewSwitcher.showNext();
                                mTransitionsCount = 0;
                            }

                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    //homeLayout.setVisibility(View.VISIBLE);
                                    //home_logo.setVisibility(View.INVISIBLE);
                                    //YoYo.with(Techniques.SlideInLeft).duration(2000).playOn(aboutTextView);
                                    //YoYo.with(Techniques.SlideInRight).duration(2000).playOn(contactUsTextView);
                                    //YoYo.with(Techniques.Landing).duration(2000).playOn(home_logo);

                                }
                            }, 600);

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            home_logo.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.Landing).withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    twitter.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FlipInX).duration(1500).playOn(twitter);
                                    facebook.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FlipInX).duration(1500).playOn(facebook);
                                    youtube.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FlipInX).duration(1500).playOn(youtube);
                                    google_plus.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FlipInX).duration(1500).playOn(google_plus);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(home_logo);
                            homeLayout.setVisibility(View.VISIBLE);
                            //aboutUsBtn.setTextSize(18);
                            //contactUsBtn.setTextSize(18);

                            Typeface typeface = Typeface.createFromAsset(MainActivitySample.this.getAssets(), "fonts/roboto_condensed_bold.ttf");
                            aboutUsBtn.setTypeface(typeface);
                            contactUsBtn.setTypeface(typeface);
                            ripple_about_btn.setVisibility(View.VISIBLE);
                            ripple_contact_btn.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.SlideInLeft).playOn(ripple_about_btn);
                            YoYo.with(Techniques.SlideInRight).playOn(ripple_contact_btn);


                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).duration(3000).playOn(splashLinearLayout);


                }
                if (state == AnimatedSvgView.STATE_FILL_STARTED) {
                    mSubtitleView.setAlpha(0.0f);

                    mSubtitleView.setVisibility(View.VISIBLE);


                    mSubtitleView.setTranslationY(-mSubtitleView.getHeight());


                    set = new AnimatorSet();
                    Interpolator interpolator = new OvershootInterpolator();
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(mAnimatedSvgView, View.TRANSLATION_Y, 0);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(mSubtitleView, View.TRANSLATION_Y, 0);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(mSubtitleView, View.ALPHA, 1);
                    a1.setInterpolator(interpolator);
                    a2.setInterpolator(interpolator);
                    set.setDuration(500).playTogether(a1, a2, a3);
                    set.start();


                    if (mOnFillStartedCallback != null) {
                        mOnFillStartedCallback.run();

                    }
                }
            }
        });
        reset();
    }

    private void animateLogo() {
        mAnimatedSvgView.start();
    }

    public void setOnFillStartedCallback(Runnable fillStartedCallback) {
        mOnFillStartedCallback = fillStartedCallback;
    }

    public void reset() {
        mAnimatedSvgView.reset();
        mAnimatedSvgView.setTranslationY(mInitialLogoOffset);
        mSubtitleView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateLogo();
            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add a listener to the spring when the Activity resumes.
        mScaleSpring.addListener(mSpringListener);
        //twitter.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FlipInX).duration(1500).playOn(twitter);
        //facebook.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FlipInX).duration(1500).playOn(facebook);
        //youtube.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FlipInX).duration(1500).playOn(youtube);
        //google_plus.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FlipInX).duration(1500).playOn(google_plus);

        YoYo.with(Techniques.Landing).playOn(home_logo);
        YoYo.with(Techniques.SlideInRight).playOn(ripple_contact_btn);
        YoYo.with(Techniques.SlideInLeft).playOn(ripple_about_btn);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Remove the listener to the spring when the Activity pauses.
        mScaleSpring.removeListener(mSpringListener);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //Toast.makeText(HomeActivity.this, "ffff", Toast.LENGTH_LONG).show();

        switch (view.getId()) {
            case R.id.root_viw_frame:
                pressed_btn = 0;
                break;
            case R.id.root_viw_frame1:
                pressed_btn = 1;
                break;
            case R.id.root_viw_frame2:
                pressed_btn = 2;
                break;
            case R.id.root_viw_frame3:
                pressed_btn = 3;
                break;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When pressed start solving the spring to 1.
                mScaleSpring.setEndValue(1);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // When released start solving the spring to 0.
                mScaleSpring.setEndValue(0);
                break;
        }
        return true;
    }

    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            // On each update of the spring value, we adjust the scale of the image view to match the
            // springs new value. We use the SpringUtil linear interpolation function mapValueFromRangeToRange
            // to translate the spring's 0 to 1 scale to a 100% to 50% scale range and apply that to the View
            // with setScaleX/Y. Note that rendering is an implementation detail of the application and not
            // Rebound itself. If you need Gingerbread compatibility consider using NineOldAndroids to update
            // your view properties in a backwards compatible manner.
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);

            switch (pressed_btn) {
                case 0:
                    twitter.setScaleX(mappedValue);
                    twitter.setScaleY(mappedValue);
                    break;
                case 1:
                    facebook.setScaleX(mappedValue);
                    facebook.setScaleY(mappedValue);
                    break;
                case 2:
                    youtube.setScaleX(mappedValue);
                    youtube.setScaleY(mappedValue);
                    break;
                case 3:
                    google_plus.setScaleX(mappedValue);
                    google_plus.setScaleY(mappedValue);
                    break;
            }

        }

        @Override
        public void onSpringAtRest(Spring spring) {
            super.onSpringAtRest(spring);
            String url = "";
            switch (pressed_btn) {
                case 0:
                    url = "https://twitter.com/BITSAtmos";
                    break;
                case 1:
                    url = "https://www.facebook.com/bits.atmos";
                    break;
                case 2:
                    url = "https://www.youtube.com/user/dovebitshyd";
                    break;
                case 3:
                    url = "https://plus.google.com/111790318958389806399/posts";
                    break;
            }
            //Toast.makeText(HomeActivity.this, "Rest", Toast.LENGTH_LONG).show();
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(youtubeIntent);

            overridePendingTransition(R.anim.fade_in_web, R.anim.fade_out_web);


        }
    }

}
