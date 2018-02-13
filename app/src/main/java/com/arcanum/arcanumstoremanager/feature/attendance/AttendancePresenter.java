package com.arcanum.arcanumstoremanager.feature.attendance;

import android.os.Environment;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;
import com.arcanum.arcanumstoremanager.utils.CsvWriter;
import com.arcanum.arcanumstoremanager.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.arcanum.arcanumstoremanager.utils.DateUtils.dateFormat;

/**
 * Created by norman on 29/01/18.
 */

public class AttendancePresenter extends BasePresenter<AttendanceContract.View> implements AttendanceContract.Presenter {

    @Inject
    GetVisitsUseCase getVisitsUseCase;

    @Inject
    AttendancePresenter(AttendanceContract.View mainView, GetVisitsUseCase useCase) {
        attachView(mainView);
        getVisitsUseCase = useCase;
    }

    @Override
    public void getAttendance() {
        getVisitsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    @Override
    public void writeToCsv(List<VisitWithName> visits) {
        File exportDir = prepareFolderExport();
        File file = new File(exportDir, "visits.csv");
        try {
            writeFile(file, visits);
            mView.showError("Success");
        } catch (Exception e) {
            mView.showError(e.getLocalizedMessage());
        }
    }

    private File prepareFolderExport() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        return exportDir;
    }

    private void writeFile(File file, List<VisitWithName> visits) throws IOException {
        file.createNewFile();
        CsvWriter csvWrite = new CsvWriter(new FileWriter(file));
        csvWrite.writeNext(new String[]{"username", "fullname", "visit"});
        for (VisitWithName visit : visits) {
            String arrStr[] = {visit.username, visit.fullname, DateUtils.DateInMillisToStringFormatted(visit.visittime, dateFormat)};
            csvWrite.writeNext(arrStr);
        }
        csvWrite.close();
    }

    private void onSuccess(List<VisitWithName> visits) {
        mView.updateAdapter(visits);
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private List<VisitWithName> insertHeader(List<VisitWithName> visits) {
        List<VisitWithName> toBeAdded = new ArrayList<>();
        List<VisitWithName> result = new ArrayList<>();
        result.addAll(visits);

        if(visits.size() != 0) {
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < visits.size(); i++) {
                Calendar visitCal = DateUtils.MillisToCalendar(0);
                if (visitCal.get(Calendar.DAY_OF_MONTH) != cal.get(Calendar.DAY_OF_MONTH)) {
                    VisitWithName header = new VisitWithName();
                    header.visittime = cal.getTimeInMillis();
                    header.username = String.valueOf(i);
                    header.fullname = "";
                    toBeAdded.add(header);
                    cal = visitCal;
                }
            }

            for (VisitWithName header : toBeAdded) {
                result.add(Integer.valueOf(header.username), header);
            }

        }

        return result;
    }
}
