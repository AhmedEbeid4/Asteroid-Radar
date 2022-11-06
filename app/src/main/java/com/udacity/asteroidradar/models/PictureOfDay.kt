package com.udacity.asteroidradar.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class PictureOfDay(
    val date:String,
    val explanation:String,
    val title:String,
    @PrimaryKey
    val url:String
){
}
//
//{
//    "copyright": "Aman Chokshi",
//    "date": "2022-11-05",
//    "explanation": "Last May 16 the Moon slid through Earth's shadow, completely immersed in the planet's dark umbra for about 1 hour and 25 minutes during a total lunar eclipse. In this composited timelapse view, the partial and total phases of the eclipse were captured as the Moon tracked above the horizon from Amundsen-Scott South Pole Station. There it shared a cold and starry south polar night with a surging display of the aurora australis and central Milky Way. In the foreground are the BICEP (right) and South Pole telescopes at the southernmost station's Dark Sector Laboratory. But while polar skies can be spectacular, you won't want to go to the South Pole to view the total lunar eclipse coming up on November 8. Instead, that eclipse can be seen from locations in Asia, Australia, the Pacific, the Americas and Northern Europe. It will be your last chance to watch a total lunar eclipse until 2025.",
//    "hdurl": "https://apod.nasa.gov/apod/image/2211/Lunar-Eclipse-South-Pole.jpg",
//    "media_type": "image",
//    "service_version": "v1",
//    "title": "Lunar Eclipse at the South Pole",
//    "url": "https://apod.nasa.gov/apod/image/2211/Lunar-Eclipse-South-Pole_1024.jpg"
//}