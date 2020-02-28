package dev.timwang.alulu.thesis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    static int DELAY = 3000;
    static float PLAY_SPEED = 0.6f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        VideoView videoView = findViewById(R.id.launchVideo);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.anime_eye));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(PLAY_SPEED));
                mp.setLooping(true);
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                jump();
                return true;
            }
        });
        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        }, DELAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    public void jump() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
