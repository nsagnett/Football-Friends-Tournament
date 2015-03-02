package nsapp.com.footballfriendstournament.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nsapp.com.footballfriendstournament.R;
import nsapp.com.footballfriendstournament.model.Match;
import nsapp.com.footballfriendstournament.model.Team;
import nsapp.com.footballfriendstournament.model.rss.RSSItem;

public class MatchesAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Match> matches = new ArrayList<>();

    public MatchesAdapter(Context context, ArrayList<Match> matches) {
        super();
        this.context = context;
        this.matches = matches;
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Match getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Match match = matches.get(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.holder_match, null);
        }

        TextView teamName1 = (TextView) convertView.findViewById(R.id.teamName1);
        TextView score = (TextView) convertView.findViewById(R.id.score);
        TextView teamName2 = (TextView) convertView.findViewById(R.id.teamName2);

        Team team1 = match.getHome();
        Team team2 = match.getOutside();

        teamName1.setText(team1.getName());
        score.setText(String.format(context.getString(R.string.score_format), team1.getGoalsFor(), team2.getGoalsFor()));
        teamName2.setText(team2.getName());

        return convertView;
    }
}
