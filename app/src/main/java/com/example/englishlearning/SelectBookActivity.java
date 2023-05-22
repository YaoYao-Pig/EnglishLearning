package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.englishlearning.Controller.DownLoadController;
import com.example.englishlearning.DB.DataBaseController;

import java.util.ArrayList;
import java.util.List;


public class SelectBookActivity extends Activity {
    private Button addBook;
    private DataBaseController dataBaseController;


    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "book_name";
    private static final String BOOK_COVER = "book_cover";
    private static final String WORD = "word";
    private static final String WORD_NUMBER = WORD + "_number";
    private static final String FILE_NAME = "file_name";
    private static final String TABLENAME_BOOK = "t_book";

    private Integer[] book_ids={
            1,2,3,4,5,18,20
    };

     int[] bookCovets=new int[] {
            R.id.item_img_book1,
            R.id.item_img_book2,
            R.id.item_img_book3,
            R.id.item_img_book4,
            R.id.item_img_book5,
            R.id.item_img_book6,
            R.id.item_img_book7
    };
    private final int[] bookName={
            R.id.item_text_book_name1,
            R.id.item_text_book_name2,
            R.id.item_text_book_name3,
            R.id.item_text_book_name4,
            R.id.item_text_book_name5,
            R.id.item_text_book_name6,
            R.id.item_text_book_name7,
    };
    private final int[] bookWordNumber={
            R.id.item_text_book_word_num1,
            R.id.item_text_book_word_num2,
            R.id.item_text_book_word_num3,
            R.id.item_text_book_word_num4,
            R.id.item_text_book_word_num5,
            R.id.item_text_book_word_num6,
            R.id.item_text_book_word_num7,
    };

    private int[] draw={
            R.drawable.cet4luan_1,
            R.drawable.cet6luan_1,
            R.drawable.kaoyanluan_1,
            R.drawable.level4luan_1,
            R.drawable.level8_1,
            R.drawable.lelts_2,
            R.drawable.cre_2
    };


    public Boolean inIds(Integer id){
        int len=7;
        for(int i=0;i<len;++i){
            if(book_ids[i].equals(id)){
                return true;
            }
        }
        return false;
    }

    List<String> nameList=new ArrayList<>();
    List<Integer> numList=new ArrayList<>();
    public void initBookListView(){
        SQLiteDatabase sqLiteDatabase=dataBaseController.getReadableDatabase();
        String[] columns={
                BOOK_ID,BOOK_NAME,BOOK_COVER,FILE_NAME,WORD_NUMBER
        };

        Cursor cursor=sqLiteDatabase.query(TABLENAME_BOOK,columns,null,null,null,null,null);
        cursor.moveToFirst();
        int i=0;
        while(!cursor.isAfterLast()){
            if(inIds(cursor.getInt(0))){
                ImageView imageView=(ImageView) findViewById(bookCovets[i]);
                TextView textView=(TextView) findViewById(bookName[i]);
                TextView number=(TextView) findViewById(bookWordNumber[i]);


                textView.setText(cursor.getString(1));
                nameList.add(cursor.getString(1));

                imageView.setImageDrawable(getResources().getDrawable(draw[i]));
                number.setText( new Integer(cursor.getInt(4)).toString() );
                numList.add(cursor.getInt(4));


                imageView.setOnClickListener(new coverClick());


                i++;
            }
            cursor.moveToNext();
        }
    }

    private void initialize(){
       // bookView=(BookView) findViewById(R.id.select_book);
        dataBaseController=new DataBaseController(SelectBookActivity.this);


        dataBaseController.initializeBookList(0);



        //初始化View列表
        initBookListView();



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectbook);
        initialize();



    }


    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DownLoadController downLoadController=new DownLoadController();
            downLoadController.downLoadBookContent(SelectBookActivity.this,1);
        }
    }

    public void changePage(String filename,int cover,int num,String name){
        Intent intent=new Intent(SelectBookActivity.this,WordBook_Detail.class);
        Bundle bundle=new Bundle();
        bundle.putString("filename",filename);
        bundle.putInt("cover",cover);
        bundle.putInt("num",num);
        bundle.putString("name",name);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    class coverClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.item_img_book1:
                    changePage("CET4luan_1.json",draw[0],numList.get(0), nameList.get(0));
                    //dataBaseController.initializeWordList(0,"CET4luan_1.json");
                    break;
                case R.id.item_img_book2:
                    changePage("CET6luan_1.json",draw[1],numList.get(1), nameList.get(1));
                    //dataBaseController.initializeWordList(0,"CET6luan_1.json");
                    break;
                case R.id.item_img_book3:
                    changePage("KaoYanluan_1.json",draw[2],numList.get(2), nameList.get(2));
                    //dataBaseController.initializeWordList(0,"KaoYanluan_1.json");
                    break;
                case R.id.item_img_book4:
                    changePage("Level4luan_1.json",draw[3],numList.get(3), nameList.get(3));
                    //dataBaseController.initializeWordList(0,"Level4luan_1.json");
                    break;
                case R.id.item_img_book5:
                    changePage("Level8_1.json",draw[4],numList.get(4), nameList.get(4));
                    //dataBaseController.initializeWordList(0,"Level8_1.json");
                    break;
                case R.id.item_img_book6:
                    changePage("IELTSluan_2.json",draw[5],numList.get(5), nameList.get(5));
                    //dataBaseController.initializeWordList(0,"IELTSluan_2.json");
                    break;
                case R.id.item_img_book7:
                    changePage("GRE_2.json",draw[6],numList.get(6), nameList.get(6));
                    //dataBaseController.initializeWordList(0,"GRE_2.json");
                    break;

            }
        }
    }
}
