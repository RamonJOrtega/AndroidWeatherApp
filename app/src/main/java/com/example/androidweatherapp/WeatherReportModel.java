package com.example.androidweatherapp;

import org.json.JSONObject;

public class WeatherReportModel {

    private float latitude;
    private float longitude;
    private float elevation;
    private float generationtime_ms;
    private float utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private JSONObject hourly = new JSONObject();
    //hourly.put("time", new Integer(100));
    //hourly.put("temperature_2m", new Integer(2000));
    private JSONObject hourly_units = new JSONObject();
    private JSONObject current_weather = new JSONObject();

    public WeatherReportModel(float latitude, float longitude, float elevation, float generationtime_ms, float utc_offset_seconds, String timezone, String timezone_abbreviation, JSONObject hourly, JSONObject hourly_units, JSONObject current_weather) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.generationtime_ms = generationtime_ms;
        this.utc_offset_seconds = utc_offset_seconds;
        this.timezone = timezone;
        this.timezone_abbreviation = timezone_abbreviation;
        this.hourly = hourly;
        this.hourly_units = hourly_units;
        this.current_weather = current_weather;
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", elevation=" + elevation +
                ", generationtime_ms=" + generationtime_ms +
                ", utc_offset_seconds=" + utc_offset_seconds +
                ", timezone='" + timezone + '\'' +
                ", timezone_abbreviation='" + timezone_abbreviation + '\'' +
                ", hourly=" + hourly +
                ", hourly_units=" + hourly_units +
                ", current_weather=" + current_weather +
                '}';
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

    public float getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(float utc_offset_seconds) {
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

    public JSONObject getHourly() {
        return hourly;
    }

    public void setHourly(JSONObject hourly) {
        this.hourly = hourly;
    }

    public JSONObject getHourly_units() {
        return hourly_units;
    }

    public void setHourly_units(JSONObject hourly_units) {
        this.hourly_units = hourly_units;
    }

    public JSONObject getCurrent_weather() {
        return current_weather;
    }

    public void setCurrent_weather(JSONObject current_weather) {
        this.current_weather = current_weather;
    }

    //    public static String getWeatherCodeDescription(int weatherCode) {
//        String description = "invalid Code";
//        switch (weatherCode) {
//            case 0: {
//                description = "Clear sky";
//                break;
//            }
//            case 1: {
//                description = "Mainly clear";
//                break;
//            }
//            case 2: {
//                description = "Partly Cloudy";
//                break;
//            }
//            case 3: {
//                description = "Overcast";
//                break;
//            }
//            case 45: {
//                description = "Fog";
//                break;
//            }
//            case 48: {
//                description = "Depositing rime fog";
//                break;
//            }
//            case 51: {
//                description = "Light Drizzle";
//                break;
//            }
//            case 53: {
//                description = "Moderate Drizzle";
//                break;
//            }
//            case 55: {
//                description = "Dense Drizzle";
//                break;
//            }
//            case 56: {
//                description = "Light Freezing Drizzle";
//                break;
//            }
//            case 57: {
//                description = "Dense Freezing Drizzle";
//                break;
//            }
//            case 61: {
//                description = "Slight Rain";
//                break;
//            }
//            case 63: {
//                description = "Moderate Rain";
//                break;
//            }
//            case 65: {
//                description = "Heavy Rain";
//                break;
//            }
//            case 66: {
//                description = "Light Freezing Rain";
//                break;
//            }
//            case 67: {
//                description = "Heavy Freezing Rain";
//                break;
//            }
//            case 71: {
//                description = "Slight Snow Fall";
//                break;
//            }
//            case 73: {
//                description = "Moderate Snow Fall";
//                break;
//            }
//            case 75: {
//                description = "Heavy Snow Fall";
//                break;
//            }
//            case 77: {
//                description = "Snow Grains";
//                break;
//            }
//            case 80: {
//                description = "Slight Rain Showers";
//                break;
//            }
//            case 81: {
//                description = "Moderate Rain Showers";
//                break;
//            }
//            case 82: {
//                description = "Violent Rain Showers";
//                break;
//            }
//            case 85: {
//                description = "Slight Snow Showers";
//                break;
//            }
//            case 86: {
//                description = "Heavy Snow Showers";
//                break;
//            }
//            case 95: {
//                description = "Slight/Moderate Thunderstorm";
//                break;
//            }
//            case 96: {
//                description = "Thunderstorm with Slight Hail";
//                break;
//            }
//            case 99: {
//                description = "Thunderstorm with Heavy Hail";
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        return description;
//    }

}


