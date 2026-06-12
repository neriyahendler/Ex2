package com.arabic.learning;

import android.speech.tts.TextToSpeech;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        registerPlugin(NativeTTSPlugin.class);
        super.onCreate(savedInstanceState);
    }

    @CapacitorPlugin(name = "NativeTTS")
    public static class NativeTTSPlugin extends Plugin implements TextToSpeech.OnInitListener {

        private TextToSpeech tts;
        private boolean ttsReady = false;

        @Override
        public void load() {
            tts = new TextToSpeech(getContext(), this);
        }

        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("ar"));
                ttsReady = true;
            }
        }

        @PluginMethod
        public void speak(PluginCall call) {
            String text = call.getString("text", "");
            float rate = call.getFloat("rate", 0.75f);
            if (!ttsReady || text.isEmpty()) {
                call.resolve();
                return;
            }
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
}
