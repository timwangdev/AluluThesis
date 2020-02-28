package dev.timwang.alulu.thesis;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class VoiceController {

    static final int IDLE = 0;
    static final int VOICE_PLAYING = 1;
    static final int SPEECH_LISTENING = 2;
    static final int SPEECH_REQUEST_CODE = 100;
    private static final int ASK_START = 3;
    private static final int ASK_CURRENT = 4;
    private static final int ASK_NEXT = 5;
    private static final int NOT_ASKING = 6;
    private MainActivity context;
    private MediaPlayer mediaPlayer;
    private int voiceStatus = IDLE;
    private int askCtx = ASK_START;
    private StatusChangeListener statusChangeListener;
    private List<Integer> audioResIdList = Arrays.asList(
//            R.raw.introduction_1,
//            R.raw.introduction_2
    );

    VoiceController(final MainActivity context) {
        this.context = context;
//        mediaPlayer = MediaPlayer.create(context, R.raw.prompt_start);
//        mediaPlayer.setVolume((float) 1.0, (float) 1.0);
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                setVoiceStatus(VOICE_PLAYING);
//                mp.start();
//            }
//        });
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                setVoiceStatus(IDLE);
//                if (askCtx != NOT_ASKING) {
//                    Intent intent = new Intent();
//                    intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
//                    intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
//                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "You could answer with \"yes\" or \"no\".");
//                    context.startActivityForResult(intent, SPEECH_REQUEST_CODE);
//                }
//            }
//        });
//        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                setVoiceStatus(IDLE);
//                mp.reset();
//                return true;
//            }
//        });
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

    private void setMediaSource(int id) {
//        try {
//            mediaPlayer.reset();
//            mediaPlayer.setDataSource(context.getResources().openRawResourceFd(id));
//            mediaPlayer.prepareAsync();
//        } catch (Exception e) {
//            Log.e("media-player", e.toString());
//        }
    }

    void onSpeechResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && result.get(0) != null
                    && Arrays.asList("yes", "ok", "sure", "yep", "yeah").contains(result.get(0))) {
                if (askCtx == ASK_START) {
//                    setMediaSource(R.raw.prompt_current);
                    askCtx = ASK_CURRENT;
                } else if (askCtx == ASK_CURRENT) {
                    setMediaSource(audioResIdList.get(context.getCurrentPage()));
                    askCtx = ASK_NEXT;
                } else if (askCtx == ASK_NEXT) {
                    setMediaSource(audioResIdList.get(context.getCurrentPage() + 1));
                    context.navToNext();
                }
            } else {
                askCtx = NOT_ASKING;
            }
        }
    }

    void playPrompt() {
        playPrompt(false);
    }

    void playPrompt(boolean forced) {
        if (forced || askCtx != NOT_ASKING) {
//            setMediaSource(R.raw.prompt_current);
            askCtx = ASK_NEXT;
        }
    }

    void stop() {
        if (askCtx != ASK_START) {
//            mediaPlayer.stop();
            setVoiceStatus(IDLE);
        }
    }

    public interface StatusChangeListener {
        void onUpdated(int status);
    }
}
