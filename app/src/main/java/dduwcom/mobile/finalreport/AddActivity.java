package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etAuthor;
    EditText etPublisher;
    EditText etContents;
    DatePicker datePicker;
    ImageView bookimageView;

    BookDBManager bookDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.add_et_title);
        etAuthor = findViewById(R.id.add_et_author);
        etPublisher = findViewById(R.id.add_et_publisher);
        etContents = findViewById(R.id.add_et_contents);
        datePicker = findViewById(R.id.add_datepicker);
        bookimageView = findViewById(R.id.add_image);

        bookDBManager = new BookDBManager(this);
    }

    public void addOnClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn_ok:

                if (etTitle.getText().toString().equals("")) {
                    Toast.makeText(this, "필수 항목이 입력되지 않았습니다(제목)", Toast.LENGTH_SHORT).show();
                } else {
                    Date date = new Date(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                    Book book = new Book(etTitle.getText().toString(), etAuthor.getText().toString(), etPublisher.getText().toString(), etContents.getText().toString(), date);

                    boolean result = bookDBManager.addNewBook(book);

                    if (result) {    // 정상수행에 따른 처리
                        setResult(RESULT_OK);
                        finish();
                    } else {        // 이상에 따른 처리
                        Toast.makeText(this, "정상적으로 등록되지 않았습니다", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.add_btn_cancel:   // 취소에 따른 처리
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
