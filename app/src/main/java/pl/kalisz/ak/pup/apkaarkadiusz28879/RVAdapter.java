package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private String[] nazwa_;
    private String[] opis_;
    private int[] fotoId_;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public RVAdapter(String[] nazwa_, String[] opis_, int[] fotoId_){
        this.nazwa_ = nazwa_;
        this.opis_ = opis_;
        this.fotoId_ = fotoId_;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_obiekty_uczelni_cardview, viewGroup, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int i) {
        CardView cardView = holder.cardView;
        TextView obiektNazwa = (TextView) cardView.findViewById(R.id.nazwa);
        obiektNazwa.setText(nazwa_[i]);
        TextView obiektOpis = (TextView) cardView.findViewById(R.id.opis);
        obiektOpis.setText(opis_[i]);
        ImageView obiektFoto = (ImageView) cardView.findViewById(R.id.foto);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), fotoId_[i]);
        obiektFoto.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return nazwa_.length;
    }
}
