package com.example.volleyimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edit_ref, edit_des, edit_prix;
    Button btn_ajouter, btn_modifier, btn_supprimer, btn_afficher;
    RecyclerView recycler_produits;

    ArrayList<Produit> produitArrayList;
    AdapterProduit adapterProduit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_des = findViewById(R.id.edit_des);
        edit_ref = findViewById(R.id.edit_ref);
        edit_prix = findViewById(R.id.edit_prix);

        btn_afficher = findViewById(R.id.btn_afficher);
        btn_ajouter = findViewById(R.id.btn_ajouter);
        btn_modifier = findViewById(R.id.btn_modifier);
        btn_supprimer = findViewById(R.id.btn_supprimer);

        recycler_produits = findViewById(R.id.recycler_produits);


        //Recycle view
        produitArrayList = new ArrayList<Produit>();
        adapterProduit = new AdapterProduit(this, produitArrayList);
        recycler_produits.setAdapter(adapterProduit);

        // Pour lignie les listes .
        recycler_produits.setLayoutManager(new LinearLayoutManager(this));

        String urlPr = "http://192.168.1.101:8088/scripts/";
        //String urlPr = "https://upfstock.000webhostapp.com/";




        btn_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ref = edit_ref.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url =  urlPr + "Suppression.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("ref", edit_ref.getText().toString());

                        return params;
                    }
                };
                queue.add(request);

            }
        });

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ref = edit_ref.getText().toString();
                String des = edit_des.getText().toString();
                String prix = edit_prix.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = urlPr + "MAJ.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("ref", edit_ref.getText().toString());
                        params.put("des", edit_des.getText().toString());
                        params.put("prix", edit_prix.getText().toString());

                        return params;
                    }
                };
                queue.add(request);

            }
        });

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ref = edit_ref.getText().toString();
                String des = edit_des.getText().toString();
                int prix = Integer.parseInt(edit_prix.getText().toString());
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = urlPr + "Ajout.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("ref", edit_ref.getText().toString());
                        params.put("des", edit_des.getText().toString());
                        params.put("prix", edit_prix.getText().toString());

                        return params;
                    }
                };
                queue.add(request);
            }
        });

        btn_afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = urlPr + "selection.php";

                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Produit produit = new Produit();
                                produit.setRef(object.getString("rf").toString());
                                produit.setDes(object.getString("dg").toString());
                                produit.setPrix(object.getInt("prix"));

                                produitArrayList.add(produit);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        adapterProduit.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });

    }
}