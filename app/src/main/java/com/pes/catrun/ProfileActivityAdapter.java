package com.pes.catrun;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ProfileActivityAdapter extends RecyclerView.Adapter<ProfileActivityVH> {

	private final List<String> titles;

	public ProfileActivityAdapter(List<String> titles) {
		this.titles = titles;
	}

	@Override public ProfileActivityVH onCreateViewHolder(ViewGroup viewGroup, int i) {

		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.list_item_card, viewGroup, false);

		return new ProfileActivityVH(itemView);
	}

	@Override
	public void onBindViewHolder(ProfileActivityVH fakePageVH, int i) {
		// do nothing
		fakePageVH.textView.setText(titles.get(i).toString());
	}

	@Override public int getItemCount() {
		return titles.size();
	}
}

