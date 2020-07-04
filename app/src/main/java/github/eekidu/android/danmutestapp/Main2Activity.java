package github.eekidu.android.danmutestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.HashMap;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.ui.widget.DanmakuView;

public class Main2Activity extends AppCompatActivity {
    DanmakuView mDanmakuView;
    private DanmakuContext mContext;
    private BaseDanmakuParser mParse;
    private String TAG = "Main2Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mDanmakuView = findViewById(R.id.danmu);
        initDanmakuView(mDanmakuView);

        mContext = DanmakuContext.create();
        iniDanmakuContext(mContext);

        mParse = createParase();

        mDanmakuView.prepare(mParse, mContext);
    }


    private void initDanmakuView(DanmakuView danmakuView) {
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            private long lastTime;

            @Override
            public void prepared() {
                mDanmakuView.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {
                if (timer.currMillisecond < lastTime) {
                    long currMillisecond = timer.currMillisecond;
                    Log.i(TAG, "updateTimer: " + currMillisecond);
                }
                lastTime = timer.currMillisecond;
            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {
                LogUtil.Builder builder = new LogUtil.Builder();
                builder.put("danmaku.getActualTime()", danmaku.getActualTime());
                builder.put("danmaku.getTime()", danmaku.getTime());
                Log.d(TAG, "danmakuShown: " + builder.toString());

            }

            @Override
            public void drawingFinished() {

            }
        });


        mDanmakuView.showFPS(true);
        mDanmakuView.enableDanmakuDrawingCache(false);
    }


    private BaseDanmakuParser createParase() {
//        BaseDanmakuParser parser = new BaseDanmakuParser() {
//            @Override
//            protected Danmakus parse() {
//                return new Danmakus();
//            }
//        };


        InputStream stream = this.getResources().openRawResource(R.raw.comments);
        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);

        return parser;
    }

    private void iniDanmakuContext(DanmakuContext context) {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 20); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(1.2f)
//                .setCacheStuffer(new MainActivity.BackgroundCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
//                .preventOverlapping(overlappingEnablePair);
                .preventOverlapping(overlappingEnablePair)
                .setDanmakuMargin(40);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mDanmakuView.getConfig().setDanmakuMargin(0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mDanmakuView.getConfig().setDanmakuMargin(40);
        }
    }


    public void onClickMain2(View view) {
        int id = view.getId();
        if (id == R.id.bt01) {
            mDanmakuView.seekTo(2000L);
        }
    }
}
