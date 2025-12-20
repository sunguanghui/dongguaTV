package com.ednovas.donguatv;

import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 强制解决状态栏遮挡：让内容不延伸到状态栏区域
        try {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);

            View decorView = window.getDecorView();
            // 清除全屏标志，确保显示状态栏
            int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(uiOptions);

            // 关键：让根布局适应系统窗口（即给状态栏留出 Padding）
            // 这会强制 Webview 向下移动，不被黑色状态栏遮挡
            View content = findViewById(android.R.id.content);
            if (content != null) {
                content.setFitsSystemWindows(true);
                // 强制请求重新布局
                content.requestApplyInsets();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
