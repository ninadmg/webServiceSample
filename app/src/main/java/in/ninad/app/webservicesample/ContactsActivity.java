package in.ninad.app.webservicesample;


import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


/**
 * Created by ninad on 23-07-2015.
 */
public class ContactsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    contactsCursorAdapter mCursorAdapter;
    ListView mListView;

    private static final String[] PROJECTION =
            {
                    ContactsContract.Data._ID,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                            ContactsContract.Data.DISPLAY_NAME_PRIMARY :
                            ContactsContract.Data.DISPLAY_NAME,
                               ContactsContract.Data.DATA1
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);

        getSupportLoaderManager().initLoader(0,null,this);

        mCursorAdapter = new contactsCursorAdapter(
                this,
                null,false);
        mListView.setAdapter(mCursorAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(
                this,
                ContactsContract.Data.CONTENT_URI,
                PROJECTION,ContactsContract.Data.MIMETYPE +  "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
//                ContactsContract.Data.HAS_PHONE_NUMBER+ " = 1",
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}


