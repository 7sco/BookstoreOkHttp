package c4q.nyc.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    TextView textDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        showToolBar("Book Details", true);
        textDetail=(TextView) findViewById(R.id.textDetail);
        String total="";
        Bundle sendObject= getIntent().getExtras();
        Model model=null;
        if(sendObject!=null){
            model= (Model) sendObject.getSerializable("book");
            total= model.getId()+"\n"+
                    model.getName()+"\n"+
                    model.getAuthor()+"\n"+
                    model.getSequence_i()+"\n"+
                    model.getGenre_s()+"\n"+
                    model.getInStock()+"\n"+
                    model.getPrice()+"\n"+
                    model.getPages_i();
        }
        textDetail.setText(total);
    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
