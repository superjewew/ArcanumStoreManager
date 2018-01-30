package com.arcanum.arcanumstoremanager.feature.attendance;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by norman on 30/01/18.
 */

@EViewGroup(R.layout.rowview_attendance)
public class AttendanceViewHolder extends ConstraintLayout {

    @ViewById(R.id.name)
    TextView nameTv;

    @ViewById(R.id.time)
    TextView timeTv;

    public AttendanceViewHolder(Context context) {
        super(context);
    }

    public void bindData(Visit visit) {
        nameTv.setText(visit.getVisitor());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        timeTv.setText(timeFormat.format(visit.getVisitTime()));
    }
}
