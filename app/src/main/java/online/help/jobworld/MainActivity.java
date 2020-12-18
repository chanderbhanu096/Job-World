package online.help.jobworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button findJob, postJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        findJob = (Button) findViewById( R.id.search_jobs );
        postJob = (Button) findViewById( R.id.new_job );
        findJob.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent( MainActivity.this, all_jobs.class );

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity( activityChangeIntent );
            }
        } );


        postJob.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent( MainActivity.this, New_Job.class );

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity( activityChangeIntent );
            }
        } );
    }

}