package com.habitissimo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BudgetAdapter extends BaseAdapter {

    private Context context;
    private List<Budget> budgets;

    public BudgetAdapter(Context context, List<Budget> budgets) {
        this.context = context;
        this.budgets = budgets;
    }

    @Override
    public int getCount() {
        return this.budgets.size();
    }

    @Override
    public Object getItem(int position) {
        return this.budgets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_budget, parent, false);
        }

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvCategory = rowView.findViewById(R.id.tvCategory);
        TextView tvPhone = rowView.findViewById(R.id.tvPhone);
        TextView tvEmail = rowView.findViewById(R.id.tvEmail);
        TextView tvLocation = rowView.findViewById(R.id.tvLocation);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);

        Budget budget = this.budgets.get(position);
        tvName.setText(budget.getName());
        tvCategory.setText(budget.getSubCategory());
        tvPhone.setText(budget.getPhone());
        tvLocation.setText(null);
        tvEmail.setText(budget.getEmail());
        tvDescription.setText(budget.getDescription());

        return rowView;
    }
}
