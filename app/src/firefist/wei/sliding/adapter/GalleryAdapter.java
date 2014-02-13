package firefist.wei.sliding.adapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.FailReason;

import firefist.wei.main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

	DisplayImageOptions options;

	public ImageLoader imageLoader = ImageLoader.getInstance();

	Context mContext;
	// int[] res = new int[] { R.drawable.t1, R.drawable.t2,
	// R.drawable.t3, R.drawable.t1, R.drawable.t2,
	// R.drawable.t3, R.drawable.t1, R.drawable.t2,
	// R.drawable.t3 };

	int[] res = null;
	String[] urls = null;

	public GalleryAdapter(Context context, String[] urls) {
		this.mContext = context;
		this.urls = urls;
	}

	public GalleryAdapter(Context cnt) {
		this.mContext = cnt;
		this.res = new int[] { R.drawable.recommend_week_1, R.drawable.recommend_week_2,
				R.drawable.recommend_week_3,R.drawable.recommend_week_4,
				R.drawable.recommend_week_5 };
	}

	@Override
	public int getCount() {

		if (urls != null) {
			return urls.length;
		} else {
			return res.length;
		}
	}

	@Override
	public Object getItem(int position) {

		if (urls != null) {
			return urls[position];
		} else {
			return res[position];
		}

	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.gallery_item, null);
		}
		ImageView img = (ImageView) convertView.findViewById(R.id.home_img);

		if (urls != null) {

			imageLoader.displayImage(urls[position], img, options,
					new SimpleImageLoadingListener() {

						@Override
						public void onLoadingStarted(String imageUri, View view) {

						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							String message = null;
							switch (failReason.getType()) {
							case IO_ERROR:
								message = "Input/Output error";
								break;
							case DECODING_ERROR:
								message = "Image can't be decoded";
								break;
							case NETWORK_DENIED:
								message = "Downloads are denied";
								break;
							case OUT_OF_MEMORY:
								message = "Out Of Memory error";
								break;
							case UNKNOWN:
								message = "Unknown error";
								break;
							}

						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {

						}
					});

		} else {
			img.setImageResource(res[position]);
		}

		return convertView;
	}

}
