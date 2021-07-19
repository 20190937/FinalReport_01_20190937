package dduwcom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BookDBManager {

    BookDBHelper bookDBHelper = null;
    Cursor cursor = null;

    public BookDBManager(Context context) {
        bookDBHelper = new BookDBHelper(context);
    }

    public ArrayList<Book> getAllBook() {
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BookDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(bookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_PUBLISHER));
            String contents = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_CONTENTS));
            String date = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_DATE));

            String[] dateArray = date.split("/");

            int imageId = getResId((int) id);

            bookList.add ( new Book (id, title, author, publisher,
                    contents, new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])), imageId) );
        }

        close();
        return bookList;
    }

    public boolean addNewBook(Book newBook) {
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(BookDBHelper.COL_TITLE, newBook.getTitle());
        value.put(BookDBHelper.COL_AUTHOR, newBook.getAuthor());
        value.put(BookDBHelper.COL_PUBLISHER, newBook.getPublisher());
        value.put(BookDBHelper.COL_CONTENTS, newBook.getContents());
        value.put(BookDBHelper.COL_DATE, newBook.getDate().toString());

        long count = db.insert(BookDBHelper.TABLE_NAME, null, value);
        close();

        if (count > 0) return true;
        else return false;
    }

    public boolean modifyBook(Book book) {
        SQLiteDatabase sqLiteDatabase = bookDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(bookDBHelper.COL_TITLE, book.getTitle());
        row.put(bookDBHelper.COL_AUTHOR, book.getAuthor());
        row.put(bookDBHelper.COL_PUBLISHER, book.getPublisher());
        row.put(bookDBHelper.COL_CONTENTS, book.getContents());
        row.put(bookDBHelper.COL_DATE, book.getDate().toString());

        String whereClause = bookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(book.get_id())};

        int result = sqLiteDatabase.update(BookDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        close();

        if (result > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean removeBook(long id) {
        SQLiteDatabase sqLiteDatabase = bookDBHelper.getWritableDatabase();
        String whereClause = bookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        int result = sqLiteDatabase.delete(bookDBHelper.TABLE_NAME, whereClause, whereArgs);
        close();

        if (result > 0){
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Book> getBooksByTitle(String et_title) {
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();

        String selection = "title=?";
        String[] selectArgs = new String[]{et_title};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(bookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_PUBLISHER));
            String contents = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_CONTENTS));
            String date = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_DATE));

            String[] dateArray = date.split("/");

            int imageId = getResId((int) id);

            bookList.add ( new Book (id, title, author, publisher,
                    contents, new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])), imageId) );
        }

        close();
        return bookList;
    }

    public ArrayList<Book> getBooksByAuthor(String et_author) {
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();

        String selection = "author=?";
        String[] selectArgs = new String[]{et_author};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(bookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_PUBLISHER));
            String contents = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_CONTENTS));
            String date = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_DATE));

            String[] dateArray = date.split("/");

            int imageId = getResId((int) id);

            bookList.add ( new Book (id, title, author, publisher,
                    contents, new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])), imageId) );
        }

        close();
        return bookList;
    }

    public ArrayList<Book> getBooksByPublisher(String et_publisher) {
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();

        String selection = "publisher=?";
        String[] selectArgs = new String[]{et_publisher};

        Cursor cursor = db.query(BookDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(bookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_PUBLISHER));
            String contents = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_CONTENTS));
            String date = cursor.getString(cursor.getColumnIndex(bookDBHelper.COL_DATE));

            String[] dateArray = date.split("/");

            int imageId = getResId((int) id);

            bookList.add ( new Book (id, title, author, publisher,
                    contents, new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])), imageId) );
        }

        close();
        return bookList;
    }

    public void close() {
        if (bookDBHelper != null) bookDBHelper.close();
        if (cursor != null) cursor.close();
    }

    private int getResId(int id) {
        int imageId = R.mipmap.ic_launcher;

        switch (id) {
            case 1:
                imageId = R.mipmap.a;
                break;
            case 2:
                imageId = R.mipmap.b;
                break;
            case 3:
                imageId = R.mipmap.c;
                break;
            case 4:
                imageId = R.mipmap.d;
                break;
            case 5:
                imageId = R.mipmap.e;
                break;
        }

        return imageId;
    }

}
