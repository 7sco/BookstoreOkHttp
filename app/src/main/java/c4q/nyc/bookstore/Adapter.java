package c4q.nyc.bookstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
/**
 * Created by franciscoandrade on 12/18/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Model> lista;
    Context context;
    public Adapter(List<Model> lista, Context context) {
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
        holder.id.setText(lista.get(position).getId());
        holder.name.setText(lista.get(position).getName());
        holder.name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        holder.author.setText(lista.get(position).getAuthor());
        holder.sequence.setText(lista.get(position).getSequence_i()+"");
        holder.genre.setText(lista.get(position).getGenre_s());
        holder.stock.setText(lista.get(position).getInStock().toString());
        holder.price.setText("Price= $"+lista.get(position).getPrice());
        holder.price.setTextColor(context.getResources().getColor(R.color.money));
        holder.pages.setText(lista.get(position).getPages_i()+"");
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

        if(lista.get(position).getInStock()){
            holder.addCartBtn.setVisibility(View.VISIBLE);
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(context, DetailsActivity.class);
                //JSONObject object= lista.get(position);
                Model model= lista.get(position);
                Bundle bundle= new Bundle();
                bundle.putSerializable("book", model);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
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
