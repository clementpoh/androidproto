package cc.poh.first.ui.scaffold;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.poh.first.R;
import cc.poh.first.data.DummyContent.DummyItem;
import cc.poh.first.databinding.ItemLinearBinding;

/**
 * Created by cpoh on 4/3/2018.
 */

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public LinearAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_linear, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.binding.textTitle.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemLinearBinding binding;
        DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
