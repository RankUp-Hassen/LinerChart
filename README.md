# LinerChart
LinerChart Compose with powreful Animation 
[![](https://jitpack.io/v/RankUp-Hassen/LinerChart.svg)](https://jitpack.io/#RankUp-Hassen/LinerChart)


look this is powerful Compose Views :

https://github.com/RankUp-Hassen/LinerChart/assets/140910656/1af3278a-84da-44c6-bf7d-1c90beee9db3



To get a Git project into your build:

# Step 1. Add the JitPack repository to your build file


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
# Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.RankUp-Hassen:LinerChart:Tag'
	}


 if implementation don't working u can Download Releases from here :
https://github.com/RankUp-Hassen/LinerChart/archive/refs/tags/1.1.1.zip


# Step 3: 
   # LinerChart Code:
   	LineChart(
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

   # QuadLineChart Code:
   
   	QuadLineChart(
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
   	

   


