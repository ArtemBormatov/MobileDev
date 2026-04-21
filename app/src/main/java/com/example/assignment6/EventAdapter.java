package com.example.assignment6;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
//displays data
    private List<Event> events;

    public EventAdapter(List<Event> events){ this.events = events; }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView txtEvent;
        public EventViewHolder(View view){
            super(view);
            txtEvent = view.findViewById(android.R.id.text1);
        }
    }
//inflate just turns xml layout into view objects
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new EventViewHolder(view);
    }
//keeps the data in the place
    @Override
    public void onBindViewHolder(EventViewHolder holder, int position){
        Event event = events.get(position);
        holder.txtEvent.setText(event.getType() + " | " + event.getDate() + " | " + event.getTime());
    }

    @Override
    public int getItemCount(){ return events.size(); }
}