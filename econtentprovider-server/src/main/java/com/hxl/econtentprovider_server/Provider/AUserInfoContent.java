package com.hxl.econtentprovider_server.Provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class AUserInfoContent implements BaseColumns {

    public static final String AUTHORITIES = "com.hxl.econtentprovider_server.Provider.AUserInfoProvider";

    // 访问内容提供器的URI
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/user");

    // 下面是该表的各个字段名称
    public static final String USER_NAME = "name";
    public static final String USER_AGE = "age";
    public static final String USER_HEIGHT = "height";
    public static final String USER_MARRIED = "married";
}
