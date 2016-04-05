package com.kcd.david.todosmvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TodoMain extends AppCompatActivity {

    EditText _editTextValue;
    ListView _listView;
    ArrayAdapter<String> _arrayAdapter;
    ArrayList<String> _masterTodoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("newTestFile")));

            String stra;
            while((stra = inputReader.readLine()) != null){
                _masterTodoList.add(stra);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        setContentView(R.layout.activity_todo_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        _editTextValue= (EditText)findViewById(R.id.editText);
        _listView = (ListView)findViewById(R.id.listView);

        _listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        _arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, _masterTodoList);
        _listView.setAdapter(_arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doSomething(View view){
        String newTodoItem = _editTextValue.getText().toString();
        writeFile(newTodoItem);
        _masterTodoList.add(newTodoItem);
        _editTextValue.setText("");
        _arrayAdapter.notifyDataSetChanged();
    }

    public void writeFile(String stringToWrite){
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput("newTestFile", this.MODE_PRIVATE);

            outputStream.write((stringToWrite + "\n").getBytes());
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
