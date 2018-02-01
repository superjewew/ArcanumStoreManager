package com.arcanum.arcanumstoremanager.feature.userslist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.arcanum.arcanumstoremanager.base.BaseRecyclerViewAdapter;
import com.arcanum.arcanumstoremanager.base.ViewWrapper;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by norman on 31/01/18.
 */

@EBean
public class AccountsAdapter extends BaseRecyclerViewAdapter<User, AccountsViewHolder> {

    @RootContext
    Context context;

    View.OnClickListener listener;

    @Override
    protected AccountsViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return AccountsViewHolder_.build(context);
    }

    @Override
    protected void initItems(List<User> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<AccountsViewHolder> holder, int position) {
        AccountsViewHolder view = holder.getView();
        User user = items.get(position);

        setOnClickListener(view, user);

        view.bindData(user);
    }

    private void setOnClickListener(AccountsViewHolder view, User user) {
        view.setTag(user);
        view.setOnClickListener(listener);
    }
}
