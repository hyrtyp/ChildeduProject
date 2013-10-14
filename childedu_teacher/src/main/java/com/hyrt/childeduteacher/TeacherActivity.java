package com.hyrt.childeduteacher;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class TeacherActivity extends SherlockActivity {

    ActionMode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SampleList.THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mMode=startActionMode(new AnActionModeOfEpicProportions());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;

        menu.add("Save")
                .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add("Search")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add("Refresh")
                .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(TeacherActivity.this,"---"+item.toString(),Toast.LENGTH_SHORT).show();
        return true;
    }

        private final class AnActionModeOfEpicProportions implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //Used to put dark icons on light action bar
            boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;

            menu.add("Save")
                    .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

            menu.add("Search")
                    .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Refresh")
                    .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Save")
                    .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

            menu.add("Search")
                    .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Refresh")
                    .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("Refresh")
                    .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("Refresh")
                    .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("Refresh")
                    .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Toast.makeText(TeacherActivity.this, "Got click: " + item, Toast.LENGTH_SHORT).show();
//            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Toast.makeText(TeacherActivity.this, "destorymode", Toast.LENGTH_SHORT).show();
        }
    }
}
