package test.pandasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import strcture.ItemNew;
import strcture.ListData;




public class MainActivity extends AppCompatActivity {

    Button butttonLogin;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        butttonLogin = (Button) findViewById(R.id.buttonLogin);

        butttonLogin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ListNews.class);
                startActivity(i);
            }
        });


    }



}
