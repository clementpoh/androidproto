package cc.poh.first.ui.home;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.annotation.Nullable;

import cc.poh.first.R;
import cc.poh.first.data.PostEntity;
import cc.poh.first.databinding.ItemPostBinding;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PostEntity}.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostEntity> mValues;

    @Nullable
    private final OnPostClick mOnPostClick;

    public PostAdapter(OnPostClick onPostClick) {
        mOnPostClick = onPostClick;
    }

    @Override
    @NonNull public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PostEntity post = mValues.get(position);
        holder.binding.setPost(post);
        holder.binding.setCall(mOnPostClick);

        holder.binding.image.setImageURI(post.photo);
        holder.binding.textTitle.setText(mValues.get(position).title);
        holder.binding.textBookmarked.setText(String.valueOf(position));
        holder.binding.textDays.setText("SMTWTFS");

        holder.binding.textPrice.setText(R.string.hint_price);

        String text = holder.binding.textPrice.getText().toString();
        holder.binding.textPrice.setText(String.format(text, position));


        holder.binding.textSeen.setText(String.valueOf(position));

        holder.binding.btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.ic_bookmark);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public void setValues(List<PostEntity> items) {
        mValues = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
