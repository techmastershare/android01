package com.example.pizzalist;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ListCategory extends ListFragment {
	int mNum;
	private ListView mListItem_listview = null;
	private ListItemAdapter mAdapter = null;
	private ArrayList<CategoryItem> mListItem = null;

	/**
	 * Create a new instance of CountingFragment, providing "num" as an
	 * argument.
	 */
	static ListCategory newInstance(int num) {
		ListCategory f = new ListCategory();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.category_list, container, false);
		View tv = v.findViewById(R.id.text);
		
		mListItem_listview = (ListView) v.findViewById(R.id.listItem_listview);
		mListItem = new ArrayList<CategoryItem>();

		if (mNum == 0) {
			((TextView) tv).setText("Pizza Menu");
			mListItem.add(new CategoryItem("Supreme", "Pizza phủ sáu lớp - Thịt bò, Pepperoni, thịt heo, ớt chuông, nãm rơm và hành tây.", R.drawable.supreme1));
			mListItem.add(new CategoryItem("Supreme Meatlovers", "Pizza giàu protien với thịt muối, thịt bò, giăm bông, xúc xích và thịt heo.", R.drawable.meatlovers1));
			mListItem.add(new CategoryItem("Supreme Seafood Pesto", "Pizza hải sản sốt pesto và phô mai Ý.", R.drawable.supreme_seafood_pesto1));
			mListItem.add(new CategoryItem("Chicken Supreme", "Pizza ba loại thịt gà, ớt chuông, hành tây và nấm rơm.", R.drawable.chicken_supreme1));
			mListItem.add(new CategoryItem("Pepperoni Lover", "Pizza phủ 2 lớp xúc xích nướng.", R.drawable.peperoni_lover1));
			mListItem.add(new CategoryItem("Hawaiian Paradise", "Pizza phủ hai lớp giăm bông và dứa tươi", R.drawable.hawaiian_paradise1));
		}
		if (mNum == 1) {
			((TextView) tv).setText("Rice and Noodles");
			mListItem.add(new CategoryItem("Tuna Melt", "Tuna, roasted mushrooms and onions with pesto mayonaise topped with tomato slices, Mozzarella cheese in a baguette and grilled to perfection. Served with our famous crinkle cut fries.", R.drawable.tuna_melt1));
			mListItem.add(new CategoryItem("Spaghetti Bolognese with Meatballs", "Mì Ý thịt viên trộn sốt cà chua", R.drawable.spaghetti_bolonese1));
			mListItem.add(new CategoryItem("Fettucine Carbonara", "Mì sợi dẹp trộn thịt giăm bông và sốt kem sữa phủ phô mai.", R.drawable.fettucine_carbonara1));
			mListItem.add(new CategoryItem("Garlic Shrimps Ala Olio Spaghetti", "Mì Ý xào tôm tươi và đậu tây", R.drawable.garlic_shrimp_ala1));
			mListItem.add(new CategoryItem("Seafood Spaghetti Marinara", "Mì Ý hải sản trộn sốt cà chua.", R.drawable.seafood_spaghetti1));
		}
		if (mNum == 2) {
			((TextView) tv).setText("Soup and Salad");
			mListItem.add(new CategoryItem("Seafood Soup", "Súp hải sản phủ bánh nướng", R.drawable.seafood_soup1));
			mListItem.add(new CategoryItem("Minestrone", "Súp thịt và rau củ kiểu Ý", R.drawable.minestrone1));
			mListItem.add(new CategoryItem("Russian Salad", "Xà lách trộn kiểu Nga", R.drawable.russian_salad1));
			mListItem.add(new CategoryItem("Our House Salad", "Xà lách trộn sốt Thousand Island.", R.drawable.our_house_salad1));
			mListItem.add(new CategoryItem("Cream of Chicken and Corn Soup", "Súp kem sửa với thịt gà và hạt ngô (bắp).", R.drawable.cream_of_chicken_corn_soup1));
			mListItem.add(new CategoryItem("Caesar Chicken Salad", "Xà lách, ớt chuông, thịt gà trộn sốt Caesar và phô mai parmesan.", R.drawable.ceasar_chicken_salad1));
			mListItem.add(new CategoryItem("Sedfood Salad", "Xà lách hải sản trộn sốt Wasabi Caesar.", R.drawable.seafood_salad1));
		}
		mAdapter = new ListItemAdapter(v.getContext(), mListItem);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mListItem_listview.setAdapter(mAdapter);
	}
}
