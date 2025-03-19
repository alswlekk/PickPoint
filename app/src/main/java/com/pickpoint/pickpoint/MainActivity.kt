package com.pickpoint.pickpoint

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.pickpoint.pickpoint.navigation.PickPointNavGraph
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var isLoading = true // splash 유지 여부 결정(이후에 viewModel 에서 값 가져오도록 수정?)
    private lateinit var appTheme: AppTheme

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Splash
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            // 이 람다 안의 값이 true이면 Splash 화면이 유지됨
            isLoading
        }

        val dataStoreManager = DataStoreManager(this)

        lifecycleScope.launch { // 2초후에 Splash 화면 종료
            appTheme = runBlocking {
                dataStoreManager.getAppThemeSetting().first()
            }
            delay(2000L)
            isLoading = false
        }
        enableEdgeToEdge()
        setContent {
            var mAppTheme by remember { mutableStateOf(appTheme) }

            PickPointTheme(theme = mAppTheme, dynamicColor = false) {

                Log.d("MainActivity", "appTheme: $mAppTheme")
                val navController = rememberNavController()
                // homeViewModel을 전달
                PickPointNavGraph(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars),
                    navController = navController,
                    dataStoreManager = dataStoreManager,
                    changeTheme = { newTheme ->
                        lifecycleScope.launch {
                            mAppTheme = newTheme
                        }
                    }
                )
            }
        }
    }
}
