package netroxtech.com.employeetracker.Models;

/**
 * Created by mac on 10/17/2016.
 */
public class Locations {
    private String latitude= null;
    private String longitude = null;
    private String currentTime = null;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
