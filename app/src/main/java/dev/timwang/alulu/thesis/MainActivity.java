package dev.timwang.alulu.thesis;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private MainPagerAdapter mainPagerAdapter;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private VoiceController voiceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);
        BottomAppBar bottomBar = findViewById(R.id.bar);
        setSupportActionBar(bottomBar);

        final TextView appBarTitle = findViewById(R.id.app_bar_title);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                appBarTitle.setText(mainPagerAdapter.getPageTitle(position));
                if (position == 6) {
                    voiceController.beginContent();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        voiceController = new VoiceController(this);
        fab = findViewById(R.id.fab);
        voiceController.setVoiceStatusChangeListener(new VoiceController.StatusChangeListener() {
            @Override
            public void onUpdated(int status) {
                if (status == VoiceController.VOICE_PLAYING) {
                    fab.setImageResource(R.drawable.ic_stop_black_24dp);
                    fab.setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.colorAccent)
                    ));
                } else {
                    fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    fab.setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.colorPrimary)
                    ));
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = voiceController.getVoiceStatus();
                if (status == VoiceController.VOICE_PLAYING) {
                    voiceController.stop();
                } else if (status == VoiceController.IDLE) {
                    voiceController.playSection("1");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VoiceController.SPEECH_REQUEST_CODE) {
            voiceController.onSpeechResult(resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_previous:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                return true;
            case R.id.action_next:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return true;
            case R.id.action_help:
                viewPager.setCurrentItem(0);
                voiceController.stop();
                return true;
            case R.id.action_toc:
                viewPager.setCurrentItem(4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
