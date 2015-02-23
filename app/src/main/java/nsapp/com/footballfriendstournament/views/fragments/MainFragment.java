package nsapp.com.footballfriendstournament.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nsapp.com.footballfriendstournament.R;

public class MainFragment extends AbstractFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        onSetupModel(view);

        return view;
    }

    @Override
    protected void onSetupModel(View inflatedView) {
        super.onSetupModel(inflatedView);
        setTitle(getString(R.string.menu));
        onSetupView(inflatedView);
    }

    @Override
    protected void onSetupView(View inflatedView) {
        Button button1 = (Button) inflatedView.findViewById(R.id.leagueButton);
        Button button2 = (Button) inflatedView.findViewById(R.id.cupButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.prepareOnReplaceTransaction(PlayersFragment.newInstance(LEAGUE));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.prepareOnReplaceTransaction(PlayersFragment.newInstance(CUP));
            }
        });
    }

    @Override
    protected void onSetupListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
