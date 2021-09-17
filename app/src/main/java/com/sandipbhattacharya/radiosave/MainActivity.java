package com.sandipbhattacharya.radiosave;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Let's assume 1 = male and 0 = female
    // Declare a RadioGroup object reference
    RadioGroup rgGender;
    // Declare RadioButton object references for Male and Female
    RadioButton rbMale, rbFemale;
    // Declare a SharedPreferences object reference since you are going to store
    // the clicked button's state using Android SharedPreferences class.
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the SharedPreferences object reference
        sharedPreferences = getSharedPreferences("pref", 0);
        // Here pref is the name of the file and 0 for private mode.
        // To read values from SharedPreferences, you can use getInt() method on
        // sharedPreferences object.
        int genderSP = sharedPreferences.getInt("genderSP", 3);
        // Here, the first parameter of getInt() is the key.
        // The second parameter is the default value that is, value to return if this preference
        // does not exist. I am giving a default value that is neither 1 nor 0,
        // so that it doesn't select a radiobutton by default for the first time. If you want to
        // select the radio button for Male you can give 1, for example.
        // So, this is how you can create a key named "genderSP" using SharedPreferences,
        // retrieved value from it and stored in another integer variable
        // also named "genderSP".
        // Instantiate SharedPreferences.Editor object by calling edit() method
        // on sharedPreferences object.
        editor = sharedPreferences.edit();
        // Next, get the handles for Views
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        // Check the value of genderSP integer variable to be equal to 1 or 0, so that you can make
        // the corresponding radio button checked.
        if(genderSP == 1){
            rbMale.setChecked(true);
        }else if(genderSP == 0){
            rbFemale.setChecked(true);
        }
        /*
        // Method 1: Settings OnCheckedChangeListener on RadioGroup object.
        When a button is checked a callback method is fired containing Id of the checked button.

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbMale){
                    // Put the value 1 in the key "genderSP" using editor object
                    editor.putInt("genderSP", 1);
                }else if(checkedId == R.id.rbFemale){
                    // Here, put the value 0 in the key "genderSP" using editor
                    editor.putInt("genderSP",0);
                }
                // Now, to save the changes call commit() on editor.
                editor.commit();
            }
        });*/
    }

    public void btnClicked(View view) {
        /* Method 2: Writing a method name in RadioButton's onClick attribute.
        Check if the button is now checked?
        // Check which radio button was clicked using a switch-case
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            // If the case matches with id for male radio button, put the value 1 in the key "genderSP"
            // using editor object
            case R.id.rbMale:
                if(checked){
                    editor.putInt("genderSP", 1);
                }
                break;
            // If it matches with id for female radio button, put the value 0 in the key "genderSP" using editor
            case R.id.rbFemale:
                if(checked){
                    editor.putInt("genderSP", 0);
                }
                break;
        }
        // Don't forget to save the changes by calling commit() on editor.
        editor.commit();*/

        // Method 3: Check individual radio buttons checked state
        // and put necessary value in the key using editor
        if (rbMale.isChecked()){
            editor.putInt("genderSP", 1);
        } else if(rbFemale.isChecked()){
            editor.putInt("genderSP",0);
        }
        // Save the changes
        editor.commit();
    }
}