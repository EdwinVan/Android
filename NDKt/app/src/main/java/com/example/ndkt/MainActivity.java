package com.example.ndkt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ndkt.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TextView tv;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });
        tv.setText(stringFromJNI());
    }

    private native String stringFromJNI();
    public native int getMaxPrime(int n);

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public void search(View view){
        String text_input=editText.getText().toString();
        tv.setText(text_input+"以内的最大素数为："+getMaxPrime(Integer.valueOf(text_input)));
    };
}