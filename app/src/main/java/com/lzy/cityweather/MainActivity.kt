package com.lzy.cityweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.lzy.cityweather.cityui.CityWeatherWidget
import com.lzy.cityweather.cityui.vm.WeatherViewModel
import com.lzy.cityweather.config.Constant.LIST_CODE
import com.lzy.cityweather.ui.theme.CityWeatherTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: WeatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        setContent {
            CityWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .padding(12.dp)
                            .background(Color.White)
                    ) {
                        ScrollPager(this@MainActivity, vm)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollPager(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current, weatherViewModel: WeatherViewModel) {
    HorizontalPager(pageCount = 6) { page ->
        CityWeatherWidget(LIST_CODE[page], lifecycleOwner, weatherViewModel)
    }
}