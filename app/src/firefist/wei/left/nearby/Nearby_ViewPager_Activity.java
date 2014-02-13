package firefist.wei.left.nearby;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import firefist.wei.left.CustomViewPager;
import firefist.wei.left.MyFragmentPagerAdapter;
import firefist.wei.main.R;

public class Nearby_ViewPager_Activity extends FragmentActivity{

	private ArrayList<Fragment> fragmentsList;
	private CustomViewPager viewPager;
	private ImageView imageView;
	
	private Bitmap cursor;
	private int offset;
	private int currentItem;
	private Matrix matrix = new Matrix();
	private int bmWidth;
	private Animation animation;
	private TextView tv1, tv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.left_nearby_main);
		
		imageView = (ImageView) findViewById(R.id.nearby_cursor);
		tv1 = (TextView) findViewById(R.id.nearby_tv1);
		tv2 = (TextView) findViewById(R.id.nearby_tv2);
		
		initCursor();
		
		fragmentsList = new ArrayList<Fragment>();
		
	    fragmentsList.add(Fragment.instantiate(this,
	    		NearBy_Left.class.getName()));
	    fragmentsList.add(Fragment.instantiate(this,
	    		NearBy_Right.class.getName()));
	    
	    viewPager = (CustomViewPager) findViewById(R.id.nearby_viewpager);
	    viewPager.setAdapter(new MyFragmentPagerAdapter(this
				.getSupportFragmentManager(), fragmentsList));
	    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				int one = offset * 2 + bmWidth;// ҳ��1 -> ҳ��2 ƫ����
				// int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����

				animation = new TranslateAnimation(one * currentItem, one
						* arg0, 0, 0);// ��Ȼ����Ƚϼ�ֻ࣬��һ�д��롣
				currentItem = arg0;
				animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
				animation.setDuration(300);
				imageView.startAnimation(animation);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
			});
	    tv1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
				tv1.setBackgroundColor(Color.argb(256, 128, 0, 128));
				tv2.setBackgroundColor(Color.argb(0, 128, 0, 128));
			}
		});

		tv2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
				tv2.setBackgroundColor(Color.argb(256, 128, 0, 128));
				tv1.setBackgroundColor(Color.argb(0, 128, 0, 128));
			}
		});
	    
	}

	private void initCursor() {
		cursor = BitmapFactory
				.decodeResource(getResources(), R.drawable.cursor);
		bmWidth = cursor.getWidth();

		DisplayMetrics dm;
		dm = getResources().getDisplayMetrics();

		offset = (dm.widthPixels - 2 * bmWidth) / 4;
		matrix.setTranslate(offset, 0);
		imageView.setImageMatrix(matrix);
		currentItem = 1;
	}
	
	public void left_nearby_back(View v){
		this.finish();
	}
}
