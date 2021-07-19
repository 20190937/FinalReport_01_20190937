package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Book book;

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
        setContentView(R.layout.activity_update);
        book = (Book) getIntent().getSerializableExtra("book");

        etTitle = findViewById(R.id.update_et_title);
        etAuthor = findViewById(R.id.update_et_author);
        etPublisher = findViewById(R.id.update_et_publisher);
        etContents = findViewById(R.id.update_et_contents);
        datePicker = findViewById(R.id.update_datepicker);
        bookimageView = findViewById(R.id.update_image);

        bookDBManager = new BookDBManager(this);

        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etPublisher.setText(book.getPublisher());
        etContents.setText(book.getContents());
        bookimageView.setImageResource(book.getBookImageResId());

        Date date = book.getDate();
        datePicker.init(date.getYear(), date.getMonth() - 1, date.getDay(), null);
    }

    public void updateOnClick(View v) {
        switch(v.getId()) {
            case R.id.update_btn_ok:
                if (etTitle.getText().toString().equals("")) {
                    Toast.makeText(this, "필수 항목이 입력되지 않았습니다(제목)", Toast.LENGTH_SHORT).show();
                } else {
                    Date date = new Date(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                    Book newBook = new Book(book.get_id(), etTitle.getText().toString(), etAuthor.getText().toString(), etPublisher.getText().toString(), etContents.getText().toString(), date);
                    boolean result = bookDBManager.modifyBook(newBook);

                    if (result) {
                        setResult(RESULT_OK);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                }
                break;
            case R.id.update_btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
