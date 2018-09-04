package com.haji.suada.customcontrolsapplication;

import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.haji.suada.customcontrolsapplication.databinding.ActivityMainBinding;
import com.haji.suada.customcontrolsapplication.model.QuickAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuickActionAdapter.ItemSelectedListener {
    private QuickActionAdapter adapter;
    private QuickActionAdapter selectedAdapter;

    private List<QuickAction> quickActions;
    private List<QuickAction> selectedQuickActions;
    private List<QuickAction> unselectedQuickActions;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main );
        quickActions = new ArrayList<>();
        selectedQuickActions = new ArrayList<>();
        unselectedQuickActions = new ArrayList<>();

        getselectedQuickActions();

        adapter = new QuickActionAdapter();
        selectedAdapter = new QuickActionAdapter();

        adapter.setQuickActions(unselectedQuickActions);
        adapter.setOnItemClickListener(this);
        selectedAdapter.setQuickActions(selectedQuickActions);
        selectedAdapter.setOnItemClickListener(this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectedAdapter);

        RecyclerView selectedRecyclerView = binding.recyclerViewMore;
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectedRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(View view, QuickAction quickAction) {
        if (quickAction.isSelected()) {
            if (selectedQuickActions.size() > 1) {
                quickAction.setSelected(false);
                selectedQuickActions.remove(quickAction);
                selectedAdapter.setQuickActions(selectedQuickActions);
                unselectedQuickActions.add(quickAction);
                Collections.sort(unselectedQuickActions, new Comparator<QuickAction>() {
                    @Override
                    public int compare(QuickAction quickAction1, QuickAction quickAction2) {
                        String s1 = quickAction1.getName();
                        String s2 = quickAction2.getName();
                        return s1.compareToIgnoreCase(s2);
                    }

                });
                adapter.setQuickActions(unselectedQuickActions);
            } else {
                Toast.makeText(this, "Atleast one shortcut must be selected", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (selectedQuickActions.size() < 5) {
                quickAction.setSelected(true);
                unselectedQuickActions.remove(quickAction);
                Collections.sort(unselectedQuickActions, new Comparator<QuickAction>() {
                    @Override
                    public int compare(QuickAction quickAction1, QuickAction quickAction2) {
                        String s1 = quickAction1.getName();
                        String s2 = quickAction2.getName();
                        return s1.compareToIgnoreCase(s2);
                    }

                });
                adapter.setQuickActions(unselectedQuickActions);
                selectedQuickActions.add(quickAction);
                selectedAdapter.setQuickActions(selectedQuickActions);

            } else {
                Toast.makeText(this, "Only five shortcuts can be selected", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private List<QuickAction> getQuickActions() {
        quickActions.add(new QuickAction("Calculator", ContextCompat.getDrawable(this, R.drawable.ic_avator), true));
        quickActions.add(new QuickAction("Low Power Mode", ContextCompat.getDrawable(this, R.drawable.ic_avator), true));
        quickActions.add(new QuickAction("Do Not Disturb While Driving", ContextCompat.getDrawable(this, R.drawable.ic_avator), true));
        quickActions.add(new QuickAction("Accessibility shortcuts", ContextCompat.getDrawable(this, R.drawable.ic_avator), true));
        quickActions.add(new QuickAction("Alarm", ContextCompat.getDrawable(this, R.drawable.ic_avator), false));
        quickActions.add(new QuickAction("Camera", ContextCompat.getDrawable(this, R.drawable.ic_avator), false));
        quickActions.add(new QuickAction("Guided Access", ContextCompat.getDrawable(this, R.drawable.ic_avator), false));


        return quickActions;
    }

    private void getselectedQuickActions() {
        quickActions = getQuickActions();
        Log.d(TAG, "Size: " + quickActions.size());
        for (QuickAction quickAction : quickActions) {
            if (quickAction.isSelected()) {
                selectedQuickActions.add(quickAction);
            } else {
                unselectedQuickActions.add(quickAction);
            }
        }
    }


}
