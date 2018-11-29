package com.example.zhuanpan;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.util.Random;

public class zhuanpan extends View implements View.OnClickListener {
    private Paint mPaint;
    private Paint strPaint;
    private int mWidth;
    private int mPadding;
    private RectF mRectF;
    private Context mcontext;
    private String mStr = "开始";
    private String[] contents = new String[]{"iPhoneX", "送李天玮", "送家电", "马尔代夫三日游", "羽泉演唱会门票", "翟萍萍写真照","30G流量","腾讯会员"};
    public int[] colors = new int[]{Color.parseColor("#8EE5EE"), Color.parseColor("#FFD700"), Color.parseColor("#FFD39B"), Color.parseColor("#FF8247"), Color.parseColor("#FF34B3"), Color.parseColor("#F0E68C"),Color.parseColor("#FFD39B"), Color.parseColor("#FF8247")};
    private int start;
    private boolean first = true;

    public zhuanpan(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mcontext=context;
        setOnClickListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置线条颜色
        mPaint.setColor(Color.BLUE);
        //设置字体大小
        mPaint.setTextSize(30);
        //设置空心
        mPaint.setStyle(Paint.Style.STROKE);
        //设置局齿轮
        mPaint.setAntiAlias(true);
        //设置空心线条的粗细
        mPaint.setStrokeWidth(4);
        //设置圆
        canvas.drawCircle(mWidth/2,mWidth/2,300,mPaint);


        //画扇形
        RectF rectF=new RectF(0,0,mWidth,mWidth);
        //设置实心
        mPaint.setStyle(Paint.Style.FILL);
        //填充颜色
        for(int i=0;i<colors.length;i++)
        {
            mPaint.setColor(colors[i]);
            int jd=i*45;
            canvas.drawArc(rectF,jd,45,true,mPaint);
        }


        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(35);
        for(int i=0;i<contents.length;i++)
        {
            int jd=i*45;
            Path path=new Path();
            path.addArc(rectF,jd,45);
            canvas.drawTextOnPath(contents[i],path,60,60,mPaint);
        }

      /*  canvas.drawLine(0,200,300,mWidth/2,mPaint);*/




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(600,600);
        mWidth=getMeasuredWidth();
    }

    @Override
    public void onClick(View v) {
        Random random = new Random();
        int i = random.nextInt(3600);
        int j = random.nextInt(6666);

        RotateAnimation rotate = null;
        if (first){

            rotate=new RotateAnimation(0,i,mWidth/2,mWidth/2);
            start = i;
            first = false;
        }else {
            rotate=new RotateAnimation(start,i,mWidth/2,mWidth/2);
            int i1 = i % 360;
            start = i1;
        }


        //保留最后的位置 不回到原位
        rotate.setFillAfter(true);
        rotate.setDuration(j);
        rotate.setRepeatMode(0);
        rotate.setInterpolator(new LinearInterpolator());
        startAnimation(rotate);


    }
}
