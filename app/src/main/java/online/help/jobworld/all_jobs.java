package online.help.jobworld;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class all_jobs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AllJobsAdapter adapter;
    private List<Job> jobList;
    private ProgressBar progressBar;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_jobs );

        progressBar = findViewById( R.id.progressbar );

        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        jobList = new ArrayList<>();

        adapter = new AllJobsAdapter( this, jobList );

        recyclerView.setAdapter( adapter );

        db = FirebaseFirestore.getInstance();
        db.collection( "Jobs" ).get()
                .addOnSuccessListener( new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility( View.GONE );
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Job j = d.toObject( Job.class );
                                jobList.add( j );
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                } );
    }

}