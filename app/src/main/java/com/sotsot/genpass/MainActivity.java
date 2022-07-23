package com.sotsot.genpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton copyButton = (ImageButton) findViewById(R.id.copy_button);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCopyButtonClick();
            }
        });

        ImageButton resetButton = (ImageButton) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetButtonClick();
            }
        });

        ImageButton generateButton = (ImageButton) findViewById(R.id.generate_button);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGenerateButtonClick();
            }
        });

        TextView lengthTextView = (TextView) findViewById(R.id.length_text);
        SeekBar lengthBar = (SeekBar) findViewById(R.id.length_bar);
        String length = Integer.toString(lengthBar.getProgress());
        lengthTextView.setText(length);
        lengthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                lengthTextView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void onCopyButtonClick(){
        TextView passwordText = (TextView) findViewById(R.id.password_text);
        String password = passwordText.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("password", password);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Password Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    private void onResetButtonClick(){
        TextView passwordText = (TextView) findViewById(R.id.password_text);
        passwordText.setText("");
    }

    private void onGenerateButtonClick(){
        Switch lowercaseSwitch = (Switch) findViewById(R.id.lowercase_letters_switch);
        Switch uppercaseSwitch = (Switch) findViewById(R.id.uppercase_letters_switch);
        Switch numbersSwitch = (Switch) findViewById(R.id.numbers_switch);
        Switch symbolsSwitch = (Switch) findViewById(R.id.symbols_switch);
        TextView lengthText = (TextView) findViewById(R.id.length_text);

        boolean has_lowercase = lowercaseSwitch.isChecked();
        boolean has_uppercase = uppercaseSwitch.isChecked();
        boolean has_numbers = numbersSwitch.isChecked();
        boolean has_symbols = symbolsSwitch.isChecked();
        int length = Integer.parseInt(lengthText.getText().toString());

        if(has_lowercase == false && has_uppercase == false && has_numbers == false && has_symbols == false){
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        int count = 0;
        boolean[] rules = new boolean[4];
        rules[0] = has_lowercase;
        rules[1] = has_uppercase;
        rules[2] = has_numbers;
        rules[3] = has_symbols;
        for(int i=0; i<4; i++){
            if(rules[i] == true) count++;
        }
        if(length < count){
            Toast.makeText(this, "Password can not be generated, please select a bigger length", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = Generator.generate(has_lowercase, has_uppercase, has_numbers, has_symbols, length);

        TextView passwordText = (TextView) findViewById(R.id.password_text);
        passwordText.setText(password);
    }
}