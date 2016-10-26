package com.ixvil.boxbonus.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ixvil.boxbonus.Models.User;
import com.ixvil.boxbonus.R;
import com.ixvil.boxbonus.Models.Wallet;


public class HomeFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View homeView = this.fillFragment(inflater, container, savedInstanceState);

        // TODO: 24.10.2016 Work around Instance restore
        return homeView;
    }

    public View fillFragment(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView walletView = (TextView) homeView.findViewById(R.id.walletID);
        walletView.setText(Wallet.getWalletId().toString());

        TextView bonusesView = (TextView) homeView.findViewById(R.id.card_text);
        bonusesView.setText(getString(R.string.you_have) + User.staticUser.customer.balance + getString(R.string.bonuses_string));

        ImageView imageView = (ImageView) homeView.findViewById(R.id.card_image);
        int pic_height = (int) getResources().getDimension(R.dimen.pic_height);
        imageView.setImageBitmap(Wallet.getQr(pic_height, pic_height));
        return homeView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
