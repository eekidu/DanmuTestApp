package github.eekidu.android.danmutestapp.ext;

import android.util.Log;

import github.eekidu.android.danmutestapp.model.DanmuUtil;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;

public class BaseCacheStufferProxy extends BaseCacheStuffer.Proxy {

    private IDanmakuView mIDanmakuView;

    public BaseCacheStufferProxy(IDanmakuView IDanmakuView) {
        mIDanmakuView = IDanmakuView;
    }

    @Override
    public void prepareDrawing(BaseDanmaku danmaku, boolean b) {
//        Log.d("Danmu", "prepareDrawing: " + DanmuUtil.toString(danmaku));
        if (!danmaku.isGuest) {
            if (danmaku.firstShownFlag == 0) {
                mIDanmakuView.invalidateDanmaku(danmaku, true);
            }
        }
    }

    @Override
    public void releaseResource(BaseDanmaku baseDanmaku) {

    }
}
