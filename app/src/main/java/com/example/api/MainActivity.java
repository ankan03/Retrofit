package com.example.api;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Users> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users=new ArrayList<>();
        final Adapter adapter = new Adapter(users,this);
        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi service = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Users>> call = service.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Response Failure "+ response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(MainActivity.this,"Done "+ response.code(),Toast.LENGTH_LONG).show();
                    users.addAll(response.body());
                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure to response: "+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
