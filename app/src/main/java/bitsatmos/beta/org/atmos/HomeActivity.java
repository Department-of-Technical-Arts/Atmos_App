package bitsatmos.beta.org.atmos;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nineoldandroids.animation.Animator;

public class HomeActivity extends AppCompatActivity {

    private RippleView more1, more2;
    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        more1 = (RippleView) findViewById(R.id.more1);
        more2 = (RippleView) findViewById(R.id.more2);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_condensed_bold.ttf");
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.SlideOutLeft).playOn(more1);

                YoYo.with(Techniques.SlideOutRight).playOn(more2);
            }
        });
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.SlideOutRight).playOn(more2);

                YoYo.with(Techniques.SlideOutLeft).playOn(more1);

            }
        });
    }
}

