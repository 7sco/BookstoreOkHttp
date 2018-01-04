package c4q.nyc.bookstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchET;
    TextView text;
    Button btn;
    HashMap<String, JSONObject> booksMap = new HashMap<>();

    public HashMap<String, JSONObject> getBooksMap() {
        return booksMap;
    }

    List<JSONObject> listaBooks= new ArrayList<>();

    private static String TAG = "MainActivity";
    protected MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        makeRequestWithOkHttp();


        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerContainer);


        Adapter adapter= new Adapter(listaBooks, this);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        // 1. Define the filter
        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                RSSPullService.BROADCAST_ACTION);

        // Adds a data filter for the HTTP scheme
        // statusIntentFilter.addDataScheme("http");

        // 2. Register the BroadcastReceiver and IntentFilter with the system
        // Instantiates a new DownloadStateReceiver
        DownloadStateReceiver mDownloadStateReceiver =
                new DownloadStateReceiver();
        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mDownloadStateReceiver,
                statusIntentFilter);
    }



    private void makeRequestWithOkHttp() {
        //String url = "https://raw.githubusercontent.com/tamingtext/book/master/apache-solr/example/exampledocs/books.json";
        String url = "https://gist.githubusercontent.com/justiceo/a7d373399a5e146104e9de3ee7987680/raw/c93dc5c11a8b8e7eddb63d269cc21cc37ad59006/book_store_data";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();



        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("FAIL===", "onFail: ");

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Log.d("RESULT===", "onResponse: "+jsonData);
                try {
                    //JSONObject jsonObject= new JSONObject(response.body().string());
//                            Log.d("RESULT===", "onResponse: "+jsonObject);
//
                    JSONArray jsonArray=new JSONArray(jsonData);
////
//                            Log.d("RESULT===", "onResponse: "+jsonArray.getJSONObject(1));
////
//                            List<JSONObject> list= new ArrayList<>();
////
//                            Log.d("ID===", "onResponse: "+jsonArray.getJSONObject(1).get("id"));
//
//
//                            for (int i=0; i<jsonArray.length();i++){
//                                list.add(jsonArray.getJSONObject(i));
//                                Log.d("LIST===", "onResponse: "+list.get(i)+"\n");
//                            }
//
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("id"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).getJSONArray("cat").get(0));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("name"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("author"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("series_t"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("sequence_i"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("genre_s"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("inStock"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("price"));
//                            Log.d("BOOK===", "onResponse: "+list.get(0).get("pages_i"));

                    for(int i=0; i<jsonArray.length(); i++){

                        booksMap.put(jsonArray.getJSONObject(i).get("name").toString().toLowerCase(),jsonArray.getJSONObject(i) );

                        listaBooks.add(jsonArray.getJSONObject(i));
                    }
//
                    String prueba="The Lightning Thief".toLowerCase();
                    Log.d("HASHMAP==", "onResponse: "+ booksMap.get(prueba)+"\n");


                    searchET=(EditText)findViewById(R.id.searchET);
                    text=(TextView)findViewById(R.id.text);


                    Log.d(TAG, "onResponse: NEWWWWW==="+listaBooks.size());

                    btn= (Button)findViewById(R.id.btn);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String texto= searchET.getText().toString().toLowerCase();
                            if(TextUtils.isEmpty(texto)){
                                Toast.makeText(getApplicationContext(), "Enter Data First", Toast.LENGTH_SHORT).show();
                            }
                            else if(findBook(texto, booksMap)==null ){
                                Toast.makeText(getApplicationContext(), "Cant Find the book", Toast.LENGTH_SHORT).show();

                            }
                            else {

                                String resultado="";

                                for(int i = 0; i<findBook(texto, booksMap).size(); i++){
                                    resultado+=findBook(texto, booksMap).get(i)+"\n";
                                }
//                                        String result= findBook(texto, booksMap);
//                                        Log.d("TEXTO=====", "onClick: "+result);
                                text.setText(resultado);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private List<String> findBook(String text, HashMap<String, JSONObject> listBooks) {

        List<String> lista=new ArrayList<>();

        if(listBooks.get(text) == null){
            return null;
        }

        //for each key set that cointains text return all objects

//        else  if(booksMap.keySet().contains(text)){
//            return booksMap.get(text).toString();
//        }


        for (String key:listBooks.keySet()) {
            if(key.contains(text.toLowerCase())){
                //add to the list

                lista.add(listBooks.get(key).toString());
            }
        }

        return lista;





        //return the list
//        else{
//            return booksMap.get(text).toString();
//        }

    }



    public void downloadInBackground(View v) {
        Intent mServiceIntent = new Intent(this, RSSPullService.class);
        mServiceIntent.setData(Uri.parse("http://google.com"));
        startService(mServiceIntent);
        Log.d(TAG, "fired off rsspullservice");
    }

    // Broadcast receiver for receiving status updates from the IntentService
    private class DownloadStateReceiver extends BroadcastReceiver
    {
        String TAG = "DownloadStateReceiver";
        // Prevents instantiation
        private DownloadStateReceiver() {
        }

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive fired");
            String data = intent.getStringExtra(RSSPullService.EXTENDED_DATA_STATUS);
            Toast.makeText(MainActivity.this, "Download complete", Toast.LENGTH_SHORT).show();

            Button downloadButton= (Button)findViewById(R.id.downloadButton);

            TextView tv = mainActivity.findViewById(R.id.textView);
            tv.setText("Last downloaded: " + data);


        }
    }
}
