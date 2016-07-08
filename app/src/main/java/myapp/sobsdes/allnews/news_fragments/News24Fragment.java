package myapp.sobsdes.allnews.news_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import myapp.sobsdes.allnews.NewsAdapter;
import myapp.sobsdes.allnews.NewsDatabase;
import myapp.sobsdes.allnews.OneNewsActivity;
import myapp.sobsdes.allnews.imageloader.LazyImageLoadNewsAdapter;

import android.content.Intent;

import java.util.ArrayList;

import android.support.v4.app.ListFragment;
import android.widget.ListView;

public class News24Fragment extends ListFragment {
    LazyImageLoadNewsAdapter adapter;
    ListView list;

    public News24Fragment(){
    }

    final String LOG_TAG = "NewsLog";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = (ListView) getActivity().findViewById(myapp.sobsdes.allnews.R.id.listView);

        adapter = new LazyImageLoadNewsAdapter(getActivity(), getData(),
                getActivity().getApplicationContext());// mStrings
        list.setAdapter(adapter);
        Log.d(LOG_TAG, "переход во фрагмент и  onActivityCreated");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        Intent i = new Intent(getActivity(), OneNewsActivity.class);


        i.putExtra("mPosition", position);
        startActivity(i);

    }

    private ArrayList<NewsAdapter> getData() {
        NewsDatabase db = new NewsDatabase(getActivity()
                .getApplicationContext());
        final ArrayList<NewsAdapter> stringItems = new ArrayList<NewsAdapter>();

        ArrayList<NewsAdapter> pr = (ArrayList<NewsAdapter>) db.getAllNews();

        for (NewsAdapter p : pr) {
            stringItems.add(p);
        }
        Log.d(LOG_TAG, "сбор новостей в функции  private ArrayList<NewsAdapter> getData() ");
        return stringItems;

    }
}