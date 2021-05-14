package com.example.roombai1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_add,btn_remove,btn_edt;
    private EditText pt;
    private ListView lv;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // anh xa
        btn_edt = findViewById(R.id.btnCancel);
        btn_add =findViewById(R.id.btnAdd);
        btn_remove =findViewById(R.id.btnRemove);
        pt = findViewById(R.id.pt);
        lv= findViewById(R.id.lvname);

        ///

        AppDatabase db =
                Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database")
                        .allowMainThreadQueries()
                        .build();

        UserDao userDao = db.userDao();

        ArrayAdapter adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);

        adapter.addAll(userDao.getAllUser());

       btn_add.setOnClickListener(v -> {
            if(pt.getText().toString().trim().isEmpty())
                return;

            User user = new User(pt.getText().toString().trim());

            userDao.insert(user);

            adapter.add(userDao.getLastUser());
            adapter.notifyDataSetChanged();
        });

        btn_remove.setOnClickListener(v -> {
            if(pt.getText().toString().trim().isEmpty())
                return;

            User user = userDao.findUserByName(pt.getText().toString().trim());
            if(user == null)
                return;

            userDao.delete(user);
            adapter.clear();

            adapter.addAll(userDao.getAllUser());

        });








    }
}