package com.lzy.cityweather.cityui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.lzy.cityweather.cityui.vm.WeatherViewModel

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
@Composable
fun CityWeatherWidget(code: String = "", lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current, weatherViewModel: WeatherViewModel) {
    val weatherInfoStatus = weatherViewModel.map[code]?.collectAsState()

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                weatherViewModel.getWeather(code)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(width = 12.dp, color = Color.White, shape = RoundedCornerShape(6.dp))
            .background(Color(0xFF002FA7))
    ) {
        Spacer(
            modifier = Modifier
                .height(25.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${weatherInfoStatus?.value?.city}",
                color = Color.White,
                fontSize = 18.sp,
            )
        }
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "天气情况:${weatherInfoStatus?.value?.weather}",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "当前温度:${weatherInfoStatus?.value?.temperature}",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}