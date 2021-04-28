package com.rasheek.thedock.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.rasheek.thedock.R;
import com.rasheek.thedock.models.Products;
import com.rasheek.thedock.ui.ProductDetail;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView product_list;
    private FirestoreRecyclerAdapter adapter;
    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        db = FirebaseFirestore.getInstance();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        product_list = root.findViewById(R.id.product_list);

        //query

        Query query = db.collection("products");

        //Recycler options
        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>().setQuery(query, Products.class).build();
         adapter = new FirestoreRecyclerAdapter<Products, ProdutsViewHolder>(options) {
            @NonNull
            @Override
            public ProdutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
                return new ProdutsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProdutsViewHolder holder, int position, @NonNull Products model) {

                holder.product_name.setText(model.getName());
                holder.product_price.setText("Rs " + model.getPrice() + "/-");
                Picasso.get().load(model.getCoverImage()).into(holder.product_cover);
                holder.product_cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent productDetailIntent = new Intent(getActivity(), ProductDetail.class );
                        productDetailIntent.putExtra("product", model );
                        startActivity(productDetailIntent);

    //                    Toast.makeText(getContext(), model.getName() , Toast.LENGTH_LONG).show();
                    }
                });

            }
        };

    product_list.setHasFixedSize(true);
    product_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
    product_list.setAdapter(adapter);
        return root;
    }

    private class ProdutsViewHolder extends  RecyclerView.ViewHolder {
        private  TextView product_name;
        private  TextView product_price;
        private ImageView product_cover;

        public ProdutsViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_cover = itemView.findViewById(R.id.product_cover);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}