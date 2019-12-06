package com.example.shan.menudemoapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewUserName, mTextViewPassword;
    EditText mEditTextEmail, mEditTextConfirmEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextConfirmEmail = findViewById(R.id.editTextRepeatEmail);

        mTextViewUserName = findViewById(R.id.textViewUserName);
        mTextViewPassword = findViewById(R.id.textViewPassword);
        registerForContextMenu(mTextViewUserName);
        registerForContextMenu(mTextViewPassword);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if(v.getId() == R.id.textViewUserName) {
            menuInflater.inflate(R.menu.context_menu, menu);
        }else if(v.getId() == R.id.textViewPassword) {
            menuInflater.inflate(R.menu.context_menu_password, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemRemember:
                Toast.makeText(this, "itemRemember selected",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemForget:
                Toast.makeText(this, "itemForget selected",
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.itemContextCopy:
                Toast.makeText(this, "itemContextCopy selected",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemCopy:
                copyEmail();
                return true;
            case R.id.itemPaste:
                pasteEmail();
                return true;

            case R.id.itemCreateFile:
                Toast.makeText(this,"itemCreateFile selected",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void copyEmail(){
        ClipboardManager clipboardManager =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("emailMain",
                mEditTextEmail.getText().toString());
        clipboardManager.setPrimaryClip(clipData);

    }
    public void pasteEmail(){
        ClipboardManager clipboardManager =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData.Item item =  clipboardManager.getPrimaryClip().getItemAt(0);

        mEditTextConfirmEmail.setText(item.getText());
    }
}
