package com.arcanum.arcanumstoremanager.feature.attendance;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import static com.arcanum.arcanumstoremanager.utils.DateUtils.DateInMillisToStringFormatted;

/**
 * Created by norman on 30/01/18.
 */

@EViewGroup(R.layout.rowview_attendance)
public class AttendanceViewHolder extends ConstraintLayout {

    @ViewById(R.id.name)
    TextView nameTv;

    @ViewById(R.id.fullname)
    TextView fullnameTv;

    @ViewById(R.id.time)
    TextView timeTv;

    public AttendanceViewHolder(Context context) {
        super(context);
    }

    public void bindData(VisitWithName visit) {
        nameTv.setText(visit.username);
        fullnameTv.setText(visit.fullname);
        timeTv.setText(DateInMillisToStringFormatted(visit.visittime));
    }
}
