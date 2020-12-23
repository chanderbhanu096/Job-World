package online.help.jobworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllJobsAdapter extends RecyclerView.Adapter<AllJobsAdapter.AllJobsViewHolder> {

    private Context mCtx;
    private List<Job> jobList;

    public AllJobsAdapter(Context mCtx, List<Job> jobList) {
        this.mCtx = mCtx;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public AllJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllJobsViewHolder(
                LayoutInflater.from( mCtx ).inflate( R.layout.list_item, parent, false )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AllJobsViewHolder holder, int position) {

        Job job = jobList.get( position );

        holder.textViewtitle.setText( job.getTitle() );
        holder.textViewCategory.setText( job.getCategory() );
        holder.textViewLocation.setText( job.getLocation() );

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class AllJobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewtitle, textViewLocation, textViewCategory;

        public AllJobsViewHolder(View itemView) {
            super( itemView );

            textViewtitle = itemView.findViewById( R.id.viewTitle );
            textViewLocation = itemView.findViewById( R.id.viewLocation );
            textViewCategory = itemView.findViewById( R.id.viewCategory );
        }

        @Override
        public void onClick(View v) {

        }
    }
}
