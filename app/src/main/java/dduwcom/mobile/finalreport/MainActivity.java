package dduwcom.mobile.finalreport;

//과제명 : 나의 도서 관리 앱
//분반 : 01 분반
//학번 : 20190937 성명 : 구다희
//제출일 : 2021년 6월 17일

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ADD_CODE = 100;
    final int UPDATE_CODE = 200;

    ArrayList<Book> bookList;
    Adapter adapter;
    ListView listView;
    BookDBManager bookDBManager;
    Intent intent;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDBManager = new BookDBManager(this);
        bookList = bookDBManager.getAllBook();
        adapter = new Adapter(this, R.layout.custom_adapter_view, bookList);
        listView = (ListView) findViewById(R.id.main_customListView);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("도서 정보 삭제")
                        .setMessage("'"+bookList.get(pos).getTitle()+"'을(를) 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = bookDBManager.removeBook(bookList.get(pos).get_id());
                                if (result) {
                                    Toast.makeText(MainActivity.this, "삭제 완료",Toast.LENGTH_SHORT).show();
                                    bookList.clear();
                                    bookList.addAll(bookDBManager.getAllBook());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("book", book); // serializable 로 구현되있으니까 할 수 있음
                startActivityForResult(intent, UPDATE_CODE);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void onResume() {
        super.onResume();
        bookList.clear();
        bookList.addAll(bookDBManager.getAllBook());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addBook:
                intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_CODE); // onresume이 자동 호출될것임
                break;

            case R.id.searchBook:
                intent = new Intent(this, searchActivity.class);
                startActivity(intent);
                break;

            case R.id.infoProgrammer:
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;

            case R.id.exitApp:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "도서 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "도서 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {    // onresume이 자동 호출될것임
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "도서 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "도서 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}