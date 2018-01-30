package com.arcanum.arcanumstoremanager.feature.attendance;

import android.content.Context;
import android.view.ViewGroup;

import com.arcanum.arcanumstoremanager.base.BaseRecyclerViewAdapter;
import com.arcanum.arcanumstoremanager.base.ViewWrapper;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by norman on 30/01/18.
 */

@EBean
public class AttendanceAdapter extends BaseRecyclerViewAdapter<Visit, AttendanceViewHolder> {

    @RootContext
    Context context;

    @Override
    protected AttendanceViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return AttendanceViewHolder_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<AttendanceViewHolder> holder, int position) {
        AttendanceViewHolder view = holder.getView();
        Visit visit = items.get(position);

        view.bindData(visit);
    }
}
