package firefist.wei.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Welcome extends Activity{

	String keyCheck;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);	
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //      WindowManager.LayoutParams.FLAG_FULLSCREEN);   //全屏显示
		
		SharedPreferences sharedPreferences = getSharedPreferences("u2bsp",
				MODE_PRIVATE);
		 keyCheck = sharedPreferences.getString("keycheckbox", "0");
		
		setContentView(R.layout.welcome);
		
	
		
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			if(keyCheck.equals("0") ){
			Intent intent = new Intent (Welcome.this,Viewpager.class);			
			startActivity(intent);
			}else{
				Intent intent2 = new Intent(Welcome.this,Login.class);
				startActivity(intent2);
			}
			
			Welcome.this.finish();
		}
	}, 2000);
   }
	
}
