package com.stone.tumblr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 此为RecyclerView的分割线
 * Created by xmuSistone
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int dividerColor;
    private int dividerHeight; // 分割线的高度
    private int dividerMargin; // 分割线到左右两侧的margin距离
    private Paint mPaint; // 画笔

    /**
     * 构造方法传入布局方向，不可不传
     *
     * @param dividerHeight 列表之间的间距，如果传入负值 默认为1像素
     */
    public DividerItemDecoration(Context context, int dividerHeight) {
        if (dividerHeight < 0) {
            this.dividerHeight = 1;
        } else {
            this.dividerHeight = dividerHeight;
        }

        dividerMargin = 0;
        dividerColor = Color.parseColor("#f0f0f0");

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        drawVertical(canvas, parent);
    }

    /**
     * 绘制纵向 item 分割线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = dividerMargin;
        final int right = parent.getMeasuredWidth() - dividerMargin;
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + dividerHeight;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, dividerHeight);
    }
}