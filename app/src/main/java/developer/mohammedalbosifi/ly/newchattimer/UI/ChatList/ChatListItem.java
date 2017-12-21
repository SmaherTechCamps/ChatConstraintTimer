package developer.mohammedalbosifi.ly.newchattimer.UI.ChatList;

/**
 * Created by Mohammed_Albosifi on 17/12/2017.
 */

public class ChatListItem {
    private String appName;
    private int imageResource;
    private String appPackage;
    private boolean isSetting;

    public ChatListItem(String appName, int imageResource, String appPackage, boolean isSetting) {
        this.appName = appName;
        this.imageResource = imageResource;
        this.appPackage = appPackage;
        this.isSetting = isSetting;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public boolean isSetting() {
        return isSetting;
    }

    public void setSetting(boolean setting) {
        isSetting = setting;
    }
}
