package com.arcanum.arcanumstoremanager.feature.attendance;

import android.content.Context;
import android.view.ViewGroup;

import com.arcanum.arcanumstoremanager.base.BaseRecyclerViewAdapter;
import com.arcanum.arcanumstoremanager.base.ViewWrapper;
import com.arcanum.arcanumstoremanager.data.VisitDao;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by norman on 30/01/18.
 */

@EBean
public class AttendanceAdapter extends BaseRecyclerViewAdapter<VisitWithName, AttendanceViewHolder> {

    @RootContext
    Context context;

    @Override
    protected AttendanceViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return AttendanceViewHolder_.build(context);
    }

    @Override
    protected void initItems(List<VisitWithName> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<AttendanceViewHolder> holder, int position) {
        AttendanceViewHolder view = holder.getView();
        VisitWithName visit = items.get(position);

        view.bindData(visit);
    }
}
