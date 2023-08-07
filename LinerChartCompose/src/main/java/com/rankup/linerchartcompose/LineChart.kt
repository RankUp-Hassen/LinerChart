@file:Suppress("NAME_SHADOWING")

package com.rankup.linerchartcompose


import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun LineChart(
    width:Dp=400.dp,
    height:Dp=300.dp,
    padding:Dp=10.dp,
    backgroundColor: Color= Color.White,
    data: List<Pair<Int, Double>> = emptyList(),
    textColor: Int = android.graphics.Color.BLACK,
    fontSize: Int = 15,
    graphColor:Color= Color.Blue,
    strokeColor: Color= Color.Blue,
    strokeWidth: Dp = 2.dp,
    alphaGraphColor:Float=0.5f,
    horizontalSymbol:String="",
    verticalSymbol:String="",
    verticalTextCount:Int=5,
    typeFace:  Typeface =Typeface.create(Typeface.DEFAULT, Typeface.BOLD),
    animationDuration:Int=3000,
    verticalAnimation: Boolean=true,
    horizontalAnimation: Boolean=true,







) {
    val spacing = 100f
    val transparentGraphColor = remember { graphColor.copy(alpha = alphaGraphColor) }
    val upperValue = remember { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0 }
    val lowerValue = remember { (data.minOfOrNull { it.second }?.toInt() ?: 0) }
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {

            color =textColor
            textAlign = Paint.Align.CENTER
            textSize = density.run { fontSize.sp.toPx() }
            typeface = typeFace
        }
    }

    Box(
        modifier= Modifier
            .width(width)
            .padding(padding)
            .height(height)
            .background(backgroundColor),
        contentAlignment = Alignment.BottomCenter
    ) {

        Canvas(
            modifier= Modifier
                .width(width)
                .padding(padding)
                .height(height)
        ) {
            val spacePerHour = (size.width - spacing) / data.size

            (data.indices step 2).forEach { i ->
                val hour = data[i].first
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "$hour $horizontalSymbol",
                        spacing + i * spacePerHour,
                        size.height,
                        textPaint
                    )
                }
            }

            val priceStep = (upperValue - lowerValue) / verticalTextCount.toFloat()
            (0 until verticalTextCount).forEach { i ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        round(lowerValue + priceStep * i).toString()+" $verticalSymbol",
                        20f,
                        size.height - spacing - i * size.height / verticalTextCount.toFloat(),
                        textPaint
                    )
                }
            }




        }


        var expanded by remember { mutableStateOf(false) }
        val targetHeight = if (expanded) height else 20.dp
        val animatedHeight by animateDpAsState(
            targetValue = targetHeight ,
            animationSpec = tween(durationMillis = animationDuration)
        )



        Box(
            modifier = Modifier
                .height(if (verticalAnimation) animatedHeight else  height ),
            contentAlignment = Alignment.BottomCenter
        ){





            Canvas(
                modifier= Modifier
                    .width(width)
                    .padding(padding)
                    .height(height)

            ){


                val spacePerHour = (size.width - spacing) / data.size


                expanded=true
                val strokePath = Path().apply {
                    val height = size.height
                    data.indices.forEach { i ->
                        val info = data[i]
                        val ratio = (info.second - lowerValue) / (upperValue - lowerValue)

                        val x1 = spacing + i * spacePerHour
                        val y1 = height - spacing - (ratio * height).toFloat()

                        if (i == 0) { moveTo(x1, y1) }
                        lineTo(x1, y1)
                    }
                }

                drawPath(
                    path = strokePath,
                    color = strokeColor,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )

                val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
                    lineTo(size.width - spacePerHour, size.height - spacing)
                    lineTo(spacing, size.height - spacing)
                    close()
                }

                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            transparentGraphColor,
                            Color.Transparent
                        ),
                        endY = size.height - spacing
                    )
                )


            }
        }


        if (horizontalAnimation){
            val w=
                if (width>400.dp) 400.dp else width
            var expand by remember { mutableStateOf(false) }
            val targetHeight = if (expand) 0.dp else w-spacing.dp
            val animatedHeight by animateDpAsState(
                targetValue = targetHeight ,
                animationSpec = tween(durationMillis = animationDuration)
            )

            Box(
                modifier = Modifier
                    .width(animatedHeight)
                    .height(height - spacing.dp + 60.dp)
                    .background(backgroundColor)
                    .align(Alignment.TopEnd),


            )
            expand=true



        }




    }



}






