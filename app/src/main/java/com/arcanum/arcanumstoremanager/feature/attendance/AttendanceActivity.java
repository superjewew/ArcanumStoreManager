package com.arcanum.arcanumstoremanager.feature.attendance;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
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
                AttendanceActivityPermissionsDispatcher.onExportClickedWithPermissionCheck(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onExportClicked() {
        presenter.writeToCsv(visits);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AttendanceActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
