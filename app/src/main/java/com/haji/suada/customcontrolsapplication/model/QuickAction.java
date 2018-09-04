package com.haji.suada.customcontrolsapplication.model;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class QuickAction {
    String name;
    Drawable drawable;
    int count, quickActionIcon;
    boolean isSelected;

    public QuickAction() {
    }


    public QuickAction(String name, Drawable drawable, boolean isSelected) {
        this.name = name;
        this.drawable = drawable;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuickActionIcon() {
        return quickActionIcon;
    }

    public void setQuickActionIcon(int quickActionIcon) {
        this.quickActionIcon = quickActionIcon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @BindingAdapter({"app:imageBinding"})
    public static void bindImage(ImageView imageView, Drawable placeholder) {
        imageView.setImageDrawable(placeholder);
    }
}
