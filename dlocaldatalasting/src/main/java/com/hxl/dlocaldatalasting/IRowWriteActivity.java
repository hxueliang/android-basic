package com.hxl.dlocaldatalasting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.dlocaldatalasting.dao.BookDao;
import com.hxl.dlocaldatalasting.enity.BookInfo;
import com.hxl.dlocaldatalasting.util.ToastUtil;

import java.util.List;

public class IRowWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_author;
    private EditText et_press;
    private EditText et_price;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irow_write);

        et_name = findViewById(R.id.et_name);
        et_author = findViewById(R.id.et_author);
        et_press = findViewById(R.id.et_press);
        et_price = findViewById(R.id.et_price);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        // 从App实例中获取唯一的书籍持久化对象
        bookDao = IMyApplication.getInstance().getBookDB().bookDao();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String author = et_author.getText().toString();
        String press = et_press.getText().toString();
        String price = et_price.getText().toString();

        if (v.getId() == R.id.btn_add) {
            // 5. 在操作书籍信息表的地方获取数据表的持久化对象
            // 以下声明一个书籍信息对象，并填写它的各字段值
            BookInfo b1 = new BookInfo();
            b1.setName(name);
            b1.setAuthor(author);
            b1.setPress(press);
            b1.setPrice(Double.parseDouble(price));
            bookDao.insert(b1);
            ToastUtil.show(this, "添加成功");
        } else if (v.getId() == R.id.btn_delete) {
            BookInfo b2 = new BookInfo();
            // 根据name找到具体记录，再得到具体id。
            BookInfo _b = bookDao.queryByName(name);
            b2.setId(_b.getId());
            bookDao.delete(b2);
        } else if (v.getId() == R.id.btn_update) {
            BookInfo b3 = new BookInfo();
            // 根据name找到具体记录，再得到具体id。
            BookInfo _b = bookDao.queryByName(name);
            b3.setId(_b.getId());
            b3.setName(name);
            b3.setAuthor(author);
            b3.setPress(press);
            b3.setPrice(Double.parseDouble(price));
            bookDao.update(b3);
        } else if (v.getId() == R.id.btn_query) {
            List<BookInfo> list = bookDao.queryAll();
            for (BookInfo b : list) {
                Log.d("x_log", b.toString());
            }
        }
    }
}