package com.arcanum.arcanumstoremanager.feature.userslist;

import android.os.Environment;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.data.VisitDao;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetAllUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.utils.CsvWriter;
import com.arcanum.arcanumstoremanager.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.arcanum.arcanumstoremanager.utils.DateUtils.dateFormat;

/**
 * Created by norman on 31/01/18.
 */

public class AccountsPresenter extends BasePresenter<AccountsContract.View> implements AccountsContract.Presenter {

    @Inject
    GetAllUserUseCase getAllUserUseCase;

    @Inject
    public AccountsPresenter(AccountsContract.View mainView, GetAllUserUseCase userUseCase) {
        attachView(mainView);
        getAllUserUseCase = userUseCase;
    }

    @Override
    public void loadData() {
        getAllUserUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    @Override
    public void writeToCsv(List<User> users) {
        File exportDir = prepareFolderExport();
        File file = new File(exportDir, "users.csv");
        try {
            writeFile(file, users);
            mView.showMessage("Success");
        } catch (Exception e) {
            mView.showMessage(e.getLocalizedMessage());
        }
    }

    private void onFailed(Throwable throwable) {
        mView.showMessage(throwable.getLocalizedMessage());
    }

    private void onSuccess(List<User> users) {
        mView.updateAdapter(users);
    }

    private File prepareFolderExport() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        return exportDir;
    }

    private void writeFile(File file, List<User> users) throws IOException {
        file.createNewFile();
        CsvWriter csvWrite = new CsvWriter(new FileWriter(file));
        csvWrite.writeNext(new String[]{"username", "fullname", "email", "password", "pass type", "dob", "phone"});
        for (User user : users) {
            String arrStr[] = {user.getUsername(), user.getFullname(), user.getEmail(), user.getPassword(), user.getPassType(), user.getDob(), user.getPhone()};
            csvWrite.writeNext(arrStr);
        }
        csvWrite.close();
    }
}
