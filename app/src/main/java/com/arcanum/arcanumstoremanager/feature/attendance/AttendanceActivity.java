package com.arcanum.arcanumstoremanager.feature.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;

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

    private List<VisitWithName> visits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_export:
                presenter.writeToCsv(visits);
                return true;
        }

        return super.onOptionsItemSelected(item);
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
        this.visits = visits;
        adapter = AttendanceAdapter_.getInstance_(this);
        adapter.initItems(visits);
        attendanceList.setAdapter(adapter);
    }
}
