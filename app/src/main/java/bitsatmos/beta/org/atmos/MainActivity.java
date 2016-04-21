package bitsatmos.beta.org.atmos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Gravity;
import android.view.MenuItem;
import app.AppConfig;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout mDrawer;
    CustomActionBarDrawerToggle mDrawerToggle;
    NavigationView mNavigation;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent=new Intent(this, ScheduleUpdateService.class);
        //startService(intent);

        //todo add thy data
        addData();
        createToken();
        setContentView(R.layout.activity_main_final);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer, toolbar);
        mNavigation = (NavigationView) findViewById(R.id.navigationView);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        manager = getSupportFragmentManager();

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                manager.popBackStack("events", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.reachCampus:
                        /*fragment = new ReachCampus();
                        transaction.replace(R.id.container, fragment, "reachCampus");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);*/
                        Intent intent = new Intent(MainActivity.this, Maps.class);
                        startActivity(intent);

                        break;
                    case R.id.schedule:
                        fragment = new ScheduleFragmentHeader();
                        transaction.replace(R.id.container, fragment, "schedule");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.events:

                        fragment = new EventChooserFragment();
                        transaction.replace(R.id.container, fragment, "events");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.contactUs:

                        fragment = new ContactsFragment();
                        transaction.replace(R.id.container, fragment, "contacts");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.favourites:
                        fragment = new FavouriteFragment();
                        transaction.replace(R.id.container, fragment, "favourite");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.feed:
                        fragment=new FeedFragment();
                        transaction.replace(R.id.container,fragment,"feed");
                        transaction.commit();
                        menuItem.setChecked(true);
                        mDrawer.closeDrawer(Gravity.LEFT);


                }

                return false;
            }
        });



    }

    private void addData() {
        EventTableManager tableManager = new EventTableManager(this);
        tableManager.addEntry(
                1,
                "Electrical",
                "Technical Events",
                "Line & Wall Follower",
                1234567l,
                1234598l,
                "D-Block",
                "It's time to test your basic automation and robotics skill! A basic line follower robot is an automated vehicle capable of navigating on ground using wheels/legs following a guided path using sensors,from a starting line to a finish line on the track. \n",
                "",
                "http://bits-atmos.org/Events/img/mo_line%20follower.jpg",
                0,
                0d,
                1);
        tableManager.addEntry(2,
                "Electrical",
                "Technical Events",
                "Robowars",
                1234669l,
                1234998l,
                "D-Block",
                "The headliner, the crowd-puller, the pure visual euphoria in any ATMOS always had just one name - RoboWars! See the rebirth of the majestic medieval knights in their 21st century avatar. See the robots battle it all out in the mighty space at BITS Pilani Hyderabad Campus. Behold a futuristic projection of Gladiatorial days of combat. The war is about to begin. Tighten the screws, charge your batteries, oil the parts, it�s going to get messy. Are you ready?",
                "",
                "http://bits-atmos.org/Events/img/robowars.jpg",
                0,
                0d,
                0);
        tableManager.addEntry(3,
                "Electrical",
                "Technical Events",
                "Open Micro Challenge",
                1234669l,
                1234998l,
                "F-104",
                "If microprocessor and microcontrollers are your toys on interest, this contest is made for you. Combine your knowledge of digital electronics, microprocessors to create miracles and solve real life problems. With ticking clock and all your skills put at test, work on your preferred microcontroller platform and walk away with prizes this October. It's high time you gear up for the challenge, with all your concepts at their best and programming skills tuned up to the level of professionals. We present to all you Microprocessor fanatics the Open Micro Challenge! ",
                "",
                "http://bits-atmos.org/Events/img/openMicrochallenge.jpg",
                0,
                0d,
                0);
        tableManager.addEntry(4,
                "Electrical",
                "Technical Events",
                "Quadcopter Challenge",
                1234669l,
                1234998l,
                "Hockey Ground",
                "Design and build a wireless remote controlled flying drone to complete the tasks with highest points possible.\nThere are two rounds and a BONUS round in the competition. Top 10 teams from ROUND-1 are eligible to compete in ROUND-2. There is an optional BONUS round to show your outstanding design, maneuver capabilities.",
                "",
                "http://bits-atmos.org/Events/img/quadcopter.jpg",
                0,
                0d,
                1);


        tableManager.addEntry(5,
                "Computer Science",
                "Technical Events",
                "Googled",
                1234669l,
                1234998l,
                "IPC-3",
                "Information Systems Association in association with Quiz Club of Hyderabad presents Googled, an event where none but only Google can be your savior. So, if you know how to tame the beast, you are in for a roll!",
                "",
                "http://bits-atmos.org/Events/img/CSEgoogled.jpg",
                0,
                0d,
                0);

        tableManager.addEntry(6,
                "Mechanical",
                "Technical Events",
                "Mini-GP",
                1234669l,
                1234998l,
                "FootBall Ground",
                "I bet most of us have dreamt of racing our cars at top speeds through spick, black race courses. Come October and BITS-Pilani,Hyderabad Campus will be all set to fulfil your dream,well,in a way.On the line of the ecstatic Moto GP competition,we are conducting The Mini GP competition.The competition will have its participants (or racers) drive self-designed remote controlled cars through deliciously twisty race tracks to win the final cup! Seems simple?Well, brace yourselves for some rock solid competition. Forget not, the more innovative and inventive your design is, the closer you are to the Mini GP cup.Fasten your seatbelts and let the adrenaline pump up!",
                "",
                "http://bits-atmos.org/Events/img/miniGp.jpg",
                0,
                0d,
                1);
        tableManager.addEntry(7,
                "Economics",
                "Sciences",
                "The Court Room",
                1234669l,
                1234998l,
                "FootBall Ground",
                "I bet most of us have dreamt of racing our cars at top speeds through spick, black race courses. Come October and BITS-Pilani,Hyderabad Campus will be all set to fulfil your dream,well,in a way.On the line of the ecstatic Moto GP competition,we are conducting The Mini GP competition.The competition will have its participants (or racers) drive self-designed remote controlled cars through deliciously twisty race tracks to win the final cup! Seems simple?Well, brace yourselves for some rock solid competition. Forget not, the more innovative and inventive your design is, the closer you are to the Mini GP cup.Fasten your seatbelts and let the adrenaline pump up!",
                "",
                "http://bits-atmos.org/Events/img/courtroom.jpg",
                0,
                0d,
                0);
        tableManager.addEntry(8,
                "Maths",
                "Sciences",
                "Mathefia",
                1234669l,
                1234998l,
                "FootBall Ground",
                "I bet most of us have dreamt of racing our cars at top speeds through spick, black race courses. Come October and BITS-Pilani,Hyderabad Campus will be all set to fulfil your dream,well,in a way.On the line of the ecstatic Moto GP competition,we are conducting The Mini GP competition.The competition will have its participants (or racers) drive self-designed remote controlled cars through deliciously twisty race tracks to win the final cup! Seems simple?Well, brace yourselves for some rock solid competition. Forget not, the more innovative and inventive your design is, the closer you are to the Mini GP cup.Fasten your seatbelts and let the adrenaline pump up!",
                "",
                "http://bits-atmos.org/Events/img/mathefia.jpg",
                0,
                0d,
                1);


    }

    private void createToken() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(AppConfig.SENT_TOKEN_TO_SERVER, false)) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        } else {
            Intent intent = new Intent(this, MyInstanceIDListenerService.class);
            startService(intent);
        }
    }
    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {
        public CustomActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar) {
            super(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        }

        public CustomActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout) {
            super(activity, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        }

    }

}
