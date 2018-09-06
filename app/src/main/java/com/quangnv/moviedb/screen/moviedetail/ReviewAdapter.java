package com.quangnv.moviedb.screen.moviedetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Review;
import com.quangnv.moviedb.databinding.ItemReviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 06/09/2018
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ItemViewHolder> {

    private LayoutInflater mInflater;
    private List<Review> mReviews;

    public ReviewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mReviews = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_review, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews != null ? mReviews.size() : 0;
    }

    public void setReviews(List<Review> reviews) {
        mReviews.clear();
        mReviews.addAll(reviews);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemReviewBinding mBinding;
        private ItemReviewViewModel mViewModel;

        public ItemViewHolder(ItemReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemReviewViewModel();
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Review review) {
            mViewModel.setReview(review);
            mBinding.executePendingBindings();
        }
    }
}
