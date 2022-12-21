package com.example.volleyimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProduit extends RecyclerView.Adapter<AdapterProduit.Conteneur> {

    ArrayList<Produit> produitArrayList;
    Context context;

    public AdapterProduit(Context context, ArrayList<Produit> produitArrayList) {
        this.produitArrayList = produitArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterProduit.Conteneur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //recupere template .
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.items_produit, parent, false);

        return new Conteneur(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduit.Conteneur holder, int position) {

        Produit produit = produitArrayList.get(position);

        holder.txt_item_ref.setText(produit.getRef().toString());
        holder.txt_item_des.setText(produit.getDes().toString());
        holder.txt_item_prix.setText(String.valueOf(produit.getPrix()).toString());

    }

    @Override
    public int getItemCount() {
        return produitArrayList.size();
    }

    public class Conteneur extends RecyclerView.ViewHolder {

        TextView txt_item_ref, txt_item_des, txt_item_prix;

        public Conteneur(@NonNull View itemView) {
            super(itemView);

            txt_item_des = itemView.findViewById(R.id.txt_item_des);
            txt_item_ref = itemView.findViewById(R.id.txt_item_ref);
            txt_item_prix = itemView.findViewById(R.id.txt_item_prix);

        }
    }
}
