package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ObiektyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ObiektyInfoFragment extends Fragment {
    private long obiektId;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String EXTRA_OBIEKT_ID = "ID";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ObiektyInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ObiektyInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ObiektyInfoFragment newInstance(String param1, String param2) {
        ObiektyInfoFragment fragment = new ObiektyInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           obiektId = savedInstanceState.getLong("obiektId");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putLong("obiektId", obiektId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obiekty_info, container, false);
    }

    public void setObiekt(long id){
        this.obiektId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView obiektNazwa = (TextView)view.findViewById(R.id.nazwa);
            Obiekt obiekt = Obiekt.obiekty[(int) obiektId];
            obiektNazwa.setText(obiekt.getNazwa());
            TextView obiektOpis = (TextView)view.findViewById(R.id.opis);
            obiektOpis.setText(obiekt.getOpis());
            ImageView obiektFoto = (ImageView)view.findViewById(R.id.foto);
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), obiekt.getFotoId());
            obiektFoto.setImageDrawable(drawable);
        }
    }
}