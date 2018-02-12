package com.example.dellpc.facts.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dellpc.facts.R;
import com.example.dellpc.facts.adapter.FactsAdapter;
import com.example.dellpc.facts.model.FactsList;
import com.example.dellpc.facts.model.FactsResponse;
import com.example.dellpc.facts.util.Global;
import com.example.dellpc.facts.util.Networkutils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Balamithra on 2/11/2018.
 */
public class MainActivity extends AppCompatActivity{
    static String TAG = "MainActivity";
    private Context context=MainActivity.this;
    public RecyclerView recyclerView=null;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFactsFromServer();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});

        if (!new Networkutils(this).isConnectingToInternet()) {
            Global.showToastShort(this.context, getResources().getString(R.string.app_name));
        }
        swipeRefreshLayout.setRefreshing(true);
        getFactsFromServer();
    }
    private void getFactsFromServer()
    {
        Global.apiService.getFacts()
                .enqueue(new Callback<FactsResponse>() {
                    public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {
                        if (response.code() == 200) {
                            if(swipeRefreshLayout != null){
                                swipeRefreshLayout.setRefreshing(false);
                            }
                            FactsResponse factsResponse = (FactsResponse) response.body();
                            List<FactsList> factsLists=factsResponse.getFactsList();
                            if(getSupportActionBar()!=null) {
                                getSupportActionBar().setTitle(factsResponse.getTitle());
                            }

                            FactsAdapter factsAdapter = new FactsAdapter(MainActivity.this,factsLists);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setHasFixedSize(false);
                            recyclerView.getRecycledViewPool().clear();
                            recyclerView.setAdapter(factsAdapter);
                            factsAdapter.notifyDataSetChanged();
                        }
                    }

                    public void onFailure(Call<FactsResponse> call, Throwable t) {
                        Log.e(TAG, t.toString());
                        // stopping swipe refresh
                        if(swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        Global.showToastShort(context, getString(R.string.error));
                    }
                });
    }

}
