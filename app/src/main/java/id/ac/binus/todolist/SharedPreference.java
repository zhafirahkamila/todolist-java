package id.ac.binus.todolist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreference {
    private static final String PREF_NAME = "todo_pref";
    private static final String TASKS_KEY = "tasks_key";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreference(Context context){
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public void saveTasks(ArrayList<String> tasks){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(tasks);
        editor.putString(TASKS_KEY, json);
        editor.apply();
    }

    public ArrayList<String> loadTasks(){
        String json = sharedPreferences.getString(TASKS_KEY, null);
        if (json != null){
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }
}
