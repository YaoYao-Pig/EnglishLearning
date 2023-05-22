package com.example.englishlearning.Controller;


import android.app.Activity;
import android.content.res.AssetManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JsonController  {

    public static AssetManager assetManager;
    public String getAssetJson(String filename) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename),"UTF-8"); //使用IO流读取json文件内容
        BufferedReader br = new BufferedReader(inputStreamReader);//使用字符高效流
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = br.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }


    public JsonObject readJson(String filename){
        try {
            JsonObject jsonObject= (JsonObject) JsonParser.parseString(getAssetJson(filename));
            return jsonObject;
//            System.out.println( "cateName"+jsonObject.get("cates").
//                    getAsJsonArray().get(0).getAsJsonObject().
//                    get("cateName").getAsString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject readJsonByString(String s){
            JsonObject jsonObject= (JsonObject) JsonParser.parseString(s);
            return jsonObject;
//            System.out.println( "cateName"+jsonObject.get("cates").
//                    getAsJsonArray().get(0).getAsJsonObject().
//                    get("cateName").getAsString());

    }
    public String getAssetJsonWord(String filename) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename),"UTF-8"); //使用IO流读取json文件内容
        BufferedReader br = new BufferedReader(inputStreamReader);//使用字符高效流
        String line = "";
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        long i=0;
        while ((line = br.readLine())!=null){

            line+=',';
            i+=line.length();
            builder.append(line);
        }
        builder.deleteCharAt((int) i);
        builder.append(']');
        return builder.toString();
    }
    public JsonArray readJsonWord(String filename){
        try {
            JsonArray jsonObject= (JsonArray) JsonParser.parseString(getAssetJsonWord(filename));
            return jsonObject;
//            System.out.println( "cateName"+jsonObject.get("cates").
//                    getAsJsonArray().get(0).getAsJsonObject().
//                    get("cateName").getAsString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
