package nsapp.com.footballfriendstournament;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nsapp.com.footballfriendstournament.model.Championnat;
import nsapp.com.footballfriendstournament.model.Classement;
import nsapp.com.footballfriendstournament.model.Equipe;
import nsapp.com.footballfriendstournament.model.Match;

public class MainFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final Equipe equipe1 = new Equipe("Libourne");
        final Equipe equipe2 = new Equipe("Galgon");
        final Equipe equipe3 = new Equipe("Bordeaux");
        final Equipe equipe4 = new Equipe("Coutras");

        final Championnat championnat = new Championnat(getActivity(), new ArrayList<Equipe>() {{
            add(equipe1);
            add(equipe2);
            add(equipe3);
            add(equipe4);
        }});

        final ListView listView = (ListView) view.findViewById(R.id.listView);
        Button button1 = (Button) view.findViewById(R.id.simulateMatch1);
        Button button2 = (Button) view.findViewById(R.id.simulateMatch2);

        Match m1 = championnat.getJournee(0).get(0);
        Match m2 = championnat.getJournee(0).get(1);
        button1.setText(m1.getDomicile().getNom() + " - " + m1.getExterieure().getNom());
        button2.setText(m2.getDomicile().getNom() + " - " + m2.getExterieure().getNom());

        final ArrayList<String> items = new ArrayList<>();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                championnat.scoreFinal(0,0,4,1,0,0,0,0);
                items.removeAll(items);
                items.add("Nom PTS M V N D BP BC CR CJ DIFF");
                for (Equipe e : championnat.getEquipes()) {
                    items.add(e.getNom() + " " +
                            e.getPoints() + " " +
                            e.getMatches() + " " +
                            e.getVictoires() + " " +
                            e.getNuls() + " " +
                            e.getDefaites() + " " +
                            e.getButsPour() + " " +
                            e.getButsContre() + " " +
                            e.getCartonsRouges() + " " +
                            e.getCartonsJaunes() + " " +
                            e.getDifferenceButs());
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
                listView.setAdapter(adapter);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                championnat.scoreFinal(0,1,2,2,0,0,0,0);
                items.removeAll(items);
                items.add("Nom PTS M V N D BP BC CR CJ DIFF");
                for (Equipe e : championnat.getEquipes()) {
                    items.add(e.getNom() + " " +
                            e.getPoints() + " " +
                            e.getMatches() + " " +
                            e.getVictoires() + " " +
                            e.getNuls() + " " +
                            e.getDefaites() + " " +
                            e.getButsPour() + " " +
                            e.getButsContre() + " " +
                            e.getCartonsRouges() + " " +
                            e.getCartonsJaunes() + " " +
                            e.getDifferenceButs());
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
                listView.setAdapter(adapter);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
