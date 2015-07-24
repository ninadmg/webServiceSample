package in.ninad.app.webservicesample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import in.ninad.app.webservicesample.model.User;

/**
 * Created by ninad on 23-07-2015.
 */
public class UserDataSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = { DbHelper.COLUMN_ID,
            DbHelper.COLUMN_NAME,DbHelper.COLUMN_LOCATION,DbHelper.COLUMN_REP };


    public UserDataSource(Context contect) {
        dbHelper = new DbHelper(contect);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long aduser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, user.getDisplayName());
        values.put(DbHelper.COLUMN_LOCATION, user.getLocation());
        values.put(DbHelper.COLUMN_REP, user.getReputation());
        long insertId = database.insert(DbHelper.TABLE_USERS, null,
                values);
        return insertId;
    }

    public void removeUser(User user)
    {
        database.delete(DbHelper.TABLE_USERS, DbHelper.COLUMN_NAME
                + " = " + user.getDisplayName(), null);
    }

    public void removeAllUser()
    {
        database.delete(DbHelper.TABLE_USERS,null, null);
    }

    public List<User> getAllUser() {
        List<User> comments = new ArrayList<User>();

        Cursor cursor = database.query(DbHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User comment = cursorToUser(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setUserId((int) cursor.getLong(0));
        user.setDisplayName(cursor.getString(1));
        user.setLocation(cursor.getString(2));
        user.setReputation(cursor.getInt(3));
        return user;
    }
}
