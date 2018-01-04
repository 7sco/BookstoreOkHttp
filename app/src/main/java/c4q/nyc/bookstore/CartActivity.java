package c4q.nyc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class CartActivity extends AppCompatActivity {
    TextView cartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartText = findViewById(R.id.cartText);
        try {
            String text = "";
            for (int i = 0; i < MainActivity.carBooks.size(); i++) {
                text += MainActivity.carBooks.get(i).get("name").toString() + "\n";
            }
            cartText.setText(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
