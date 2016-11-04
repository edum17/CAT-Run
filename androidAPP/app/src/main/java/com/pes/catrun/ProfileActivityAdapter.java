package com.pes.catrun;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ProfileActivityAdapter extends RecyclerView.Adapter<ProfileActivityVH> {

	private final List<Cursa> cursas;

	public ProfileActivityAdapter(List<Cursa> cursas) {
		this.cursas = cursas;
	}

	@Override public ProfileActivityVH onCreateViewHolder(ViewGroup viewGroup, int i) {

		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.list_item_card, viewGroup, false);

		return new ProfileActivityVH(itemView);
	}

	@Override
	public void onBindViewHolder(ProfileActivityVH fakePageVH, int i) {
		// do nothing
		fakePageVH.textView1.setText(cursas.get(i).nom.toString());
		fakePageVH.textView2.setText("A 10km de la teva posició.");
		fakePageVH.textView3.setText(cursas.get(i).data.toString());
		fakePageVH.textView4.setText("Hi participen 185 usuaris.");
	}

	@Override public int getItemCount() {
		return cursas.size();
	}
}

