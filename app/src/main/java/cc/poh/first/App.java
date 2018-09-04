package cc.poh.first;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cc.poh.first.data.AppDb;

/**
 * Created by cpoh on 22/3/2018.
 */

public class App extends Application {

    private final Executor mDiskIO = Executors.newSingleThreadExecutor();
    private final Executor mNetworkIO = Executors.newFixedThreadPool(3);

    public AppDb getDb() {
        return AppDb.getInstance(this);
    }

    public PostRepository getPostRepository() {
        return PostRepository.getInstance(getDb(), mDiskIO);
    }
}
