package com.arabic.learning;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        registerPlugin(NativeTTSPlugin.class);
        registerPlugin(NativeSpeechPlugin.class);
        super.onCreate(savedInstanceState);
    }

    @CapacitorPlugin(name = "NativeTTS")
    public static class NativeTTSPlugin extends Plugin implements TextToSpeech.OnInitListener {

        private TextToSpeech tts;
        private boolean ttsReady = false;

        @Override
        public void load() { tts = new TextToSpeech(getContext(), this); }

        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) { tts.setLanguage(new Locale("ar")); ttsReady = true; }
        }

        @PluginMethod
        public void speak(PluginCall call) {
            String text = call.getString("text", "");
            float rate = call.getFloat("rate", 0.75f);
            if (!ttsReady || text.isEmpty()) { call.resolve(); return; }
            tts.setSpeechRate(rate);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            call.resolve();
        }

        @PluginMethod
        public void stop(PluginCall call) {
            if (ttsReady) tts.stop();
            call.resolve();
        }
    }

    @CapacitorPlugin(name = "NativeSpeech")
    public static class NativeSpeechPlugin extends Plugin {

        private static final int SPEECH_REQUEST = 7711;
        private PluginCall pendingCall = null;

        @PluginMethod
        public void startListening(PluginCall call) {
            call.setKeepAlive(true);
            this.pendingCall = call;
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            String lang = call.getString("language", "ar");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            getActivity().startActivityForResult(intent, SPEECH_REQUEST);
        }

        @Override
        protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
            super.handleOnActivityResult(requestCode, resultCode, data);
            if (requestCode != SPEECH_REQUEST || pendingCall == null) return;
            final PluginCall savedCall = pendingCall;
            pendingCall = null;
            if (resultCode == Activity.RESULT_OK && data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    JSObject ret = new JSObject();
                    ret.put("text", results.get(0));
                    JSArray all = new JSArray();
                    for (String r : results) all.put(r);
                    ret.put("alternatives", all);
                    savedCall.resolve(ret);
                    return;
                }
            }
            savedCall.reject("cancelled");
        }
    }
}
