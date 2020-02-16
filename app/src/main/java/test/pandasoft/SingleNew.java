package test.pandasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView title,detail,dateCreate;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_new);

        Picasso.get().load(getIntent().getStringExtra("image").toString()).into((ImageView) findViewById(R.id.imageView));
        title = (TextView) findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title").toString());
        detail = (TextView) findViewById(R.id.detail);
        detail.setText(getIntent().getStringExtra("detail").toString());
        dateCreate = (TextView) findViewById(R.id.dateCreate);
        dateCreate.setText(getIntent().getStringExtra("dateCreate").toString());


    }
}
