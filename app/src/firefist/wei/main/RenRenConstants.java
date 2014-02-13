package firefist.wei.main;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.friends.FriendsGetRequestParam;
import com.renren.api.connect.android.friends.FriendsGetResponseBean;
import com.renren.api.connect.android.users.UsersGetInfoRequestParam;
import com.renren.api.connect.android.users.UsersGetInfoResponseBean;

public final class RenRenConstants {

	public static long renren_uid = 0;
	public static String renren_name = null;
	public static int renren_sex = -1;
	public static String renren_headurl = null;
	public static String renren_tinyurl = null;

	private ProgressDialog progressDialog;

	/**
	 * 调用SDK接口的Renren对象
	 */
	protected static Renren renren;

	public void initRenRen() {
		Intent intent = MainActivity.instance.getIntent();
		renren = intent.getParcelableExtra(Renren.RENREN_LABEL);
		if (renren != null) {
			renren.init(MainActivity.instance);
		}
	}

	public void RenRenUsersGetInfo() {
		initRenRen();

		if (renren != null) {
			AsyncRenren asyncRenren = new AsyncRenren(renren);
			String uids[] = parseCommaIds("22273771");
			if(uids == null){
				return;
			}
			UsersGetInfoRequestParam param = new UsersGetInfoRequestParam(uids);
			
			AbstractRequestListener<UsersGetInfoResponseBean> listener = new AbstractRequestListener<UsersGetInfoResponseBean>() {

				@Override
				public void onComplete(final UsersGetInfoResponseBean bean) {
					new Runnable() {
						String json = null;
						@Override
						public void run() {

							String json = bean.toString();
							if(json!=null){
								parseJson();
							}
							Log.v("RenRen", bean.toString());

						}

						private void parseJson() {
							try {
								JSONArray array = new JSONArray(json);
								for (int i = 0; i < array.length(); i++) {
									renren_uid= Long.valueOf(array.getJSONObject(i).getString("uid"));
									renren_name = array.getJSONObject(i).getString("name");
									renren_sex = Integer.valueOf(array.getJSONObject(i).getString("sex"));
									renren_headurl = array.getJSONObject(i).getString("headurl");
									renren_tinyurl = array.getJSONObject(i).getString("tinyurl");
								}
							} catch (JSONException e) {
							
								e.printStackTrace();
							}		
						}
					};
				}

				@Override
				public void onRenrenError(final RenrenError renrenError) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", renrenError.getMessage());

						}
					};
				}

				@Override
				public void onFault(final Throwable fault) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", fault.getMessage());

						}
					};
				}
			};
			asyncRenren.getUsersInfo(param, listener );

		}

	}
	
	public void RenRenFriendsGet() {
		initRenRen();

		if (renren != null) {
			AsyncRenren asyncRenren = new AsyncRenren(renren);
			FriendsGetRequestParam param = new FriendsGetRequestParam();
			AbstractRequestListener<FriendsGetResponseBean> listener = new AbstractRequestListener<FriendsGetResponseBean>() {

				@Override
				public void onComplete(final FriendsGetResponseBean bean) {
					new Runnable() {
						String json = null;
						@Override
						public void run() {

							String json = bean.toString();
							if(json!=null){
								parseJson();
							}
							Log.v("RenRen", bean.toString());

						}

						private void parseJson() {
							try {
								JSONArray array = new JSONArray(json);
								for (int i = 0; i < array.length(); i++) {
								array.getJSONObject(i).getInt("pid");
								}
							} catch (JSONException e) {
							
								e.printStackTrace();
							}		
						}
					};
				}

				@Override
				public void onRenrenError(final RenrenError renrenError) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", renrenError.getMessage());

						}
					};
				}

				@Override
				public void onFault(final Throwable fault) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", fault.getMessage());

						}
					};
				}
			};
			asyncRenren.getFriends(param, listener);

		}

	}

	/**
	 * 解析逗号分割的字符串
	 * 
	 * @return
	 */
	protected String[] parseCommaIds(String s) {
		if (s == null) {
			return null;
		}
		String[] ids = s.split(",");
		return ids;
	}

}
