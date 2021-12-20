package com.arun.porky.assignment.clock.global;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentOpener extends ContextWrapper {

    private Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int container;

    public FragmentOpener(Context base) {
        super(base);
        this.context = base;
    }

    public FragmentOpener setManager(FragmentManager fm){
        this.fragmentManager = fm;
        return this;
    }

    public FragmentOpener setContainer(int container){
        this.container = container;
        return this;
    }

    public void open(Fragment fragment){
        String backStack = fragment.getClass().getName().toUpperCase();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container,fragment);
        fragmentTransaction.addToBackStack(backStack);
        fragmentTransaction.commit();
    }

    public void openReplace(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

    public void openReplace(Fragment fragment, Bundle bundle){
        fragment.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

    public void openReplaceBackstack(Fragment fragment){
        String backStack = fragment.getClass().getName().toUpperCase();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container,fragment);
        fragmentTransaction.addToBackStack(backStack);
        fragmentTransaction.commit();
    }

    public void openReplaceBackstack(Fragment fragment, Bundle bundle){
        String backStack = fragment.getClass().getName().toUpperCase();
        fragment.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container,fragment);
        fragmentTransaction.addToBackStack(backStack);
        fragmentTransaction.commit();
    }

    public void removeFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        fragmentManager.popBackStack();
    }

}