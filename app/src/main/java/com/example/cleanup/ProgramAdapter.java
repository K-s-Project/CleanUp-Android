package com.example.cleanup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanup.model.RoomInterface;
import com.example.cleanup.model.RoomModel;

import java.util.ArrayList;

// Create the adapter by extending RecyclerView.Adapter. This custom ViewHolder will give access to your views
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    // Declare variables to store data from the constructor
    public Context context;
    public ArrayList<RoomModel> rooms;
    private final RoomInterface roomInterface;

    // Create a static inner class and provide references to all the Views for each data item.
    // This is particularly useful for caching the Views within the item layout for fast access.
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Declare member variables for all the Views in a row
        TextView title;
        TextView sched;
        TextView location;
        TextView note;
        LinearLayout card;

        // Create a constructor that accepts the entire row and search the View hierarchy to find each subview
        public ViewHolder(@NonNull View itemView, RoomInterface roomInterface) {
            super(itemView);
            // Store the item subviews in member variables
            title = itemView.findViewById(R.id.roomnumber);
            sched = itemView.findViewById(R.id.textView1);
            location = itemView.findViewById(R.id.textView2);
            note = itemView.findViewById(R.id.textView3);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int position = (int) v.getTag();
//                RoomModel room = rooms.get(getAdapterPosition());
//                Toast.makeText(v.getContext(), "Hello"+room.getBuilding_name(), Toast.LENGTH_SHORT).show();

                    if(roomInterface != null ){
                        int pos = getAdapterPosition();

                        roomInterface.onItemClick(pos);
                    }
            }
        });
        }
    }
    // Provide a suitable constructor
    public ProgramAdapter(Context context, ArrayList<RoomModel> rooms, RoomInterface roomInterface){
        // Initialize the class scope variables with values received from constructor
        this.context = context;
        this.rooms = rooms;

        this.roomInterface = roomInterface;
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
//                int position = (int) v.getTag();
//                Toast.makeText(v.getContext(), "You clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view,roomInterface);
        return viewHolder;
    }

    // Replace the contents of a view to be invoked by the layout manager
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the View with that element
        holder.title.setText(rooms.get(position).room_number);
        holder.sched.setText(rooms.get(position).schedule);
        holder.location.setText(rooms.get(position).building_name);
        holder.note.setText(rooms.get(position).notes);

    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
       return rooms.size();
    }
}
