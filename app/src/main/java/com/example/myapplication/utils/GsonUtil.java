package com.example.myapplication.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
  * Created by hml on 2023/4/6.
 */
public class GsonUtil {

    private static Gson gson;

    static {
        gson = null == gson ? new Gson():gson;
    }

    private static final String TAG = "GsonUtil";

    public static String getStringValue (String key, JsonObject jsonObject) {
        if (null == jsonObject) {
            Log.w(TAG, "getStringValue()--->: jsonObject maybe not null!");
            return "";
        }
        JsonElement element = jsonObject.get(key);
        if (null == element) {
            Log.w(TAG, "getStringValue()--->: element maybe not null!");
            return "";
        }
        return element.getAsString();
    }

    public static long getLongValue (String key, JsonObject jsonObject) {
        if (null == jsonObject) {
            Log.w(TAG, "getLongValue()--->: jsonObject maybe not null!");
            return 0;
        }
        JsonElement element = jsonObject.get(key);
        if (null == element) {
            Log.w(TAG, "getLongValue()--->: element maybe not null!");
            return 0;
        }
        return element.getAsLong();
    }

    public static <T> T convert2JsonObject(String json, Class<T> cls) {
        if (TextUtils.isEmpty(json)) {
            Log.w(TAG, "convert2JsonObject()--->: json maybe not empty!");
            return null;
        }
        return gson.fromJson(json,cls);
    }

    public static JsonObject string2JsonObject (String string) {
        if (TextUtils.isEmpty(string)){
            return null;
        }
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(string).getAsJsonObject();
    }

    public static <T> T jsonElement2Object (JsonElement jsonElement, Class<T> cls) {
        if (jsonElement == null) {
            return null;
        }
        return gson.fromJson(jsonElement,cls);
    }

    public static <T> T jsonObject2Object (JsonObject jsonObject, Class<T> cls) {
        if (jsonObject == null) {
            return null;
        }
        return gson.fromJson(jsonObject,cls);
    }

    /**
     * ParameterizedType对象，对于Object、接口和原始类型返回null，对于数组class则是返回Object.class。
     * ParameterizedType是表示带有泛型参数的类型的Java类型，
     * JDK1.5引入了泛型之后，Java中所有的Class都实现了Type接口，ParameterizedType则是继承了Type接口，
     * 所有包含泛型的Class类都会实现这个接口。
     * @param raw
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class raw,final Type... args){
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * 将对象转换成json格式
     *
     * @param ts
     * @return
     */
    public static String objectToJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }
}
