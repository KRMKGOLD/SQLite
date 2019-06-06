# SQLite with Android

1. SQLite? : 비교적 가벼운 데이터베이스로, 서버가 아니라 응용 프로그램에 넣어 사용하는 비교적 가벼운 데이터베이스.

   

2. Android?

   - Android 자체에서 SQLiteDatabase, SQLiteOpenHelper 등으로 지원해주고 있음.

   - SQL문을 알고 시작하면 매우 편리함. (db.execSQL, db.query...)

   

3. 제작한 예제

   - RecyclerView와 SQLite를 이용한 간단한 학생 정보 정리 프로그램.
   - SQL 관련 코드는 ```MySQLiteOpenHelp``` 에 작성되어 있음.
   - 이외 코드는 Dialog를 통한 선택 등의 내용들이 작성되어 있음.

   

4. 코드

   - **모든 코드는 Kotlin으로 작성되었습니다.**

   1. SQLiteDatabase, SQLiteOpenHelper

      - SQLite 사용에 도움을 주는 클래스들.

      - SQLiteDatabase보다 SQLiteOpenHelper 사용을 권장하고 있다.

        ```kotlin
        class MySQLiteOpenHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
            SQLiteOpenHelper(context, name, factory, version) {
        
            private val TAG = "MySQLiteOpenHelper"
        
            override fun onCreate(db: SQLiteDatabase?) {
                db?.execSQL("Create table student (_id integer primary key autoincrement, name text, age integer, address text)")
            }
        
            override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                db?.execSQL("Drop table if exists student")
                onCreate(db)
            }
        
            override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                onUpgrade(db, oldVersion, newVersion)
            }
            
            ...
        ```

   2. database.execSQL : SELECT문을 제외한 모든 SQL 문장을 실행한다.

      ```sqlite
      db?.execSQL("Create table student (_id integer primary key autoincrement, name text, age integer, address text)")
      ```

      - SQL 문장과 매우 유사해 SQL에 익숙하다면 사용하기 편리하다.

      - 이 예시는 student 테이블을 만드는 예제.

   3. database.rawQuery : SELECT문을 이용해 쿼리를 실행하기 위해서는 rawQuery를 사용하면 된다.
      - 반환형은 Cursor로, Cursor 안에 SELECT문 결과가 반환된다.

