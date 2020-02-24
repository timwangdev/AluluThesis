package dev.timwang.alulu.thesis;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SpeechRecognizer mSpeechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);
        BottomAppBar bottomBar = findViewById(R.id.bar);
        setSupportActionBar(bottomBar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_introduction,
                R.id.nav_chapter_1,
                R.id.nav_chapter_2,
                R.id.nav_conclusion,
                R.id.nav_reference
        )
                .setDrawerLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mSpeechRecognizer.setRecognitionListener(new CustomRecognitionListener());
        } else {
            Log.w("speech", "Speech recognition is not supported on this device.");
        }

    }

    class CustomRecognitionListener implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onResults(Bundle results) {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onRmsChanged(float rmsdB) {
        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        int id = item.getItemId();
        switch (id) {
            case R.id.action_previous:
                invalidateOptionsMenu();
                return true;
            case R.id.action_next:
                invalidateOptionsMenu();
                return true;
            case R.id.action_home:
                invalidateOptionsMenu();
                navController.navigate(R.id.nav_home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
