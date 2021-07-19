package dduwcom.mobile.finalreport;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        textView = findViewById(R.id.infoTextView);

        String str = "안녕하세요~\n모바일소프트웨어 01분반에서\n수업을 듣는 20190937 구다희 입니다\n\n"
            + "최종 과제로 나의 도서 관리 앱을 개발했습니다\n한 학기 수업해주셔서 감사합니다";
        textView.setText(str);
    }
}
