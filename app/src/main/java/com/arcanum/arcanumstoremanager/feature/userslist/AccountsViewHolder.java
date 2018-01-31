package com.arcanum.arcanumstoremanager.feature.userslist;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by norman on 31/01/18.
 */

@EViewGroup(R.layout.rowview_account)
public class AccountsViewHolder extends RelativeLayout {

    @ViewById(R.id.username)
    TextView usernameTv;

    @ViewById(R.id.fullname)
    TextView fullnameTv;

    public AccountsViewHolder(Context context) {
        super(context);
    }

    public void bindData(User user) {
        usernameTv.setText(user.getUsername());
        fullnameTv.setText(user.getFullname());
    }
}
