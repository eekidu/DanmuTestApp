package github.eekidu.android.danmutestapp.model;

import android.graphics.Color;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;

public class DanmuUtil {
    public static BaseDanmaku generateDanmu(DanmakuContext context) {
        BaseDanmaku danmaku = context.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null) {
            return null;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = "这是一条弹幕";
        danmaku.padding = 5;
        danmaku.priority = 1;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.textSize = 25;
        danmaku.isGuest = false;
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
//        danmaku.borderColor = Color.GREEN;
        return danmaku;
    }

    public static String toString(BaseDanmaku baseDanmaku) {
        String result = "index:" + baseDanmaku.index + ", showCount:" + baseDanmaku.firstShownFlag;
        return result;

    }
}
