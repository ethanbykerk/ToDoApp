package com.darkfield.ethanbykerk.todolistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String>  tasksToGetDone;//best method to store the tasks
    private ArrayAdapter<String> taskToGetDoneAdapter;//arrayadapter is used because of the list view in the xml file.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksToGetDone = new ArrayList<String>();
        taskToGetDoneAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, tasksToGetDone);
        ListView listOfTasks = (ListView) findViewById(R.id.taskList);//this is the list of tasks
        listOfTasks.setAdapter(taskToGetDoneAdapter);//setting the adapter to the list view
        registerForContextMenu(listOfTasks);//relates to long press instead of a button
    }

    public void buttonAddClick(View v1){
        String taskToAdd = ((EditText)findViewById(R.id.addTaskText)).getText().toString().trim();//
        if(taskToAdd.isEmpty()){
            return;//if the user doesn't type anything in the text box then nothing happens, just returns
        }
        taskToGetDoneAdapter.add(taskToAdd); //this adds the task to the screen

    }

    @Override//this method will create an options menu to allow the user to delete the task
    public void onCreateContextMenu(ContextMenu tasks, View v, ContextMenu.ContextMenuInfo tasksInfo){
        tasks.setHeaderTitle("Options");
        String options = "Delete";
        tasks.add(options);
    }

    @Override//this method deletes the task
    public boolean onContextItemSelected(MenuItem option){
        AdapterView.AdapterContextMenuInfo x = (AdapterView.AdapterContextMenuInfo) option.getMenuInfo();
        int taskIndex = x.position;
        if(option.getTitle().equals("Delete")){
            tasksToGetDone.remove(taskIndex);//does the actual deleting of the task
            taskToGetDoneAdapter.notifyDataSetChanged();
        }
        return true;
    }

}
