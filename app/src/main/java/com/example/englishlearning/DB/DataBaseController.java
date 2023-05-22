package com.example.englishlearning.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.englishlearning.Bean.WordListBean;
import com.example.englishlearning.Controller.JsonController;
import com.example.englishlearning.Status;
import com.example.englishlearning.enity.Book;
import com.example.englishlearning.enity.MyDate;
import com.example.englishlearning.enity.User;
import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.Word_Sentence;
import com.example.englishlearning.enity.Word_Translation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DataBaseController extends SQLiteOpenHelper {


    private static final String TABLENAME_USER = "t_USER";
    private static final String USER_ID = "user_id";
    private static final String USERNAME = "username";
    private static final String CREATE_TIME = "create_time";
    private static final String FLAG = "flag";
    private static final String TABLENAME_BOOK = "t_book";
    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "book_name";
    private static final String BOOK_COVER = "book_cover";
    private static final String WORD = "word";
    private static final String WORD_NUMBER = WORD + "_number";
    private static final String TABLENAME_WORD = "t_" + WORD;
    private static final String WORD_ID = WORD + "_id";
    private static final String FK_BOOK = "fk_book";
    private static final String PS = "ps";
    private static final String SENTENCE = "sentence";
    private static final String HAS_LEARN = "has_learn";
    private static final String TABLENAME_LEARN = "t_learn";
    private static final String LEARN_TIME = "learn_time";
    private static final String FK_WORD = "fk_word";
    private static final String PASSWORD = "password";
    private static final String CONTENT = "content";
    private static final String FILE_NAME = "file_name";
    private static final String TRANS = "trans";
    private static final String TABLENAME_WORD_TRAN = "t_word_tran";
    private static final String TABLENAME_WORD_SENTENCE = "t_word_sentence";
    private static final String WORD_SENTENCE_ID = "word_sentence_id";
    private static final String S_CONTENT = "s_content";
    private static final String S_CN = "s_cn";
    private static final String TRAN_CN = "tran_cn";
    private static final String POS = "pos";
    private static final String TRAN_OTHER = "tran_other";
    private static final String TRAN_ID = "tran_id";
    private static final String LEARN_ID = "learn_id";
    private static final String HAS_REVIEW = "has_review";
    private static final String TABLENAME_REVIEW = "t_review";
    private static final String REVIEW_ID = "review_id";
    private static final String C_DAY = "c_day";
    private static final String HAS_LEARN_WORD = "has_learn_word";
    private static final String COIN = "coin";
    private static final String LOGIN_ID = "login_id";
    private static final String LOGIN_TIME = "login_time";
    private static final String TABLENAME_LOGIN_DATA = "t_login_data";


    public DataBaseController(@Nullable Context context) {
        super(context, "english.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_user= "CREATE TABLE " + TABLENAME_USER + "(" +
                USER_ID + " integer primary key not null," +
                USERNAME + " varchar(255) not null," +
                PASSWORD + " varchar(255) not null," +
                CREATE_TIME + " date," +
                C_DAY + " integer," +
                HAS_LEARN_WORD + " integer," +
                COIN + " integer," +
                FLAG + " varchar(255)"+
                ");";
        String sql_book= "CREATE TABLE  " + TABLENAME_BOOK + "(" +
                BOOK_ID + " integer primary key not null," +
                BOOK_NAME + " varchar(255) not null," +
                BOOK_COVER + " text not null," +
                CONTENT + " text not null," +
                FILE_NAME + " text not null," +
                WORD_NUMBER + " integer" +
                ");";
        String sql_word= "CREATE TABLE " + TABLENAME_WORD + "(" +
                WORD_ID + " integer primary key not null," +
                BOOK_ID + " integer," +
                WORD + " varchar(255)," +
                PS + " varchar(255)," +
                HAS_LEARN + " integer," +
                FLAG + " integer" +
                ");";
        String sql_learn= "CREATE TABLE " + TABLENAME_LEARN + "(" +
                LEARN_ID + " integer primary key not null," +
                WORD_ID + " integer," +
                LEARN_TIME + " date," +
                HAS_REVIEW + " integer," +
                "FOREIGN KEY " +"("+WORD_ID +")"+
                " REFERENCES "+TABLENAME_WORD+"("+WORD_ID+")" +
                ");";

        String sql_word_tran= "CREATE TABLE " + TABLENAME_WORD_TRAN + "(" +
                TRAN_ID + " integer primary key not null," +
                WORD_ID+" integer," +
                TRAN_CN + " text," +
                POS + " text," +
                TRAN_OTHER + " text," +
                "FOREIGN KEY " +"("+WORD_ID +")"+ " REFERENCES "+TABLENAME_WORD+"("+WORD_ID+")" +
                ");";
        String sql_word_sentence= "CREATE TABLE " + TABLENAME_WORD_SENTENCE + "(" +
                WORD_SENTENCE_ID + " integer primary key not null," +
                WORD_ID+ " integer," +
                S_CONTENT + " text," +
                S_CN + " text," +
                "FOREIGN KEY " +"("+WORD_ID +")"+ " REFERENCES "+TABLENAME_WORD+"("+WORD_ID+")" +
                ");";
        String sql_review= "CREATE TABLE " + TABLENAME_REVIEW + "(" +
                REVIEW_ID + " integer primary key not null," +
                WORD_ID+ " integer," +
                LEARN_TIME+" date," +
                "FOREIGN KEY " +"("+WORD_ID +")"+
                " REFERENCES "+TABLENAME_WORD+"("+WORD_ID+")" +
                ");";
        String sql_login_data= "CREATE TABLE " + TABLENAME_LOGIN_DATA + "(" +
                LOGIN_ID + " integer primary key not null," +
                USER_ID+ " intger," +
                LOGIN_TIME + " text" +
                ");";
        db.execSQL(sql_user);
        db.execSQL(sql_book);
        db.execSQL(sql_word);
        db.execSQL(sql_learn);
        db.execSQL(sql_word_tran);
        db.execSQL(sql_word_sentence);
        db.execSQL(sql_review);
        db.execSQL(sql_login_data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getSQLiteDatabase(){

        return this.getReadableDatabase();

    }




    //book
    public int initializeBookList(int flag)  {
        if(flag==0){//flag是零就执行初始化
            if(getReadableDatabase().query(TABLENAME_BOOK,new String[]{BOOK_ID},null,null,null,null,null).getCount()!=0){
                ;
            }
            else{
                JsonController jsonController=new JsonController();
                JsonObject jsonObject=jsonController.readJson("resource.json");
                JsonArray cates= (JsonArray) jsonObject.get("cates");
                int num_cates=cates.size();
                for(int i=0;i<num_cates;++i){
                    JsonArray bookList= (JsonArray)( ((JsonObject)cates.get(i)).get("bookList"));
                    int num_books=bookList.size();
                    for(int j=0;j<num_books;++j){
                        JsonObject bookJson= (JsonObject) bookList.get(j);
                        Book book=new Book(-1,bookJson.get("title").getAsString(),
                                bookJson.get("cover").getAsString(),
                                bookJson.get("offlinedata").getAsString(),
                                bookJson.get("wordNum").getAsInt(),
                                bookJson.get("id").getAsString());
                        addBook(book);
                    }


                }

                return Status.SUCCESS;
            }

        }
        else{
            return Status.SUCCESS;
        }
        return Status.SUCCESS;
    };
    public String getFormatDate(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public int addBook(Book book){
        ContentValues cv=new ContentValues();
        cv.put(BOOK_NAME,book.getBook_name());
        cv.put(BOOK_COVER,book.getCover());
        cv.put(WORD_NUMBER,book.getWord_number());
        cv.put(CONTENT,book.getContent());
        cv.put(FILE_NAME,book.getFile_name());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert(TABLENAME_BOOK,BOOK_NAME,cv);
        if(insert==-1){
            cv.clear();
            return Status.FAIL;
        }
        cv.clear();
        return Status.SUCCESS;

    }


    public String[] getBookColumns(){
        return new String[]{BOOK_ID,BOOK_NAME, BOOK_COVER,CONTENT,FILE_NAME};
    }

    //word
public void clearWordList(){
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("delete from "+ TABLENAME_WORD_SENTENCE);
    db.execSQL("delete from "+ TABLENAME_WORD_TRAN);
    db.execSQL("delete from "+ TABLENAME_WORD);
    db.execSQL("delete from "+ TABLENAME_LEARN);
    db.execSQL("delete from "+ TABLENAME_REVIEW);

    db.close();

}

public void initializeWordList(int flag,String filename){
        clearWordList();
        if(flag==0){

            JsonController jsonController=new JsonController();
            JsonArray jsonArray=jsonController.readJsonWord(filename);



            int size=jsonArray.size();
            for(int i=0;i<size;++i){
                JsonObject word=jsonArray.get(i).getAsJsonObject().get("content").getAsJsonObject().get("word").getAsJsonObject();
                JsonObject content=word.get("content").getAsJsonObject();
                JsonArray sentences;
                if(content.get("sentence")==null){
                    sentences=null;
                }
                else{
                    sentences=content.get("sentence").getAsJsonObject().get("sentences").getAsJsonArray();
                }

                JsonArray translations=content.get("trans").getAsJsonArray();

                //sentence

                List<Word_Sentence> sens=new ArrayList<>();
                int ssize=sentences==null?0:sentences.size();
                for (int j=0;j<ssize;++j){
                    sens.add(new Word_Sentence(-1,i+1,
                            sentences.get(j).getAsJsonObject().get("sContent").getAsString(),
                            sentences.get(j).getAsJsonObject().get("sCn").getAsString()));
                }


                //translation
                List<Word_Translation> trans=new ArrayList<>();
                int tsize=translations.size();
                for(int j=0;j<tsize;++j){
                    JsonObject tmp=translations.get(j).getAsJsonObject();

                    trans.add(new Word_Translation(-1,i+1,
                            tmp.get("tranCn")==null?null:tmp.get("tranCn").getAsString(),
                            tmp.get("pos")==null?null:tmp.get("pos").getAsString(),
                            tmp.get("tranOther")==null?null:tmp.get("tranOther").getAsString()
                            ));
                }

                if(content.get("ukphone")!=null){
                    addWord(new Word(i+1, -1,
                            word.get("wordHead").getAsString(),
                            content.get("ukphone").getAsString(),
                            0,
                            trans,
                            sens,
                            0
                    ));
                }
                else if(content.get("usphone")!=null){
                    addWord(new Word(i+1, -1,
                            word.get("wordHead").getAsString(),
                            content.get("usphone").getAsString(),
                            0,
                            trans,
                            sens,
                            0
                    ));
                }
                else{
                    addWord(new Word(i+1, -1,
                            word.get("wordHead").getAsString(),
                            new String(),
                            0,
                            trans,
                            sens,
                            0
                    ));
                }

            }
           // System.out.println(jsonArray.get(1).toString());


        }
    }

    public int addWord_Sentences(List<Word_Sentence> s){
        ContentValues cv=new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int len=s.size();
        for(int i=0;i<len;++i){
            cv.clear();
            Word_Sentence tmp=s.get(i);
            cv.put(WORD_ID,tmp.getWord_id());
            cv.put(S_CONTENT,tmp.getS_content());
            cv.put(S_CN,tmp.getS_cn());
            long insert=sqLiteDatabase.insert(TABLENAME_WORD_SENTENCE,WORD_ID,cv);
            if(insert==-1){
                sqLiteDatabase.close();
                throw new RuntimeException("Insert word sentence fail!");
            }
        }
        sqLiteDatabase.close();
        return Status.SUCCESS;

    }
    public int addWord_Translations(List<Word_Translation> t){
        ContentValues cv=new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int len=t.size();
        for(int i=0;i<len;++i){
            cv.clear();
            Word_Translation tmp=t.get(i);
            cv.put(WORD_ID,tmp.getWord_id());
            cv.put(TRAN_CN,tmp.getTranCn());
            cv.put(POS,tmp.getPos());
            cv.put(TRAN_OTHER,tmp.getTranOther());

            long insert=sqLiteDatabase.insert(TABLENAME_WORD_TRAN,WORD_ID,cv);
            if(insert==-1){
                sqLiteDatabase.close();
                throw new RuntimeException("Insert word trans fail!");
            }
        }
        sqLiteDatabase.close();
        return Status.SUCCESS;
    }



    public int addWord(Word word){
        ContentValues cv=new ContentValues();
        cv.put(WORD_ID,word.getWord_id());
        cv.put(BOOK_ID,word.getBook_id());
        cv.put(WORD,word.getWord());
        cv.put(PS,word.getPs());
        cv.put(HAS_LEARN,word.getHas_learn());

        List<Word_Translation> trans=word.getTrans();
        List<Word_Sentence> sens=word.getSentence();


        addWord_Sentences(sens);
        addWord_Translations(trans);


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert(TABLENAME_WORD,WORD,cv);
        if(insert==-1){
            sqLiteDatabase.close();
            return Status.FAIL;
        }
        sqLiteDatabase.close();
        return Status.SUCCESS;
    }
    public List<Word_Sentence> getSentence(Integer word_id){
        List<Word_Sentence> sentences=new ArrayList<>();
        String[] columns={
                WORD_SENTENCE_ID,WORD_ID,S_CONTENT,S_CN
        };
        Cursor cv=this.getReadableDatabase().query(TABLENAME_WORD_SENTENCE,columns,
                WORD_ID+"=?",new String[]{
                        word_id.toString(),
                },null,null,null);
        cv.moveToFirst();
        while(!cv.isAfterLast()){
            sentences.add(new Word_Sentence(cv.getInt(0),
                    cv.getInt(1),cv.getString(2),cv.getString(3)));
            cv.moveToNext();
        }
        return sentences;
    }
    public List<Word_Translation> getTranslations(Integer word_id){
        List<Word_Translation> translations=new ArrayList<>();
        String[] columns= {
            TRAN_ID,WORD_ID,TRAN_CN,POS,TRAN_OTHER
        };
        Cursor cv=this.getReadableDatabase().query(TABLENAME_WORD_TRAN,columns,
                WORD_ID+"=?",new String[]{
                        word_id.toString(),
                },null,null,null);
        cv.moveToFirst();
        while(!cv.isAfterLast()){

            translations.add(new Word_Translation(
                    cv.getInt(0),
                    cv.getInt(1),cv.getString(2),cv.getString(3),cv.getString(4))
            );
            cv.moveToNext();
        }
        return translations;
    }
    public List<Word> getNotLearnedWord(int num){
        SQLiteDatabase sql =this.getReadableDatabase();
        String[] columns={WORD_ID,BOOK_ID,WORD,PS,HAS_LEARN};

        Cursor cv= sql.query(TABLENAME_WORD,columns,HAS_LEARN+"=?",
                new String[]{"0"},null,null,null);
        cv.moveToFirst();
        List<Word> words=new ArrayList<>();
        for(int i=0;i<num;++i){
            words.add(new Word(cv.getInt(0),
                    cv.getInt(1),
                    cv.getString(2),
                    cv.getString(3),
                    cv.getInt(4),
                    getTranslations(cv.getInt(0)),
                    getSentence(cv.getInt(0)),0));
            cv.moveToNext();
        }
        return words;
    }

    public boolean isReviewEmpty(){
        String[] columns={REVIEW_ID};
        Cursor cv=this.getReadableDatabase().query(TABLENAME_REVIEW,columns,null,null,null,null,null,null);
        if(cv.getCount()==0){
            return true;
        }
        return false;
    }
    public void initializeReviewTable(){
        SQLiteDatabase sql =this.getWritableDatabase();
        String[] columns={
                WORD_ID,LEARN_TIME,HAS_REVIEW
        };
        long oneDayTime = 1000*3600*24;
        Date today=new Date();
        String where=HAS_REVIEW+"=? AND "+ LEARN_TIME+"<=? AND "+LEARN_TIME+">=? ";
        Cursor cursor=sql.query(TABLENAME_LEARN,columns,where,new String[]{
                "0",
                getFormatDate(today),
                getFormatDate(new Date(today.getTime()-oneDayTime))

        },null,null,null,null);

        ContentValues cv=new ContentValues();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            cv.clear();
            cv.put(WORD_ID,cursor.getInt(0));
            cv.put(LEARN_TIME,cursor.getString(1));
            sql.insert(TABLENAME_REVIEW,WORD_ID,cv);
            cursor.moveToNext();
        }
    }
    private List<Integer> getReviewWordId(){
        SQLiteDatabase sql =this.getReadableDatabase();
        String[] columns={WORD_ID};
        Cursor cv=sql.query(TABLENAME_REVIEW,columns,null,null,null,null,null,null);
        List<Integer> ids=new ArrayList<>();
        cv.moveToFirst();
        while(!cv.isAfterLast()){
            ids.add(cv.getInt(0));
            cv.moveToNext();
        }
        //sql.close();
        return ids;

    }
    public List<Word> getReviewWord(int num){
        SQLiteDatabase sql =this.getWritableDatabase();
        String[] columns={WORD_ID,BOOK_ID,WORD,PS,HAS_LEARN};
        List<Integer> ids=getReviewWordId();

        String str_columns="";
        for(int i=0;i<5;++i){
            //strings[i]=ids.get(i).toString();
            if(i!=0){
                str_columns+=',';
            }
            str_columns+=columns[i];
        }

        String str_string="";
        String[] strings=new String[ids.size()];
        for(int i=0;i<ids.size();++i){
            //strings[i]=ids.get(i).toString();
            if(i!=0){
                str_string+=',';
            }
            str_string+=ids.get(i).toString();
        }

        Cursor cv=sql.rawQuery("select "+str_columns+
                " from "+TABLENAME_WORD+
                " where "+WORD_ID+" in("+str_string+");",null);
//        = sql.query(TABLENAME_WORD,columns,WORD_ID+" in ?",
//                strings,null,null,null);
        cv.moveToFirst();
        List<Word> words=new ArrayList<>();
        for(int i=0;i<num;++i){
            if(cv.isAfterLast()){
                break;
            }
            words.add(new Word(cv.getInt(0),
                    cv.getInt(1),
                    cv.getString(2),
                    cv.getString(3),
                    cv.getInt(4),
                    getTranslations(cv.getInt(0)),
                    getSentence(cv.getInt(0)),0));
            cv.moveToNext();
        }
        return words;
    }






    //user
    public int registerUser(User user){
        ContentValues cv=new ContentValues();
        cv.put(USERNAME,user.getUsername());
        cv.put(PASSWORD,user.getPassword());
        Date date=new Date();
        cv.put(CREATE_TIME,getFormatDate(date));
        cv.put(C_DAY,0);
        cv.put(HAS_LEARN_WORD,0);
        cv.put(COIN,0);
        cv.put(FLAG,"0");

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        String[] columns={USERNAME,PASSWORD};
        Cursor cursor=sqLiteDatabase.query(TABLENAME_USER,columns,USERNAME+"=?",new String[]{user.getUsername()},null,null,null);
        if(cursor.getCount()!=0){
            cursor.close();
            cv.clear();
            return Status.HAS_EXIST;
        }
        long insert=sqLiteDatabase.insert(TABLENAME_USER,USERNAME,cv);
        if(insert==-1){
            cursor.close();
            cv.clear();
            return Status.FAIL;
        }
        cursor.close();
        cv.clear();
        return Status.SUCCESS;

    }
    public int loginUser(User user){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String[] columns={USERNAME,PASSWORD,USER_ID};
        Cursor cursor=sqLiteDatabase.query(TABLENAME_USER,columns,USERNAME+"=?",new String[]{user.getUsername()},null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()==0){
            cursor.close();
            return Status.FAIL;
        }
        else if(cursor.getCount()>1){
            cursor.close();
            return Status.TOO_MANY_ITEMS;
        }
        else{
                String tmp=cursor.getString(1);
                Integer user_id=cursor.getInt(2);
                ContentValues cv=new ContentValues();
                cv.put(USER_ID,user_id);
                cv.put(LOGIN_TIME,getFormatDate(new Date()));

                sqLiteDatabase.insert(TABLENAME_LOGIN_DATA,USER_ID,cv);
                if(tmp.equals(user.getPassword())){
                    cursor.close();
                    return Status.SUCCESS;
                }

            cursor.close();
            return Status.FAIL;
        }
    }



    //learn

    public void addLearn(Word word) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(WORD_ID,word.getWord_id());
        cv.put(LEARN_TIME,getFormatDate(new Date()));
        cv.put(HAS_REVIEW,"0");
        long insert=sqLiteDatabase.insert(TABLENAME_LEARN,WORD_ID,cv);
        if(insert==-1){
            throw new RuntimeException("addLearn error!");
        }

    }


    public void updateHasStar(Integer wordId,Integer flag){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FLAG,flag.toString());
        sqLiteDatabase.update(TABLENAME_WORD,cv,WORD_ID+"=?",new String[]{
                wordId.toString()
        });
    }


    public void updateHasReview(Integer wordId){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(HAS_REVIEW,"1");
        sqLiteDatabase.update(TABLENAME_LEARN,cv,WORD_ID+"=?",new String[]{
                wordId.toString()
        });
    }

    public void updateWordHasLearn(Integer currentId) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(HAS_LEARN,"1");
        sqLiteDatabase.update(TABLENAME_WORD,cv,WORD_ID+"=?",new String[]{
                currentId.toString()
        });

    }

    public void deleteReviewed(Integer currentId) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLENAME_REVIEW,WORD_ID+"=?",new String[]{
                currentId.toString()
        });
    }


    public boolean selectWordStar(Integer wordId){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.query(TABLENAME_WORD,new String[]{FLAG},WORD_ID+"=?",new String[]{wordId.toString()},null,null,null);
        cursor.moveToFirst();

        if(cursor.getInt(0)==0){
            return false;
        }
        return true;
    }


    public List<WordListBean> getStarWordBean(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor_word=sqLiteDatabase.query(TABLENAME_WORD,new String[]{WORD_ID,WORD},FLAG+"=?",new String[]{"1"},null,null,null);
        cursor_word.moveToFirst();
        List<WordListBean> wordListBeanList=new ArrayList<>();
        while(!cursor_word.isAfterLast()){
            Integer id=cursor_word.getInt(0);
            Cursor cursor_trans=sqLiteDatabase.query(TABLENAME_WORD_TRAN,new String[]{TRAN_CN},WORD_ID+"=?",new String[]{id.toString()},null,null,null);
            cursor_trans.moveToFirst();
            wordListBeanList.add(new WordListBean(id,cursor_word.getString(1),cursor_trans.getString(0)));
            cursor_word.moveToNext();
        }
        return wordListBeanList;


    }

    public Word getWordById(Integer wordId) {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String[] columns=new String[]{
                WORD_ID,WORD,PS
        };
        Cursor cursor_word=sqLiteDatabase.query(TABLENAME_WORD,columns,
                WORD_ID+"=?",new String[]{wordId.toString()},
                null,null,null);
        cursor_word.moveToFirst();
        if(!cursor_word.isAfterLast()){
            return new Word(wordId,-1,cursor_word.getString(1),
                    cursor_word.getString(2),
                    0,
                    getTranslations(wordId),
                    getSentence(wordId),
                    0);
        }
        throw new RuntimeException("can't get word");

    }

    public List<Word> getWordsByWord(String[] words) {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String[] columns=new String[]{
                WORD_ID,WORD,PS
        };
        List<Word> wordList=new ArrayList<>();
        for(int i=0;i<words.length;++i){
            Cursor cursor_word=sqLiteDatabase.query(TABLENAME_WORD,columns,
                    WORD+"=?",new String[]{words[i].toString()},
                    null,null,null);
            cursor_word.moveToFirst();
            if(cursor_word.getCount()==0){
                System.out.println(words[i]+"没有找到");
                continue;
            }

            int wordId=cursor_word.getInt(0);
            if(!cursor_word.isAfterLast()){
                 wordList.add(new Word(wordId,-1,cursor_word.getString(1),
                        cursor_word.getString(2),
                        0,
                        getTranslations(wordId),
                        getSentence(wordId),
                        0));
            }

        }


        return wordList;
    }

    public User getCurrentUser() {

        Cursor cursor=getReadableDatabase().query(TABLENAME_LOGIN_DATA,new String[]{USER_ID},null,null,null,null,null);
        cursor.moveToLast();
        Integer user_id=cursor.getInt(0);
        Cursor cursor_user=getReadableDatabase().query(TABLENAME_USER,new String[]{
                USERNAME,PASSWORD,CREATE_TIME,C_DAY,HAS_LEARN_WORD,COIN
                },
                USER_ID+"=?",new String[]{user_id.toString()},null,null,null);
        cursor_user.moveToFirst();
        User user=new User(user_id,cursor_user.getString(0),cursor_user.getString(1),
                   cursor_user.getString(2),cursor_user.getInt(3),cursor_user.getInt(4),cursor_user.getInt(5),"0");

            return user;


    }

    public void increaseRememberNumber() {
        User user=getCurrentUser();
        Integer user_id=user.getUser_id();

        ContentValues cv=new ContentValues();
        Integer has_learn_wordNum=user.getHas_learn_word();
        cv.put(HAS_LEARN_WORD,has_learn_wordNum+1);
        this.getWritableDatabase().update(TABLENAME_USER,cv,USER_ID+"=?",new String[]{
                user_id.toString()
        });


    }

    public void increaseCoinNum(int num) {
        User user=getCurrentUser();
        Integer user_id=user.getUser_id();
        Integer coin=user.getCoin();
        ContentValues cv=new ContentValues();
        cv.put(COIN,coin+num);
        this.getWritableDatabase().update(TABLENAME_USER,cv,USER_ID+"=?",new String[]{
                        user_id.toString()
        });
    }

    public Word getRandomWord() {
        Random r=new Random();
        Integer word_id=r.nextInt(300);
        return getWordById(word_id);
    }

    public boolean isWordTableEmpty() {

        Cursor cursor=getReadableDatabase().query(TABLENAME_WORD,new String[]{WORD_ID},null,null,null,null,null);
        if(cursor.getCount()==0){
            return true;
        }
        return false;
    }

    public List<Word> searchWordByWord(String search_text) {

        String[] columns=new String[]{
                WORD_ID,WORD,PS
        };

        String str_columns="";
        for(int i=0;i<columns.length;++i){
            if(i!=0){
                str_columns+=',';
            }
            str_columns+=columns[i];
        }
        String str_string="\'%"+search_text+"%\'";




        Cursor cursor_word=this.getReadableDatabase().rawQuery("select "+str_columns+
                " from "+TABLENAME_WORD+
                " where "+WORD+" like "+str_string+";",null);

        cursor_word.moveToFirst();
        List<Word> words=new ArrayList<>();
        while (!cursor_word.isAfterLast()){
            words.add(new Word(cursor_word.getInt(0),-1,cursor_word.getString(1),
                    cursor_word.getString(2),
                    0,
                    getTranslations(cursor_word.getInt(0)),
                    getSentence(cursor_word.getInt(0)),
                    0));
            cursor_word.moveToNext();
        }
        return words;

    }

    public int getLearnWordDate(String dayTime) {


        Cursor cursor=this.getReadableDatabase().query(TABLENAME_LEARN,new String[]{
                LEARN_ID
        },LEARN_TIME+"=?",new String[]{dayTime},null,null,null);
        return cursor.getCount();

    }


    public int getReviewWordDate(String dayTime) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select "+LEARN_ID+" from "+TABLENAME_LEARN+
                " where "+LEARN_TIME+"=? AND "+HAS_REVIEW+"=?",new String[]{
                dayTime,
                "1"
        });
        return cursor.getCount();
    }

    public List<MyDate> getHasLearnDateList() {
        String today=getFormatDate(new Date());
        StringBuffer stringBuffer=new StringBuffer(today);
        String firstDay=stringBuffer.replace(8,9,"01").toString();

        Cursor cursor=this.getReadableDatabase().rawQuery("select "+LEARN_ID+","+LEARN_TIME+" from "+TABLENAME_LEARN+
                " where "+LEARN_TIME+">=? AND "+LEARN_TIME+"<?",new String[]{
                firstDay,today
        });
        cursor.moveToFirst();
        List<MyDate> myDates=new ArrayList<>();
        while (!cursor.isAfterLast()){
            myDates.add(new MyDate(cursor.getString(1)));
            cursor.moveToNext();
        }
        return myDates;

    }

    public List<Word> getGenerateWord(int num) {
        List<Word> words=new ArrayList<>();
        Cursor cursor=this.getReadableDatabase().rawQuery("select "+WORD_ID+" from "+TABLENAME_LEARN+
                " where "+HAS_REVIEW+"=?",new String[]{
                "1"
        });
        int len=cursor.getCount();
        if(len<num){
            num=len;
        }
        Random random=new Random();
        Set<Integer> set=new HashSet<>();
        while(set.size()!=num){
            int pos=random.nextInt(len);

            cursor.moveToPosition(pos);

            set.add(cursor.getInt(0));
        }
        for(int pos : set){
            words.add(getWordById(pos));
        }
        return words;

    }
}
