package online.help.jobworld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class New_Job extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submitJob;
    EditText title, description, jobLocation, contactDetails;
    Spinner aSpinner;
    FirebaseFirestore db;

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
        submitJob.setOnClickListener( this );

        db = FirebaseFirestore.getInstance();

    }

    // String aSpinnerString,
    private boolean validateInput(String titleString, String descriptionString, String jobLocationString, String contactDetailsString) {
        if (titleString.isEmpty()) {
            title.setError( "Enter the name of the Job" );
            title.requestFocus();
            return true;
        }
//        if(aSpinnerString == null) {
//            aSpinner = (String)aSpinnerString.getSelectedItem();
//        }
        if (descriptionString.isEmpty()) {
            description.setError( "Enter the Description for the job" );
            description.requestFocus();
            return true;
        }
        if (jobLocationString.isEmpty()) {
            jobLocation.setError( "Enter the Job Location" );
            jobLocation.requestFocus();
            return true;
        }
        if (contactDetailsString.isEmpty()) {
            contactDetails.setError( "Enter the contact details" );
            contactDetails.requestFocus();
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition( position ).toString();
        Toast.makeText( parent.getContext(), text, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String titleString = title.getText().toString().trim();
        String descriptionString = description.getText().toString().trim();
        String jobLocationString = jobLocation.getText().toString().trim();
        String contactDetailsString = contactDetails.getText().toString().trim();
        String categoryString = aSpinner.getSelectedItem().toString();
        if (!validateInput( titleString, descriptionString, jobLocationString, contactDetailsString )) {
            CollectionReference dbJobs = db.collection( "Jobs" );
            Job job = new Job(
                    titleString,
                    categoryString,
                    descriptionString,
                    jobLocationString,
                    contactDetailsString
            );
            dbJobs.add( job ).addOnSuccessListener( new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText( New_Job.this, "Job Added", Toast.LENGTH_LONG ).show();
                }
            } ).addOnFailureListener( new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText( New_Job.this, e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            } );
        }
    }
}