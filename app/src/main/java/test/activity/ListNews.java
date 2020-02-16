package test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import strcture.ListItem;
import strcture.ListData;

public class ListNews extends AppCompatActivity {

    LinearLayout parent_LinearLayout ;
    ListData page;
    private static android.os.Handler handler = new android.os.Handler();
    private static Runnable runnable = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

//      test
        new SimpleTask().execute("https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/news");
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
                    Intent i = new Intent(ListNews.this, MainActivity.class);
                    startActivity(i);
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
        handler.postDelayed(runnable,  TimeUnit.MINUTES.toMillis(10));
    }

    void stop() {
        handler.removeCallbacks(runnable);
    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String result)  {
            // Dismiss ProgressBar
//        updateWebView(result);
            Gson gson = new Gson();
            page = gson.fromJson(result.toString(), ListData.class);

            for (final ListItem item : page.data) {
//            Get the reference from XML layout
                parent_LinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

//        Create chile of parent
                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                lp.setMargins(0,0,0,100);
                linearLayout.setLayoutParams(lp);
                linearLayout.setBackgroundResource(R.drawable.border);
                linearLayout.setGravity(Gravity.CENTER);
                linearLayout.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent i = new Intent(ListNews.this, SingleNew.class);
                        i.putExtra("image",item.image);
                        i.putExtra("title",item.title);
                        i.putExtra("detail",item.detail);
                        i.putExtra("dateCreate",item.create);
                        startActivity(i);
                    }
                });

//        create ImageView
                ImageView imageView = new ImageView(getApplicationContext());
                LinearLayout.LayoutParams lp_ImageView = new LinearLayout.LayoutParams(
                        300,
                        300);
                lp_ImageView.setMargins(40, 40, 10, 40);
                imageView.setPadding(1, 1, 1, 1);
                imageView.setLayoutParams(lp_ImageView);
//        imageView.setId(1);

//        imageView.setBackgroundColor(Color.rgb(255,255,255));
                Picasso.get().load(item.image).into(imageView);


//        create TextView
                TextView textView = new TextView(getApplicationContext());
                LinearLayout.LayoutParams lp_textView = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                lp_textView.setMargins(10, 10, 10, 10);
                textView.setLayoutParams(lp_textView);
                textView.setTextSize(pxFromDp(ListNews.this, 5));
                textView.setText(item.title);
                textView.setTextColor(Color.rgb(0, 0, 0));
                textView.setTypeface(null, Typeface.BOLD);
                textView.setGravity(Gravity.CENTER);

//      Add to LinearLayout
                linearLayout.addView(imageView);
                linearLayout.addView(textView);
                parent_LinearLayout.addView(linearLayout);
            }
        }
    }



    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}




