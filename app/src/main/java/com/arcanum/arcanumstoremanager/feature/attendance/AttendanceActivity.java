package com.arcanum.arcanumstoremanager.feature.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.data.VisitDao;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_attendance)
public class AttendanceActivity extends DaggerAppCompatActivity implements AttendanceContract.View {

    @ViewById(R.id.attendance_list)
    RecyclerView attendanceList;

    @Inject
    AttendanceContract.Presenter presenter;

    private AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterViews() {
        attendanceList.setLayoutManager(new LinearLayoutManager(this));
        presenter.getAttendance();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAdapter(List<VisitWithName> visits) {
        adapter = AttendanceAdapter_.getInstance_(this);
        adapter.initItems(visits);
        attendanceList.setAdapter(adapter);
    }
}
