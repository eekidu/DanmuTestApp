package github.eekidu.android.danmutestapp.model;

public class DanmuBean {
    //   // <d p="23.826000213623,1,25,16777215,1422201084,0,057075e9,757076900">我从未见过如此厚颜无耻之猴</d>
    //                // 0:时间(弹幕出现时间)
    //                // 1:类型(1从右至左滚动弹幕|6从左至右滚动弹幕|5顶端固定弹幕|4底端固定弹幕|7高级弹幕|8脚本弹幕)
    //                // 2:字号
    //                // 3:颜色
    //                // 4:时间戳 ?
    //                // 5:弹幕池id
    //                // 6:用户hash
    //                // 7:弹幕id
    private float time;
    private int type;
    private int textSize;
    private int color;
    private long timeStamp;
    private int poolId;
    private String userId;
    private long id;


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
