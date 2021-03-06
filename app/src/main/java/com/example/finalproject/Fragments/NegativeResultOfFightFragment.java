package com.example.finalproject.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.finalproject.Item;
import com.example.finalproject.ItemAdapter;

import java.util.Objects;

public class NegativeResultOfFightFragment extends DialogFragment {

    private static final String AMoney = "mySettings";
    private SharedPreferences mMoneyPower;
    private static final String afterSFightMoneySaver = "counter";

    @SuppressLint("ApplySharedPref")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mMoneyPower = Objects.requireNonNull(this.getContext()).getSharedPreferences(AMoney, Context.MODE_PRIVATE);
        Item[] a = new Item[1];
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), a);
        TrainingFragment trainingExample = new TrainingFragment();
        int money = trainingExample.getMoney(Objects.requireNonNull(getContext()));
        final int Weapon = itemAdapter.getPowerAfterShopFromShop(Objects.requireNonNull(getContext()));
        builder.setMessage("Вы проиграли и потеряли " + Weapon * 10).setTitle("Поражение")
                .setPositiveButton("Выход", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        money -= Weapon*10;
        SharedPreferences.Editor money_editor = mMoneyPower.edit();
        money_editor.putInt(afterSFightMoneySaver, money);
        money_editor.commit();
        return builder.create();
    }

    int getMoneyAfterFight(Context ctx) {
        mMoneyPower = ctx.getSharedPreferences(AMoney, Context.MODE_PRIVATE);
        return mMoneyPower.getInt(afterSFightMoneySaver, 0);
    }
}
