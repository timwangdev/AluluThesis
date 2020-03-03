package dev.timwang.alulu.thesis;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
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

    private OnOffReceiver screenReceiver;
    private MainPagerAdapter mainPagerAdapter;
    private CustomViewPager viewPager;
    private BottomAppBar bottomBar;
    private FloatingActionButton fab;
    private VoiceController voiceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        screenReceiver = new OnOffReceiver();
        registerReceiver(screenReceiver, filter);

        final View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bar);
        setSupportActionBar(bottomBar);
        bottomBar.setVisibility(View.INVISIBLE);

        fab = findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        final TextView appBarTitle = findViewById(R.id.app_bar_title);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                appBarTitle.setText(mainPagerAdapter.getPageTitle(position));
                if (position > 3) {
                    bottomBar.setVisibility(View.VISIBLE);
                    viewPager.setPagingEnabled(true);
                    voiceController.playPage(position, true);
                }
                if (position > 4) {
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        voiceController = new VoiceController(this);
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
                    viewPager.setPagingEnabled(true);
                    voiceController.stop();
                } else if (status == VoiceController.IDLE) {
                    voiceController.playPage(viewPager.getCurrentItem(), false);
                }
            }
        });

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                AudioManager.FLAG_SHOW_UI
        );
    }

    @Override
    protected void onResume() {
        if (!OnOffReceiver.wasScreenOn) {
            Log.w("screen", "SCREEN TURNED ON");
            voiceController.stop();
            Intent i = getBaseContext().getPackageManager().
                    getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        voiceController.finish();
        if (screenReceiver != null) {
            unregisterReceiver(screenReceiver);
            screenReceiver = null;
        }
        super.onDestroy();
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
                viewPager.setCurrentItem(1);
                voiceController.stop();
                return true;
            case R.id.action_toc:
                viewPager.setCurrentItem(4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void scrollTo(int i) {
        viewPager.setCurrentItem(i);
    }
}
