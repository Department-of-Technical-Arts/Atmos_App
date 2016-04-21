package bitsatmos.beta.org.atmos;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.text.Html;

import android.view.View;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class About_us_Activity extends AppCompatActivity {
    private TextView aboutBody;
    private TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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

        aboutBody = (TextView) findViewById(R.id.about_body);
        //aboutTitle = (TextView) findViewById(R.id.about_title);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(roboto_condensed_bold);
        toolbar_title.setTextSize(18);
        toolbar_title.setAllCaps(true);
        aboutBody.setText(Html.fromHtml(getString(R.string.about_body)));
        //aboutTitle.setTypeface(roboto_condensed_bold);
        aboutBody.setTypeface(roboto_condensed_regular);
        YoYo.with(Techniques.FadeInUp).delay(1000).playOn(aboutBody);
        //YoYo.with(Techniques.FadeInUp).delay(1000).playOn(aboutTitle);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
