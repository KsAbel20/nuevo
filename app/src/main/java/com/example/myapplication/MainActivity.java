package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.ConnectionRequest;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.api.ServiceApi;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.Connection;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> datos = new ArrayList<>();
    ListView listViewTitulo;
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTitulo = findViewById(R.id.lstitulo);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        listViewTitulo.setAdapter(adaptador);

        carga();
    }

    public void carga(){
        ServiceApi api = Connection.getConnection().create(ServiceApi.class);
        Call<List<User>> call = api.lista();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                muestraMensaje("TAREA COMPLETA");
                if (response.isSuccessful()){
                    List<User> respu = response.body();
                    for (User u: respu){
                        datos.add(u.getId()+"-->"+u.getTitle());
                    }
                    adaptador.notifyDataSetChanged();
                }else{
                    muestraMensaje("NO SE COMPLETO LA TAREA");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                muestraMensaje("--> Error:" + t.getMessage());
            }
        });
    }
    public void muestraMensaje(String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg);
        alert.show();
    }
}