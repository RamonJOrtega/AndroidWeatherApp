package com.example.androidweatherapp;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class WeatherReportModel {

    String day;
    float dayLowTemp;
    float dayHighTemp;
    int weathercode;
    private float latitude;
    private float longitude;
    private float elevation;
    private float generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private JSONObject daily_units = new JSONObject();
    private JSONObject daily = new JSONObject();

    public WeatherReportModel(String day, float dayLowTemp, float dayHighTemp, int weathercode, float latitude, float longitude, float elevation, float generationtime_ms, int utc_offset_seconds, String timezone, String timezone_abbreviation, JSONObject daily_units, JSONObject daily) {
        this.day = day;
        this.dayLowTemp = dayLowTemp;
        this.dayHighTemp = dayHighTemp;
        this.weathercode = weathercode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.generationtime_ms = generationtime_ms;
        this.utc_offset_seconds = utc_offset_seconds;
        this.timezone = timezone;
        this.timezone_abbreviation = timezone_abbreviation;
        this.daily_units = daily_units;
        this.daily = daily;
    }

    public  WeatherReportModel() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getDayLowTemp() {
        return dayLowTemp;
    }

    public void setDayLowTemp(float dayLowTemp) {
        this.dayLowTemp = dayLowTemp;
    }

    public float getDayHighTemp() {
        return dayHighTemp;
    }

    public void setDayHighTemp(float dayHighTemp) {
        this.dayHighTemp = dayHighTemp;
    }

    public int getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(int weathercode) {
        this.weathercode = weathercode;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public float getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(float generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public JSONObject getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(JSONObject daily_units) {
        this.daily_units = daily_units;
    }

    public JSONObject getDaily() {
        return daily;
    }

    public void setDaily(JSONObject daily) {
        this.daily = daily;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {

        return
                LocalDate.parse(day, DateTimeFormatter.ISO_DATE).getDayOfWeek().name() + ' ' +
                timezone_abbreviation + " " + day + " " + '\n' +
                dayLowTemp + "\u00B0F" +" - " + dayHighTemp + "\u00B0F" + "\n" +
                getWeatherCodeDescription(weathercode)
                ;
    }


        public static String getWeatherCodeDescription(int weatherCode) {
        String description = "invalid Code";
        switch (weatherCode) {
            case 0: {
                description = "Clear sky \t \u263c";
                break;
            }
            case 1: {
                description = "Mainly clear \t \u263c";
                break;
            }
            case 2: {
                description = "Partly Cloudy \t \u26C5";
                break;
            }
            case 3: {
                description = "Overcast \t \u2601";
                break;
            }
            case 45: {
                description = "Fog \t \u2634";
                break;
            }
            case 48: {
                description = "Depositing rime fog \t \u2634";
                break;
            }
            case 51: {
                description = "Light Drizzle \t \u2614";
                break;
            }
            case 53: {
                description = "Moderate Drizzle \t \u2614";
                break;
            }
            case 55: {
                description = "Dense Drizzle \t \u2614";
                break;
            }
            case 56: {
                description = "Light Freezing Drizzle \t \u2614";
                break;
            }
            case 57: {
                description = "Dense Freezing Drizzle \t \u2614";
                break;
            }
            case 61: {
                description = "Slight Rain \t \u2614";
                break;
            }
            case 63: {
                description = "Moderate Rain \t \u2614";
                break;
            }
            case 65: {
                description = "Heavy Rain \t \u26C6";
                break;
            }
            case 66: {
                description = "Light Freezing Rain \t \u26C6";
                break;
            }
            case 67: {
                description = "Heavy Freezing Rain \t \u26C6";
                break;
            }
            case 71: {
                description = "Slight Snow Fall \t \u2744";
                break;
            }
            case 73: {
                description = "Moderate Snow Fall \t \u2744";
                break;
            }
            case 75: {
                description = "Heavy Snow Fall \t \u26c7";
                break;
            }
            case 77: {
                description = "Snow Grains \t \u2745";
                break;
            }
            case 80: {
                description = "Slight Rain Showers \t \u2614";
                break;
            }
            case 81: {
                description = "Moderate Rain Showers \t \u2614";
                break;
            }
            case 82: {
                description = "Violent Rain Showers \t \u2614";
                break;
            }
            case 85: {
                description = "Slight Snow Showers \t \u2744";
                break;
            }
            case 86: {
                description = "Heavy Snow Showers \t \u26c7";
                break;
            }
            case 95: {
                description = "Slight/Moderate Thunderstorm \t \uF329";
                break;
            }
            case 96: {
                description = "Thunderstorm with Slight Hail \t \u26c8";
                break;
            }
            case 99: {
                description = "Thunderstorm with Heavy Hail \t \u26c8";
                break;
            }
            default: {
                break;
            }
        }
        return description;
    }

}