@Composable
fun QuadLineChart(
    width:Dp=400.dp,
    height:Dp=300.dp,
    padding:Dp=10.dp,
    backgroundColor: Color= Color.White,
    data: List<Pair<Int, Double>> = emptyList(),
    textColor: Int = android.graphics.Color.BLACK,
    fontSize: Int = 15,
    graphColor:Color= Color.Blue,
    strokeColor: Color= Color.Blue,
    strokeWidth: Dp = 2.dp,
    alphaGraphColor:Float=0.5f,
    horizontalSymbol:String="",
    verticalSymbol:String="",
    verticalTextCount:Int=5,
    typeFace:  Typeface =Typeface.create(Typeface.DEFAULT, Typeface.BOLD),
    animationDuration:Int=3000,
    verticalAnimation: Boolean=true,
    horizontalAnimation: Boolean=true,

) {
    val spacing = 100f
    val transparentGraphColor = remember { graphColor.copy(alpha = alphaGraphColor) }
    val upperValue = remember { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0 }
    val lowerValue = remember { (data.minOfOrNull { it.second }?.toInt() ?: 0) }
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = textColor
            textAlign = Paint.Align.CENTER
            textSize = density.run { fontSize.sp.toPx() }
            typeface=typeFace
        }
    }


    Box(
        modifier= Modifier
            .width(width)
            .padding(padding)
            .height(height)
            .background(backgroundColor),
        contentAlignment = Alignment.BottomCenter
    ) {



        Canvas(
            modifier= Modifier
                .width(width)
                .padding(padding)
                .height(height)
        ) {
            val spacePerHour = (size.width - spacing) / data.size
            (data.indices step 2).forEach { i ->
                val hour = data[i].first
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "$hour $horizontalSymbol",
                        spacing + i * spacePerHour,
                        size.height,
                        textPaint
                    )
                }
            }

            val priceStep = (upperValue - lowerValue) / verticalTextCount.toFloat()
            (0 until verticalTextCount).forEach { i ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        round(lowerValue + priceStep * i).toString()+" $verticalSymbol",
                        20f,
                        size.height - spacing - i * size.height / verticalTextCount.toFloat(),
                        textPaint
                    )
                }
            }

        }

        var expanded by remember { mutableStateOf(false) }
        val targetHeight = if (expanded) height else 20.dp
        val animatedHeight by animateDpAsState(
            targetValue = targetHeight ,
            animationSpec = tween(durationMillis = animationDuration)
        )



        Box(
            modifier = Modifier
                .height(if (verticalAnimation) animatedHeight else  height ),
            contentAlignment = Alignment.BottomCenter
        ){




            Canvas(
                modifier= Modifier
                    .width(width)
                    .padding(padding)
                    .height(height)

            ) {
                expanded = true
                val spacePerHour = (size.width - spacing) / data.size

                var medX: Float
                var medY: Float
                val strokePath = Path().apply {
                    val height = size.height
                    data.indices.forEach { i ->
                        val nextInfo = data.getOrNull(i + 1) ?: data.last()
                        val firstRatio = (data[i].second - lowerValue) / (upperValue - lowerValue)
                        val secondRatio = (nextInfo.second - lowerValue) / (upperValue - lowerValue)

                        val x1 = spacing + i * spacePerHour
                        val y1 = height - spacing - (firstRatio * height).toFloat()
                        val x2 = spacing + (i + 1) * spacePerHour
                        val y2 = height - spacing - (secondRatio * height).toFloat()
                        if (i == 0) {
                            moveTo(x1, y1)
                        } else {
                            medX = (x1 + x2) / 2f
                            medY = (y1 + y2) / 2f
                            quadraticBezierTo(x1 = x1, y1 = y1, x2 = medX, y2 = medY)
                        }
                    }
                }

                drawPath(
                    path = strokePath,
                    color = strokeColor,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )

                val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
                    lineTo(size.width - spacePerHour, size.height - spacing)
                    lineTo(spacing, size.height - spacing)
                    close()
                }

                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            transparentGraphColor,
                            Color.Transparent
                        ),
                        endY = size.height - spacing
                    )
                )



            }


        }

        if (horizontalAnimation){
            val w=
                if (width>400.dp) 400.dp else width
            var expand by remember { mutableStateOf(false) }
            val targetHeight = if (expand) 0.dp else w-spacing.dp
            val animatedHeight by animateDpAsState(
                targetValue = targetHeight ,
                animationSpec = tween(durationMillis = animationDuration)
            )

            Box(
                modifier = Modifier
                    .width(animatedHeight)
                    .height(height - spacing.dp + 60.dp)
                    .background(backgroundColor)
                    .align(Alignment.TopEnd),


                )
            expand=true


        }

    }
}
