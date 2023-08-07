package com.rankup.linerchart

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rankup.linerchart.ui.theme.LinerChartTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        super.onCreate(savedInstanceState)
        setContent {
            LinerChartTheme {
                val data = listOf(
                    Pair(6, 111.45),
                    Pair(7, 111.0),
                    Pair(8, 113.45),
                    Pair(9, 112.25),
                    Pair(10, 116.45),
                    Pair(11, 113.35),
                    Pair(12, 118.65),
                    Pair(13, 111.15),
                    Pair(14, 113.05),
                    Pair(15, 114.25),
                    Pair(16, 116.35),
                    Pair(17, 117.45),
                    Pair(18, 112.65),
                    Pair(19, 115.45),
                    Pair(20, 111.85)
                )
                var ha by remember {
                    mutableStateOf(false)
                }
                var va by remember {
                    mutableStateOf(true)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp,),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Box(
                        modifier = Modifier
                            .clickable { ha=!ha }
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.DarkGray)
                            .padding(10.dp)

                    ) {
                        Text(text = "Animation")
                    }






                    UseLinerChart(data,va,ha)

                }

                


               





            }
        }
    }
}


@Composable
fun UseLinerChart(data: List<Pair<Int, Double>>,va:Boolean,ha:Boolean) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

       



        Column(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Blue), shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp))


        ) {
            com.rankup.linerchartcompose.LineChart(
                data = data,
                textColor = Color.Blue.hashCode(),
                fontSize = 10,
                graphColor = Color.Blue,
                strokeColor = Color.Blue,
                strokeWidth = 1.dp,
                horizontalSymbol = "A",
                verticalSymbol = "B",
                typeFace = Typeface.DEFAULT_BOLD,
                width = 400.dp,
                height = 300.dp,
                padding = 10.dp,
                verticalAnimation = va,
                horizontalAnimation = ha,
                animationDuration = 5000,


                )
        }



        Spacer(modifier = Modifier.height(40.dp))




        



        Column(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Red), shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp))


        ) {
            com.rankup.linerchartcompose.QuadLineChart(
                data = data,
                textColor = Color.Red.hashCode(),
                fontSize = 10,
                graphColor = Color.Red,
                strokeColor = Color.Red,
                strokeWidth = 1.dp,
                horizontalSymbol = "A",
                verticalSymbol = "B",
                typeFace = Typeface.DEFAULT_BOLD,
                width = 400.dp,
                height = 300.dp,
                padding = 10.dp,
                verticalAnimation = va,
                horizontalAnimation = ha,
                animationDuration = 5000,


                )
        }
    }


}
