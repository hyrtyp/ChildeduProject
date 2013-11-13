package com.hyrt.datahelper;

import android.app.Application;
import android.util.Log;

import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.binary.InFileBitmapObjectPersister;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.memory.LruCacheBitmapObjectPersister;

import roboguice.util.temp.Ln;

/**
 * A Flick image service that uses a lru cache persister back by a bitmap object persister.
 * @author David Stemmer
 */
public class FlickrSpiceService extends XmlSpringAndroidSpiceService {

    @Override
    public void onCreate() {
        super.onCreate();

        // Logging really causes the app to chug with this many requests
        Ln.getConfig().setLoggingLevel(Log.ERROR);
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();

        InFileBitmapObjectPersister filePersister = new InFileBitmapObjectPersister(getApplication());
        LruCacheBitmapObjectPersister memoryPersister = new LruCacheBitmapObjectPersister(filePersister,(int)(Runtime.getRuntime().maxMemory() / 4));

        manager.addPersister(memoryPersister);

        return manager;

    }

    @Override
    public int getThreadCount() {
        return 5;
    }
}
