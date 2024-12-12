
<img src="./logo.svg" width="400" height="400"/>

# Cafe
a plugin for android compose theme changing  

## 1. import Cafe plugin

```kotlin
plugins {
    id("io.github.kapaseker.cafe")
}
```

## 2. config you project theme name
app's build.gradle.kts
```kotlin
cafe {
    cup = setOf("dusk", "night") // this means you will use two themes, dusk and night.
}
```

## 3. define your theme colors (also support drawables/mipmaps, clone this project to learn more)
```xml
    <color name="purple">#FF3700B3</color> <!--common color--> 
    <color name="purple_dusk">#FFFF0088</color> <!--dust color--> 
    <color name="purple_night">#FF37FFB3</color> <!--night color--> 
```

## 4. build your project
**this is important**, because this plugin will auto generate code by building project, you can use gralde task `preBuild` to avoid a whole build.

## 5. use CafeTheme
```kotlin
setContent {
    CafeTheme { // use this theme, alse can include other themes(like MaterialTheme).
        Box() {}
    }
}
``` 

## 6. use Cafe's color

use `Cafe.color` anywhere to enalbe theme change.

```kotlin
Box(modifier = Modifier.background(colorResource(id = Cafe.color.purple)))
```


## 7. change theme and enjoy

```kotlin

Cafe.setCup(DUSK) // change all color to dusk color

Cafe.setCup(MAIN) // change all color to default main color

Cafe.setCup(NIGHT) // change all color to night color

```




