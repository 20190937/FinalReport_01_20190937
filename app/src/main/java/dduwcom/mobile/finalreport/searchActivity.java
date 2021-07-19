package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class searchActivity extends AppCompatActivity {

    ArrayList<Book> bookList;
    Adapter adapter;
    ListView listView;
    BookDBManager bookDBManager;
    EditText editText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText) findViewById(R.id.search_et);
        bookDBManager = new BookDBManager(this);
        bookList = new ArrayList();
        adapter = new Adapter(this, R.layout.custom_adapter_view, bookList);
        listView = (ListView) findViewById(R.id.search_customListView);
        listView.setAdapter(adapter);
    }

    public void searchOnClick(View v) {
        bookList.clear();
        switch(v.getId()) {
            case R.id.search_btn_title:
                bookList.addAll(bookDBManager.getBooksByTitle(editText.getText().toString()));
                break;

            case R.id.search_btn_author:
                bookList.addAll(bookDBManager.getBooksByAuthor(editText.getText().toString()));
                break;

            case R.id.search_btn_publisher:
                bookList.addAll(bookDBManager.getBooksByPublisher(editText.getText().toString()));
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
