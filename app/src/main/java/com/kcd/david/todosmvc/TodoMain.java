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

import java.util.ArrayList;

public class TodoMain extends AppCompatActivity {

    EditText _editTextValue;
    ListView _listView;
    ArrayAdapter<String> _arrayAdapter;
    ArrayList<String> _masterTodoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        _masterTodoList.add(_editTextValue.getText().toString());
        _editTextValue.setText("");
        _arrayAdapter.notifyDataSetChanged();
    }
}
