package bitsatmos.beta.org.atmos;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ContactUsActivity extends AppCompatActivity {
    private ScrollView main_root;
    private TextView toolbar_title;
    private TextView deppTitle, dosmTitle, tcTitle;
    private TextView deppCoodTitle, dosmCoodTitle;
    private TextView deppPhnoTitle, dosmPhnoTitle, tcPhnoTitle;
    private TextView deppCood, dosmCood, tc;
    private TextView deppPhnum, dosmPhnum, tcPhnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


        ((Toolbar) findViewById(R.id.app_bar)).setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        finish();
                    }
                });

        Typeface roboto_condensed_bold = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_condensed_bold.ttf");
        Typeface roboto_condensed_regular = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_condensed_regular.ttf");
        Typeface roboto_condensed_light = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_condensed_light.ttf");
        Typeface roboto_medium = Typeface.createFromAsset(this.getAssets(), "fonts/roboto_medium.ttf");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(roboto_condensed_bold);
        toolbar_title.setTextSize(18);
        toolbar_title.setAllCaps(true);
        deppTitle = (TextView) findViewById(R.id.depp_title);
        deppTitle.setTypeface(roboto_condensed_bold);
        deppCoodTitle = (TextView) findViewById(R.id.depp_cood_title);
        deppCoodTitle.setTypeface(roboto_medium);
        deppPhnoTitle = (TextView) findViewById(R.id.depp_phnum_title);
        deppPhnoTitle.setTypeface(roboto_medium);
        deppCood = (TextView) findViewById(R.id.depp_cood);
        deppCood.setTypeface(roboto_condensed_regular);
        deppPhnum = (TextView) findViewById(R.id.depp_phnum);
        deppPhnum.setTypeface(roboto_condensed_regular);


        dosmTitle = (TextView) findViewById(R.id.dosm_title);
        dosmTitle.setTypeface(roboto_condensed_bold);
        dosmCoodTitle = (TextView) findViewById(R.id.dosm_cood_title);
        dosmCoodTitle.setTypeface(roboto_medium);
        dosmPhnoTitle = (TextView) findViewById(R.id.dosm_phnum_title);
        dosmPhnoTitle.setTypeface(roboto_medium);
        dosmCood = (TextView) findViewById(R.id.dosm_cood);
        dosmCood.setTypeface(roboto_condensed_regular);
        dosmPhnum = (TextView) findViewById(R.id.dosm_phnum);
        dosmPhnum.setTypeface(roboto_condensed_regular);

        tcTitle = (TextView) findViewById(R.id.tc_title);
        tcTitle.setTypeface(roboto_condensed_bold);
        tc = (TextView) findViewById(R.id.tc);
        tc.setTypeface(roboto_condensed_regular);
        tcPhnoTitle = (TextView) findViewById(R.id.tc_phnum_title);
        tcPhnoTitle.setTypeface(roboto_medium);
        tcPhnum = (TextView) findViewById(R.id.tc_phnum);
        tcPhnum.setTypeface(roboto_condensed_regular);

        main_root = (ScrollView) findViewById(R.id.rootScroll);
        //main_root.setVisibility(View.VISIBLE);
        //YoYo.with(Techniques.SlideInDown).playOn(main_root);

    }


}
