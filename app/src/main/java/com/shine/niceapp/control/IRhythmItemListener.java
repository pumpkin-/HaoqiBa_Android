package com.shine.niceapp.control;

public abstract interface IRhythmItemListener {
    public abstract void onRhythmItemChanged(int paramInt);

    public abstract void onSelected(int paramInt);

    public abstract void onStartSwipe();
}