package myapp.sobsdes.allnews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimsob on 29.06.16.
 */

public class NewsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsDB";
    final String LOG_TAG = "NewsDatabase";
    private static final int DATABASE_VERSION = 1;

    public NewsDatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sQuery = "CREATE TABLE NEWS (id integer primary key, news_title text, news_date text, news_category text, news_desc text, news_image text, news_source text);";
        db.execSQL(sQuery);

    }

    public void addNews(NewsAdapter n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("news_title", n.getNews_title());
        cv.put("news_date", n.getNews_date());
        cv.put("news_category", n.getNews_category());
        cv.put("news_desc", n.getNews_desc());
        cv.put("news_image", n.getNews_image());
        cv.put("news_source", n.getNews_source());
        db.insert("NEWS", null, cv);

        Log.d("Add news", n.toString());

        db.close();
    }

    public List<NewsAdapter> getAllNews() {
        List<NewsAdapter> noteList = new ArrayList<NewsAdapter>();

        String selectQuery = "SELECT * FROM NEWS ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                NewsAdapter note = new NewsAdapter();
                note.setNews_id(Integer.valueOf(cursor.getString(0)));
                note.setNews_title(cursor.getString(1));
                note.setNews_date(cursor.getString(2));
                note.setNews_image(cursor.getString(3));
                note.setNews_desc(cursor.getString(4));
                note.setNews_category(cursor.getString(5));
                note.setNews_source(cursor.getString(6));
                Log.d(LOG_TAG, note.toString());
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return noteList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
