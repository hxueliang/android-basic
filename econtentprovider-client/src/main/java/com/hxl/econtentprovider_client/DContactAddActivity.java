package com.hxl.econtentprovider_client;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hxl.econtentprovider_client.entity.Contact;

import java.util.ArrayList;

public class DContactAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcontact_add);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_add_full).setOnClickListener(this);
        findViewById(R.id.btn_select).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String email = et_email.getText().toString().trim();

        if (v.getId() == R.id.btn_add) {
            // 注意：测试时，如果手机通讯录中已有相同记录需要先删除再添加（在应用列表"电话图"app）
            Contact contact = new Contact();
            contact.name = name;
            contact.phone = phone;
            contact.email = email;

            // 方式一：使用 ContentResolver 多次写入，每次一个字段
            addContents(getContentResolver(), contact);
        } else if (v.getId() == R.id.btn_add_full) {
            Contact contact = new Contact();
            contact.name = name;
            contact.phone = phone;
            contact.email = email;

            // 方式二：批处理方式
            // 每一次操作都是一个 ContentProviderOperation，构建一个操作集合，然后一次性执行
            // 好处是，要么全部成功，要么全部失败，保证事务的一致性
            addFullContacts(getContentResolver(), contact);
        } else if (v.getId() == R.id.btn_select) {
        }
    }

    // 往手机通讯录添加一个联系人信息（包括姓名、电话号码、电子邮箱）
    private void addContents(ContentResolver resolver, Contact contact) {
        // 往 raw_contacts表 添加"联系人"记录，并获取添加后的联系人编号
        ContentValues values = new ContentValues();
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(uri);

        // 往 data表 添加"联系人"的"姓名"记录
        ContentValues name = new ContentValues();
        // "姓名"的关联联系人的编号
        name.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // "姓名"的数据类型
        name.put(Contacts.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // "姓名"的具体数据
        name.put(Contacts.Data.DATA2, contact.name);
        // 添加
        resolver.insert(ContactsContract.Data.CONTENT_URI, name);

        // 往 data表 添加联系人的"电话号码"记录
        ContentValues phone = new ContentValues();
        // "电话号码"的关联联系人的编号
        phone.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // "电话号码"的数据类型
        phone.put(Contacts.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // "电话号码"的具体数据
        phone.put(Contacts.Data.DATA1, contact.phone);
        // "电话号码"的类型。1: 家庭电话，2: 工作电话
        phone.put(Contacts.Data.DATA2, CommonDataKinds.Phone.TYPE_MOBILE);
        // 添加
        resolver.insert(ContactsContract.Data.CONTENT_URI, phone);

        // 往 data表 添加联系人的"电子邮箱"记录
        ContentValues email = new ContentValues();
        // "电子邮箱"的关联联系人的编号
        email.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // "电子邮箱"的数据类型
        email.put(Contacts.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        // "电子邮箱"的具体数据
        email.put(Contacts.Data.DATA1, contact.email);
        // "电子邮箱"的类型。1: 家庭邮箱，2: 工作邮箱
        email.put(Contacts.Data.DATA2, CommonDataKinds.Email.TYPE_WORK);
        // 添加
        resolver.insert(ContactsContract.Data.CONTENT_URI, email);
    }

    // 往手机通讯录一次性添加一个联系人信息（包括姓名、电话号码、电子邮箱）
    private void addFullContacts(ContentResolver contentResolver, Contact contact) {
        // 创建一个插入"联系人"主记录的内容操作器
        ContentProviderOperation op_main = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();

        try {

            // 先执行主操作，获取插入的raw_contact_id
            ArrayList<ContentProviderOperation> operations = new ArrayList<>();
            operations.add(op_main);
            ContentProviderResult[] results = contentResolver.applyBatch(ContactsContract.AUTHORITY, operations);
            Uri insertedUri = results[0].uri;
            long rawContactId = ContentUris.parseId(insertedUri);

            // 创建一个插入联系人"姓名"记录的内容操作器
            ContentProviderOperation op_name = ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(Contacts.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(Contacts.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(Contacts.Data.DATA2, contact.name)
                    .build();

            // 创建一个插入联系人"电话号码"记录的内容操作器
            ContentProviderOperation op_phone = ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(Contacts.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(Contacts.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(Contacts.Data.DATA1, contact.phone)
                    .withValue(Contacts.Data.DATA2, CommonDataKinds.Phone.TYPE_MOBILE)
                    .build();

            // 创建一个插入联系人"电子邮箱"记录的内容操作器
            ContentProviderOperation op_email = ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(Contacts.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(Contacts.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(Contacts.Data.DATA1, contact.email)
                    .withValue(Contacts.Data.DATA2, CommonDataKinds.Email.TYPE_WORK)
                    .build();

            operations = new ArrayList<>();
            operations.add(op_name);
            operations.add(op_phone);
            operations.add(op_email);

            // 批量提交4个操作，要么全部成功，要么全部失败
            contentResolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (OperationApplicationException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}