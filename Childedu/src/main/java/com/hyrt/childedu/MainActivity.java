package com.hyrt.childedu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.hyrt.childedu.view.XlistviewActivity;
import com.hyrt.childedu.view.xlistview.XListView;
import com.hyrt.childeduteacher.SampleList;
import com.hyrt.datahelper.adapter.SimpleBeanAdapter;
import com.hyrt.datahelper.student.model.LoginUser;
import com.hyrt.datahelper.student.request.LoginRequest;
import com.hyrt.datahelper.UItools.UsernameItemBean;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import java.util.HashMap;
import roboguice.RoboGuice;
import roboguice.activity.event.OnCreateEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentViewListener;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

/**
 * main activity
 */
public class MainActivity extends BaseActivity{

    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>,Object>();
    protected EventManager eventManager;
    protected SimpleBeanAdapter simpleBeanAdapter;
    protected LoginUser.List loginUsers=new LoginUser.List();
    @InjectView(R.id.xlistview) private XListView xlistview;
    @InjectView(R.id.text) private TextView text;
    @InjectView(R.id.progressbar) private ProgressBar progressBar;
    @Inject
    ContentViewListener ignored; // BUG find a better place to put this
    private SuggestionsAdapter mSuggestionsAdapter;
    private static final String[] COLUMNS = {
            BaseColumns._ID,
            SearchManager.SUGGEST_COLUMN_TEXT_1,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
        final RoboInjector injector = RoboGuice.getInjector(this);
        eventManager = injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(savedInstanceState);
        eventManager.fire(new OnCreateEvent(savedInstanceState));
        setContentView(R.layout.activity_main);
        text.setText("successfully");

        simpleBeanAdapter=new SimpleBeanAdapter(MainActivity.this,
                loginUsers,
                R.layout.layout_simplebeen_item,
                new String[]{"getLogin"},
                new int[]{R.id.textview});
        xlistview.setAdapter(simpleBeanAdapter);
        performRequest();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;
        SearchView searchView = new SearchView(getSupportActionBar().getThemedContext());
        searchView.setQueryHint("Search for countrie");
        searchView.setOnQueryTextListener(new onListenerSearchView());
        searchView.setOnSuggestionListener(new onListenerSearchView());
        if (mSuggestionsAdapter == null) {
            MatrixCursor cursor = new MatrixCursor(COLUMNS);
            cursor.addRow(new String[]{"1", "'Murica"});
            cursor.addRow(new String[]{"2", "Canada"});
            cursor.addRow(new String[]{"3", "Denmark"});
            mSuggestionsAdapter = new SuggestionsAdapter(getSupportActionBar().getThemedContext(), cursor);
        }

        searchView.setSuggestionsAdapter(mSuggestionsAdapter);

        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.abs__ic_search)
                .setActionView(searchView)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        return true;
    }

    private class onListenerSearchView implements SearchView.OnQueryTextListener,SearchView.OnSuggestionListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(MainActivity.this, "You searched for: " + query, Toast.LENGTH_LONG).show();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }

        @Override
        public boolean onSuggestionSelect(int position) {
            return false;
        }

        @Override
        public boolean onSuggestionClick(int position) {
            Cursor c = (Cursor) mSuggestionsAdapter.getItem(position);
            String query = c.getString(c.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
            Toast.makeText(MainActivity.this, "Suggestion clicked: " + query, Toast.LENGTH_LONG).show();
            return true;
        }
    }
    private class SuggestionsAdapter extends CursorAdapter {

        public SuggestionsAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv = (TextView) view;
            final int textIndex = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
            tv.setText(cursor.getString(textIndex));
        }
    }



    private void performRequest() {
        LoginRequest request = new LoginRequest("","");
        getSpiceManager().execute(request, request.createCacheKey(), DurationInMillis.ONE_MINUTE, new ListLoginRequestListener());
    }

    private class ListLoginRequestListener implements RequestListener<LoginUser.List> {
        @Override
        public void onRequestFailure( SpiceException e ) {
            Toast.makeText(MainActivity.this, "loginfail", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onRequestSuccess( LoginUser.List listTweets ) {
            Toast.makeText(MainActivity.this, "loginsucess"+listTweets.get(0).getLogin(), Toast.LENGTH_LONG).show();
//            loginUsers.addAll(listTweets);
//            simpleBeanAdapter.notifyDataSetChanged();
//            progressBar.setVisibility(View.GONE);
//            xlistview.setVisibility(View.VISIBLE);
//            showtextview();
//            startActivity(new Intent().setClass(MainActivity.this, XlistviewActivity.class));
        }
    }

    private void showtextview(){
        UsernameItemBean usernameItemBean = new UsernameItemBean(MainActivity.this,R.id.textview,R.id.editText);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_inquer);
        linearLayout.addView(usernameItemBean.CreateView(R.layout.layout_inputview));
        usernameItemBean.textView.setText("uername--");
    }

}
