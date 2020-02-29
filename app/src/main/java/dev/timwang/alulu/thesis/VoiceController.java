package dev.timwang.alulu.thesis;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class VoiceController {

    static final int IDLE = 0;
    static final int VOICE_PLAYING = 1;
    static final int SPEECH_LISTENING = 2;
    static final int SPEECH_REQUEST_CODE = 100;
    private static final int ASK_ENABLE = 3;
    private static final int ASK_NEXT = 4;
    private static final int NOT_ASKING = 5;
    private MainActivity context;
    private MediaPlayer mediaPlayer;
    private int voiceStatus = IDLE;
    private int askCtx = ASK_ENABLE;
    private String current = "title";
    private StatusChangeListener statusChangeListener;
    private HashMap<String, Integer> audioMap;

    VoiceController(final MainActivity context) {
        audioMap = new HashMap<>();
        audioMap.put("title", R.raw.title);
        audioMap.put("instruction", R.raw.instruction);
        audioMap.put("abstract", R.raw.abstract_page);
        audioMap.put("content", R.raw.content);
        audioMap.put("yes_reply", R.raw.yes);
        audioMap.put("no_reply", R.raw.no);
        audioMap.put("end_of_section", R.raw.end_of_section);
        audioMap.put("sample_section", R.raw.sample_section);

        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.title);
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
                switch (current) {
                    default:
                        current = null;
                        if (askCtx != NOT_ASKING) {
                            Intent intent = new Intent();
                            intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
                            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please answer with \"yes\" or \"no\".");
                            context.startActivityForResult(intent, SPEECH_REQUEST_CODE);
                        }
                        break;
                    case "title":
                        setMediaSource("instruction");
                        break;
                    case "instruction":
                        setMediaSource("abstract");
                        break;
                    case "abstract":
                        setMediaSource("content");
                        break;
                    case "yes_reply":
                        break;
                }
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

    private void setMediaSource(String fid) {
        Integer rawId = audioMap.get(fid);
        if (rawId == null) return;
        AssetFileDescriptor afd = context.getResources().openRawResourceFd(rawId);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(afd);
            mediaPlayer.prepareAsync();
            current = fid;
        } catch (Exception e) {
            Log.e("media-player", e.toString());
        }
    }

    void onSpeechResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            boolean isYes = result != null && result.get(0) != null
                    && Arrays.asList("yes", "ok", "sure", "yep", "yeah").contains(result.get(0));

            if (askCtx == ASK_ENABLE) {
                if (isYes) {
                    askCtx = ASK_NEXT;
                    setMediaSource("yes_reply");
                } else {
                    setMediaSource("no_reply");
                    askCtx = NOT_ASKING;
                }
            } else if (askCtx == ASK_NEXT) {
                if (isYes) {
                    setMediaSource("sample_section");
                } else {
                    setMediaSource("no_reply");
                    askCtx = NOT_ASKING;
                }
            }
        }
    }

    void beginContent() {
        if (askCtx != NOT_ASKING) {
            playSection("1");
        }
    }

    void playSection(String id) {
        mediaPlayer.stop();
        askCtx = ASK_NEXT;
        setMediaSource("sample_section");
    }

    void stop() {
        mediaPlayer.stop();
        askCtx = NOT_ASKING;
        setVoiceStatus(IDLE);
    }

    public interface StatusChangeListener {
        void onUpdated(int status);
    }
}
