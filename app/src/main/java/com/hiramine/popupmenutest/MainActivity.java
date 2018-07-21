package com.hiramine.popupmenutest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener, PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener
{
	View            m_viewForPopupmenuPos;	// ポップアップメニューを指定位置に表示するためのビュー
	GestureDetector m_gesturedetector;	// ロングタップ処理用
	int             m_iLastPopupmenu;	// ロングタップ時に表示するメニュー切り替え用

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		// ボタン
		Button button;
		button = findViewById( R.id.button1 );
		button.setOnClickListener( this );
		button = findViewById( R.id.button2 );
		button.setOnClickListener( this );

		// メインビュー
		View viewgroupMain = (ViewGroup)findViewById( R.id.mainview );

		// ポップアップメニューを指定位置に表示するためのビュー
		m_viewForPopupmenuPos = new View( this );	// ビューの作成
		m_viewForPopupmenuPos.setLayoutParams( new ViewGroup.LayoutParams( 1, 1 ) );	// ビューの大きさは,1x1
		m_viewForPopupmenuPos.setBackgroundColor( Color.TRANSPARENT );	// 透明で無害なビュー
		if( !( viewgroupMain instanceof ViewGroup ) )	// ビューグループのインスタンスか確認
		{
			return;
		}
		((ViewGroup)viewgroupMain).addView( m_viewForPopupmenuPos );

		// ロングタップ対応
		viewgroupMain.setOnTouchListener( this );
		// GestureDetectorの作成
		m_gesturedetector = new GestureDetector( this, this );

		// 最後に表示したメニュー
		m_iLastPopupmenu = 1;
	}

	@Override
	public void onClick( View view )
	{
		PopupMenu    popupmenu    = new PopupMenu( this, view );	// アンカービューは、ボタンを指定。
		MenuInflater inflater = popupmenu.getMenuInflater();
		switch( view.getId() )
		{
			case R.id.button1:
				inflater.inflate( R.menu.popup1, popupmenu.getMenu() );
				m_iLastPopupmenu = 1;
				break;
			case R.id.button2:
				inflater.inflate( R.menu.popup2, popupmenu.getMenu() );
				m_iLastPopupmenu = 2;
				break;
		}
		popupmenu.setOnMenuItemClickListener( this );
		popupmenu.setOnDismissListener( this );

		popupmenu.show();
	}

	@Override
	public boolean onTouch( View view, MotionEvent motionEvent )
	{
		if( view.getId() == R.id.mainview )
		{
			// ジェスチャ処理
			m_gesturedetector.onTouchEvent( motionEvent );
		}

		return false;
	}

	@Override
	public boolean onDown( MotionEvent motionEvent )
	{
		return false;
	}

	@Override
	public void onShowPress( MotionEvent motionEvent )
	{

	}

	@Override
	public boolean onSingleTapUp( MotionEvent motionEvent )
	{
		return false;
	}

	@Override
	public boolean onScroll( MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1 )
	{
		return false;
	}

	@Override
	public void onLongPress( MotionEvent motionEvent )
	{
		PopupMenu    popupmenu    = new PopupMenu( this, m_viewForPopupmenuPos );
		MenuInflater inflater = popupmenu.getMenuInflater();
		switch( m_iLastPopupmenu )
		{
			case 1:
				inflater.inflate( R.menu.popup1, popupmenu.getMenu() );
				break;
			case 2:
				inflater.inflate( R.menu.popup2, popupmenu.getMenu() );
				break;
		}
		popupmenu.setOnMenuItemClickListener( this );
		popupmenu.setOnDismissListener( this );

		m_viewForPopupmenuPos.setX( motionEvent.getX() );
		m_viewForPopupmenuPos.setY( motionEvent.getY() );
		popupmenu.show();
	}

	@Override
	public boolean onFling( MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1 )
	{
		return false;
	}

	@Override
	public boolean onMenuItemClick( MenuItem menuItem )
	{
		switch( menuItem.getItemId() )
		{
			case R.id.menuitem_aaa:
				Toast.makeText( this, "aaa", Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menuitem_bbb:
				Toast.makeText( this, "bbb", Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menuitem_www:
				Toast.makeText( this, "www", Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menuitem_xxx:
				Toast.makeText( this, "xxx", Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menuitem_yyy:
				Toast.makeText( this, "yyy", Toast.LENGTH_SHORT ).show();
				return true;
			case R.id.menuitem_zzz:
				Toast.makeText( this, "zzz", Toast.LENGTH_SHORT ).show();
				return true;
		}
		return false;
	}

	@Override
	public void onDismiss( PopupMenu popupMenu )
	{
		// ポップアップメニューが破棄された際の処理
		Toast.makeText( this, "dismiss", Toast.LENGTH_SHORT ).show();
	}
}
