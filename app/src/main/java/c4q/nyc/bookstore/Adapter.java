package c4q.nyc.bookstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
    int position;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            holder.id.setText(lista.get(position).get("id").toString());
            holder.cat.setText(lista.get(position).get("cat").toString());
            holder.name.setText(lista.get(position).get("name").toString());
            holder.name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.author.setText(lista.get(position).get("author").toString());
           // holder.series.setText(lista.get(position).getJSONArray("series_t").getInt(0));
            holder.sequence.setText(lista.get(position).get("sequence_i").toString());
            holder.genre.setText(lista.get(position).get("genre_s").toString());
            holder.stock.setText(lista.get(position).get("inStock").toString());
            holder.price.setText("Price= $"+lista.get(position).get("price").toString());
            holder.price.setTextColor(context.getResources().getColor(R.color.money));
            holder.pages.setText(lista.get(position).get("pages_i").toString());

            //shows hide download button
//            if(lista.get(position).get("price").toString().equals("0") ){
//                holder.downloadButton.setVisibility(View.VISIBLE);
//            }

            holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.carBooks.add(lista.get(position));
                    lista.remove(position);
                    notifyDataSetChanged();
                }
            });

            if(lista.get(position).get("inStock").toString().equals("true")){
                holder.addCartBtn.setVisibility(View.VISIBLE);
            }
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=  new Intent(context, DetailsActivity.class);
                    try {
                        intent.putExtra("id", lista.get(position).get("id").toString());
                        intent.putExtra("cat", lista.get(position).get("cat").toString());
                        intent.putExtra("name", lista.get(position).get("name").toString());
                        intent.putExtra("author", lista.get(position).get("author").toString());
                        intent.putExtra("sequence_i", lista.get(position).get("sequence_i").toString());
                        intent.putExtra("genre_s", lista.get(position).get("genre_s").toString());
                        intent.putExtra("inStock", lista.get(position).get("inStock").toString());
                        intent.putExtra("price", lista.get(position).get("price").toString());
                        intent.putExtra("pages_i", lista.get(position).get("pages_i").toString());
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
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
        //Button downloadButton;
        Button addCartBtn;
        LinearLayout container;

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
          //  downloadButton= (Button) itemView.findViewById(R.id.downloadButton);
            container=(LinearLayout)itemView.findViewById(R.id.container);
            addCartBtn=(Button)itemView.findViewById(R.id.addCartBtn);
        }
    }
}
