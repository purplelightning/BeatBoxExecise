# BeatBoxExecise
pressed状态，按钮的放大缩小动画，背景设置，边框，渐变，

按钮选中与否的样式设置用selector直接设置：

<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true">
        <shape android:shape="oval">
	//背景填充色
            <solid android:color="@color/red"/>
	//边框色
            <stroke android:width="4dp" android:color="#ffffff"/>
	//渐变，角度
            <gradient android:startColor="#ff0000"　android:endColor="#0fff00" android:angle="135"/>
        </shape>
    </item>

    <item android:state_pressed="false">
        <shape android:shape="oval">
            <solid android:color="@color/dark_blue"/>
        </shape>
    </item>
</selector>

按钮点击放大，缩小

OnTouchListener是用来监听手机屏幕事件的监听，用来处理按下，抬起，滑动等动作，具体的有3种情况：UP抬起 DOWN按下 MOVE滑动
１．在Activity中，实现OnTouchListener 接口，重写onTouch方法，为需要的控件setOnTouchListener,button.setOnTouchListener(this),重写的onTouch方法return false，这样不会消化ClickListener的事件

@Override
public boolean onTouch(View v, MotionEvent event) {
    //按下操作
    switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
            Animation animation１＝AnimationUtils.loadAnimation(getContext(),R.anim.scale_to_big);
            v.startAnimation(animation1);
            break;

２．自定义动画效果:
在anim目录下新建xml, fillAfter=true,保持最后的状态
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:fillAfter="true">
    <scale
        android:fillAfter="true"
        android:fromXScale="1"
        android:fromYScale="1"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="1.2"
        android:toYScale="1.2"
        />
</set>
3. 所有的根布局clipToChildren=false，防止父布局遮挡按钮的放大
