package c4q.nyc.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by franciscoandrade on 12/18/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<JSONObject> lista;
    Context context;

    public Adapter(List<JSONObject> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        try {
            holder.id.setText(lista.get(position).get("id").toString());
            holder.cat.setText(lista.get(position).get("cat").toString());
            holder.name.setText(lista.get(position).get("name").toString());
            holder.author.setText(lista.get(position).get("author").toString());
           // holder.series.setText(lista.get(position).getJSONArray("series_t").getInt(0));

            //Log.d("RESULTADOO==", "onBindViewHolder: "+lista.get(position).getJSONArray("series_t"));
            holder.sequence.setText(lista.get(position).get("sequence_i").toString());
            holder.genre.setText(lista.get(position).get("genre_s").toString());
            holder.stock.setText(lista.get(position).get("inStock").toString());
            holder.price.setText("PRICE="+lista.get(position).get("price").toString());
            holder.pages.setText(lista.get(position).get("pages_i").toString());


            if(lista.get(position).get("price").toString().equals("0") ){
                holder.downloadButton.setVisibility(View.VISIBLE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, cat, name, author, series, sequence, genre, stock, price, pages;
        Button downloadButton;

        public ViewHolder(View itemView) {
            super(itemView);

            id= (TextView)itemView.findViewById(R.id.id);
            cat= (TextView)itemView.findViewById(R.id.cat);
            name= (TextView)itemView.findViewById(R.id.name);
            author= (TextView)itemView.findViewById(R.id.author);
            series= (TextView)itemView.findViewById(R.id.series);
            sequence= (TextView)itemView.findViewById(R.id.sequence);
            genre= (TextView)itemView.findViewById(R.id.genre);
            stock= (TextView)itemView.findViewById(R.id.stock);
            price= (TextView)itemView.findViewById(R.id.price);
            pages= (TextView)itemView.findViewById(R.id.pages);
            downloadButton= (Button) itemView.findViewById(R.id.downloadButton);



        }
    }
}
