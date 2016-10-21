package com.pes.catrun;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ProfileActivityVH extends RecyclerView.ViewHolder {
		CardView cv;
		TextView textView1;
		TextView textView2;
		TextView textView3;
		TextView textView4;

		public ProfileActivityVH(View itemView) {
			super(itemView);
			cv = (CardView) itemView.findViewById(R.id.card_view);
			textView1 = (TextView) itemView.findViewById(R.id.card_view_title);
			textView2 = (TextView) itemView.findViewById(R.id.distancia);
			textView3 = (TextView) itemView.findViewById(R.id.data);
			textView4 = (TextView) itemView.findViewById(R.id.participants);


		}
	}