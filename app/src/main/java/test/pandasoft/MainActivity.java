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
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import strcture.Item;
import strcture.MyDto;
import strcture.Page1;




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
//        jsonParse();


    }

    private void jsonParse() {

        String url = "https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/news";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            System.out.println("test : "+response.toString());
                            JSONArray jsonArray = response.getJSONArray("data");
                            Gson gson = new Gson();
                            Page1 page = gson.fromJson(response.toString(), Page1.class);
                            System.out.println(page.status);
                            for (Item item : page.data) {
                                System.out.println("ID : " + item.id);
                                System.out.println("ID : " + item.title);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }


}
