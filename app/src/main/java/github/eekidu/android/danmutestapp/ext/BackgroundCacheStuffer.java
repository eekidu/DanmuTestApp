package github.eekidu.android.danmutestapp.ext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;


import github.eekidu.android.danmutestapp.R;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;

public class BackgroundCacheStuffer extends SpannedCacheStuffer {
    // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
    final Paint paint = new Paint();
    private Context mContext;
    private final Drawable mDrawable;

    public BackgroundCacheStuffer(Context context) {
        mContext = context;
        mDrawable = context.getResources().getDrawable(R.drawable.videoplayer_yc_bg_myself_danmu);
    }

    @Override
    public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
        super.measure(danmaku, paint, fromWorkerThread);

        if (!danmaku.isGuest && danmaku.firstShownFlag == -1) {
            danmaku.padding = 15;  // 在背景绘制模式下增加padding
        } else {
            danmaku.padding = 5;
        }
    }

    @Override
    public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
        if (!danmaku.isGuest && danmaku.firstShownFlag == -1) {
            Rect rect = new Rect((int) left + 2, (int) top + 2, (int) (left + danmaku.paintWidth - 2), (int) (top + danmaku.paintHeight - 2));
            mDrawable.setBounds(rect);
            mDrawable.draw(canvas);
        } else {
            super.drawBackground(danmaku, canvas, left, top);
        }

    }

    @Override
    public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
        // 禁用描边绘制
    }

}
