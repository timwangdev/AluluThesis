package dev.timwang.alulu.thesis;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

class VoiceController {

    static final int IDLE = 0;
    static final int VOICE_PLAYING = 1;
    static final int SPEECH_REQUEST_CODE = 100;
    private static final int ASK_ENABLE = 3;
    private static final int ASK_NEXT = 4;
    private static final int NOT_ASKING = 5;
    private MainActivity context;
    private MediaPlayer mediaPlayer;
    private int voiceStatus = IDLE;
    private int askCtx = ASK_ENABLE;
    private int current = 0;
    private Handler currentHdl;
    private StatusChangeListener statusChangeListener;

    VoiceController(final MainActivity context) {

        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.page_0);
        mediaPlayer.setScreenOnWhilePlaying(true);
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                setVoiceStatus(VOICE_PLAYING);
                mp.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                setVoiceStatus(IDLE);
                if (askCtx == ASK_ENABLE) {
                    switch (current) {
                        case 0:
                            setMediaSource(1);
                            context.scrollTo(1);
                            return;
                        case 1:
                            setMediaSource(2);
                            context.scrollTo(2);
                            return;
                        case 2:
                            if (currentHdl != null) currentHdl.removeCallbacksAndMessages(null);
                            currentHdl = new Handler();
                            currentHdl.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setMediaSource(3);
                                    context.scrollTo(3);
                                }
                            }, 25000);
                            return;
                        case 3:
                            if (currentHdl != null) currentHdl.removeCallbacksAndMessages(null);
                            currentHdl = new Handler();
                            currentHdl.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setMediaSource(4);
                                    context.scrollTo(4);
                                }
                            }, 10000);
                            return;
                        case 4:
                            startSpeechIntent();
                            return;
                    }
                }
                if (Arrays.asList(7, 10, 17, 19, 25, 27, 31, 33).contains(current)) {
                    startSpeechIntent();
                }
                current = -1;
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                setVoiceStatus(IDLE);
                mp.reset();
                return true;
            }
        });
    }

    private void startSpeechIntent() {
        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please answer with \"yes\" or \"no\".");
        context.startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    int getVoiceStatus() {
        return voiceStatus;
    }

    private void setVoiceStatus(int voiceStatus) {
        if (statusChangeListener != null && voiceStatus != this.voiceStatus) {
            statusChangeListener.onUpdated(voiceStatus);
        }
        this.voiceStatus = voiceStatus;
    }

    void setVoiceStatusChangeListener(StatusChangeListener listener) {
        this.statusChangeListener = listener;
    }

    private void setMediaSource(int fid) {
        int resId;
        if (fid == -1) resId = R.raw.yes;
        else if (fid == -2) resId = R.raw.no;
        else resId = context.getResources().getIdentifier(
                    "page_" + fid,
                    "raw",
                    context.getPackageName()
            );
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context.getResources().openRawResourceFd(resId));
            mediaPlayer.prepareAsync();
            current = fid;
        } catch (Exception e) {
            Log.e("VoiceController", e.toString());
        }
    }

    void onSpeechResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            boolean ok = result != null && result.get(0) != null && Arrays.asList("yes", "ok").contains(result.get(0));
            if (ok) {
                if (askCtx == ASK_ENABLE) {
                    setMediaSource(-1);
                }
                askCtx = ASK_NEXT;
                return;
            }
        }
        setMediaSource(-2);
        setVoiceStatus(IDLE);
        askCtx = NOT_ASKING;
    }

    void playPage(final int id, boolean auto) {
        if (currentHdl != null) currentHdl.removeCallbacksAndMessages(null);
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        if (id == 4) return;
        if (auto) {
            if (askCtx != ASK_NEXT) return;
            setVoiceStatus(VOICE_PLAYING);
            currentHdl = new Handler();
            currentHdl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    playPage(id);
                }
            }, 1500);
        } else {
            playPage(id);
        }
    }

    private void playPage(int id) {
        askCtx = ASK_NEXT;
        setMediaSource(id);
    }

    void stop() {
        if (currentHdl != null) currentHdl.removeCallbacksAndMessages(null);
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        current = -1;
        askCtx = NOT_ASKING;
        setVoiceStatus(IDLE);
    }

    void finish() {
        mediaPlayer.release();
    }

    public interface StatusChangeListener {
        void onUpdated(int status);
    }
}
