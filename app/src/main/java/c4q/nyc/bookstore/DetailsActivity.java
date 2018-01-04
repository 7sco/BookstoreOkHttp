package c4q.nyc.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView textDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textDetail=(TextView) findViewById(R.id.textDetail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String cat = intent.getStringExtra("cat");
        String name = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");
        String sequence_i = intent.getStringExtra("sequence_i");
        String genre_s = intent.getStringExtra("genre_s");
        String inStock = intent.getStringExtra("inStock");
        String price = intent.getStringExtra("price");
        String pages_i = intent.getStringExtra("pages_i");
        String total= id+"\n"+
                cat+"\n"+
                name+"\n"+
                author+"\n"+
                sequence_i+"\n"+
                genre_s+"\n"+
                inStock+"\n"+
                price+"\n"+
                pages_i+"\n";
        textDetail.setText(total);
    }
}
