package com.example.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

// on below line we are creating our adapter class
// in this class we are passing our array list
// and our View Holder class which we have created.
class CurrencyRvAdapter extends RecyclerView.Adapter<CurrencyRvAdapter.CurrencyViewholder> {
    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<CurrencyModel> currencyModals;
    private final Context context;



    public CurrencyRvAdapter(ArrayList<CurrencyModel> currencyModals, Context context) {
        this.currencyModals = currencyModals;
        this.context = context;

    }

    // below is the method to filter our list.
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<CurrencyModel> filterllist) {
        // adding filtered list to our
        // array list and notifying data set changed
        currencyModals = filterllist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CurrencyRvAdapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new CurrencyViewholder(view);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CurrencyRvAdapter.CurrencyViewholder holder, int position) {
        // on below line we are setting data to our item of
        // recycler view and all its views.
        CurrencyModel modal = currencyModals.get(position);
        holder.nameTV.setText(modal.getName());
        holder.rateTV.setText("$ " + df2.format(modal.getPrice()));
        holder.symbolTV.setText(modal.getSymbol());


    }

    @Override
    public int getItemCount() {
        // on below line we are returning
        // the size of our array list.
        return currencyModals.size();
    }

    // on below line we are creating our view holder class
    // which will be used to initialize each view of our layout file.
    public static class CurrencyViewholder extends RecyclerView.ViewHolder {
        private final TextView symbolTV;
        private final TextView rateTV;
        private final TextView nameTV;
        private final TextView favbtn;
        private final TextView favsymbol;
        private final TextView favName;
        private final TextView favRate;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initializing all
            // our text views along with its ids.
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVRate);
            nameTV = itemView.findViewById(R.id.idTVName);
            favbtn=itemView.findViewById(R.id.favbtn);
            favsymbol=itemView.findViewById(R.id.favsymbol);
            favName=itemView.findViewById(R.id.favName);
            favRate= itemView.findViewById(R.id.favRate);

            //add to favorites
            favbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAbsoluteAdapterPosition();

                }
            });

        }
    }
}
