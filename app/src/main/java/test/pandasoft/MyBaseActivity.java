package test.pandasoft;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MyBaseActivity extends AppCompatActivity {
    private static android.os.Handler handler = new android.os.Handler();
    private static Runnable runnable = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (handler == null) {
            handler = new Handler();
        } else {
            handler.removeCallbacks(runnable);
        }
        if (runnable == null)
            runnable = new Runnable() {
                @Override
                public void run() {
                    //do your task here
                }
            };
        start();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stop();
        start();

    }

    void start() {
        handler.postDelayed(runnable, 1000*5);
    }

    void stop() {
        handler.removeCallbacks(runnable);
    }

}
