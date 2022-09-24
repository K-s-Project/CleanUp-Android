package com.example.cleanup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

// Create the adapter by extending RecyclerView.Adapter. This custom ViewHolder will give access to your views
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    // Declare variables to store data from the constructor
    Context context;
    String[] programNameList1;
    String[] programNameList2;
    String[] programNameList3;
    int[] images1;
    int[] images2;
    int[] images3;

    // Create a static inner class and provide references to all the Views for each data item.
    // This is particularly useful for caching the Views within the item layout for fast access.
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Declare member variables for all the Views in a row
        TextView rowName1;
        TextView rowName2;
        TextView rowName3;
        ImageView rowImage1;
        ImageView rowImage2;
        ImageView rowImage3;
        // Create a constructor that accepts the entire row and search the View hierarchy to find each subview
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Store the item subviews in member variables
            rowName1 = itemView.findViewById(R.id.textView1);
            rowName2 = itemView.findViewById(R.id.textView2);
            rowName3 = itemView.findViewById(R.id.textView3);
            rowImage1 = itemView.findViewById(R.id.imageView1);
            rowImage2 = itemView.findViewById(R.id.imageView2);
            rowImage3 = itemView.findViewById(R.id.imageView3);
        }
    }
    // Provide a suitable constructor
    public ProgramAdapter(Context context, String[] programNameList1, String[] programNameList2, String[] programNameList3, int[] images1, int[] images2, int[] images3){
        // Initialize the class scope variables with values received from constructor
        this.context = context;
        this.programNameList1 = programNameList1;
        this.programNameList2 = programNameList2;
        this.programNameList3 = programNameList3;
        this.images1 = images1;
        this.images2 = images2;
        this.images3 = images3;
    }

    // Create new views to be invoked by the layout manager
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a LayoutInflater object
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.single_item, parent, false);
        // To attach OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView rowName = v.findViewById(R.id.textView1);
                Toast.makeText(context, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view to be invoked by the layout manager
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the View with that element
        holder.rowName1.setText(programNameList1[position]);
        holder.rowName2.setText(programNameList2[position]);
        holder.rowName3.setText(programNameList3[position]);
        holder.rowImage1.setImageResource(images1[position]);
        holder.rowImage2.setImageResource(images2[position]);
        holder.rowImage3.setImageResource(images3[position]);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        for (String[] strings : Arrays.asList(programNameList1, programNameList2, programNameList3)) {
            return strings.length;
        }
        return 0;
    }
}
