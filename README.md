Download this app to your Android phone to see the weather forecast!

This is a Java Andoid App built using a REST API at open-meteo.com.
The app runs asynchronous (non-blocking code) callback functions to get data.

Android has several libraries to help with asynchronous API code.
This application uses Volley.
Please note that google now recommends Cronet instead of Volley for API parsing.
Another alternative is Retrofit which is recommend as part of Jetpack (Googles 2019 release)

First we find latitude and longitude by city using the Geo Coding api at https://open-meteo.com/en/docs/geocoding-api.
Then we use the latitude and longitude to get weather by location using the Weather Forcast API.

This app is based on a deprecated tutorial at freeCodeCamp.org.
The biggest change is the removal of the deprecated metaWeather API and the more-documented open-meteo API.

![Alt Image text](https://github.com/RamonJustisOrtega/AndroidWeatherApp/blob/main/AndroidWeatherApp.jpg)
