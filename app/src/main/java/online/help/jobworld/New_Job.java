package online.help.jobworld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class New_Job extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ;
    Button submitJob;
    EditText title, category, description, jobLocation, contactDetails;
    Spinner aSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new__job );

        submitJob = (Button) findViewById( R.id.submit_new_job );
        title = (EditText) findViewById( R.id.title );
        description = (EditText) findViewById( R.id.job_description );
        jobLocation = (EditText) findViewById( R.id.job_location );
        contactDetails = (EditText) findViewById( R.id.contact_me );

        aSpinner = findViewById( R.id.spinner );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.Spinner_item, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        aSpinner.setAdapter( adapter );
        aSpinner.setOnItemSelectedListener( this );
        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition( position ).toString();
        Toast.makeText( parent.getContext(), text, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}