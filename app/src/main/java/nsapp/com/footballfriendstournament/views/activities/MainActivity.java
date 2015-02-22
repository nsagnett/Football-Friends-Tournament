package nsapp.com.footballfriendstournament.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.Competition;
import nsapp.com.footballfriendstournament.views.fragments.MainFragment;
import nsapp.com.footballfriendstournament.views.fragments.NewsFragment;

public class MainActivity extends ActionBarActivity {

    private Competition competition;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, (new MainFragment())).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!(getFragment() instanceof NewsFragment)) {
            getMenuInflater().inflate(R.menu.global, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_rss) {
            prepareOnReplaceTransaction(new NewsFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    private Fragment getFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    public void prepareOnReplaceTransaction(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment instanceof NewsFragment) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_down);
        }
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
