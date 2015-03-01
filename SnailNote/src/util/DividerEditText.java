package util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/** ��֮���зָ��ߵ�edittext */

public class DividerEditText extends EditText {

	private Rect mRect;
	private Paint mPaint;

	private static final int mLineColor = Color.RED;
	private static final int mLineHeight = 3;

	public DividerEditText(Context context) {
		super(context);
		init();
	}

	public DividerEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DividerEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mRect = new Rect();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(mLineColor);
		mPaint.setStrokeWidth(mLineHeight);
	}

	protected void onDraw(android.graphics.Canvas canvas) {
		// ����ϵͳ���ƺ������Ľ���
		super.onDraw(canvas);

		// ��ȡ������λ����Ϣ
		int count = getLineCount();
		
		Rect r = mRect;
		Paint paint = mPaint;

		// ѭ����ÿ���е��������һ������
		
		for (int i = 0; i < count ; i++) {
			int baseline = getLineBounds(i, r);
			canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
		}
		
	};
}