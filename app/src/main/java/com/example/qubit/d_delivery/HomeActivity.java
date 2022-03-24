package com.example.qubit.d_delivery;
/** Names:
 *  -every id we name it like it is a path: <activityNameInLowerCase>_<typeOfTheItemInOnWORD>_<nameOfTheItem>
 *      like: home_menu_appBarMenu, home_menu_appBarMenu_searchView...
 *  -every xml we name it like the default template
 *      like: activity_search
 *
 **/
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.qubit.d_delivery.addition.ProToast;
import com.example.qubit.d_delivery.dialogs.AboutDialog;
import com.example.qubit.d_delivery.enums.ViewTypeEnum;

/**on progress: setting menu of home page...**/
public class HomeActivity extends AppCompatActivity {

    //UI Variables A-Z:
    Menu appBarMenu;
    ProToast toast;
    SearchView appBarMenuSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toast = new ProToast(this);
    }
    /**creating menu**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        this.appBarMenu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_appbar, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        appBarMenuSearchView = (SearchView) menu.findItem(R.id.home_menu_appBarMenu_search).getActionView();
        setSearchViewActions();
        return true;
    }
    /**set actions of MenuItems selection:**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home_menu_appBarMenu_search:
                break;
            case R.id.home_menu_appBarMenu_group1_theme:
                toast.show("coming soon...");
                break;
            case R.id.home_menu_appBarMenu_group1_setting:
                goToSettingActivity();
                break;
            case R.id.home_menu_appBarMenu_group2_about:
                AboutDialog dialog = new AboutDialog();
                dialog.show(getSupportFragmentManager(), "About");
                break;
            default:
                break;
        }
        return true;
    }

    //private methods A-Z:
    private void setSearchViewActions(){
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        appBarMenuSearchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        appBarMenuSearchView.setOnQueryTextListener(new LocalQueryTextListener(ViewTypeEnum.SEARCH_VIEW));
    }
    private void goToSettingActivity(){
        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
        searchIntent.putExtra("from", "home");
        startActivity(searchIntent);
    }

    private class LocalQueryTextListener implements SearchView.OnQueryTextListener{

        ViewTypeEnum type;
        LocalQueryTextListener(ViewTypeEnum type) {
            this.type = type;
        }
        @Override
        public boolean onQueryTextSubmit(String query) {
            switch(type){
                case SEARCH_VIEW: searchViewSubmit(query);
                    break;
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            switch(type){
                case SEARCH_VIEW: searchViewChange(newText);
                    break;
            }
            return false;
        }

        private void searchViewSubmit(String query){
            toast.show("SUBMIT: " +query);
            /*collapsing the search view:*/
            /*or:*/
            appBarMenuSearchView.onActionViewCollapsed();
            /*or2:*/
            /*invalidateOptionsMenu();*/
            /*or3:*/
            /*appBarMenuSearchView.setQuery("", false);
            appBarMenuSearchView.setIconified(true);*/
        }
        private void searchViewChange(String newText){
            toast.show("TXT CHANGED: " +newText);
        }
    }
}