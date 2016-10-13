package com.pes.catrun;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ProfileActivityVH extends RecyclerView.ViewHolder {
		CardView cv;
		TextView textView;

		public ProfileActivityVH(View itemView) {
			super(itemView);
			cv = (CardView) itemView.findViewById(R.id.card_view);
			textView = (TextView) itemView.findViewById(R.id.card_view_title);
		}
	}