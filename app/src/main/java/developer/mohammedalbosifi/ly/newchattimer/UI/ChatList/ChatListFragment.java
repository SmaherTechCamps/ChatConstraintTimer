package developer.mohammedalbosifi.ly.newchattimer.UI.ChatList;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
 import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
 import java.util.List;

import developer.mohammedalbosifi.ly.newchattimer.Base.BaseFragment;
import developer.mohammedalbosifi.ly.newchattimer.R;
import developer.mohammedalbosifi.ly.newchattimer.Utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_chat_list)
public class ChatListFragment extends BaseFragment {

    @ViewById
    RecyclerView rvChatList;
    @ViewById
    ProgressBar pbLoad;
    @ViewById
    TextView tvNotFound;
    @ViewById
    ImageView ivNotFound;
    @ViewById
    LinearLayout llNotFound;

    List<ChatListItem> chatListItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @AfterViews
    public void afterView() {
         pbLoad.setVisibility(View.VISIBLE);
        PackageManager pm = getContext().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        String[] Installed_App_List = new String[packages.size()];
        chatListItems = new ArrayList<>();
        String packageName = "";
        for (ApplicationInfo packageInfo : packages) {
            packageName = packageInfo.packageName;
            if (packageInfo.packageName.contains("facebook.orca")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_facebook2, "Facebook",isSaveInDB("Facebook")));
            } else if (packageName.contains("whatsapp")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_whatsapp, "WhatsApp",isSaveInDB("WhatsApp")));
            } else if (packageName.contains("instagram")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_instagram, "Instagram",isSaveInDB("Instagram")));
            } else if (packageName.contains("facebook.lite")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_facebook_lite, "Facebook Lite",isSaveInDB("Facebook Lite")));
            } else if (packageName.contains("twitter")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_twitter, "Twitter",isSaveInDB("Twitter")));
            } else if (packageName.contains("viber")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_viber, "Viber",isSaveInDB("Viber")));
            } else if (packageName.contains("skype")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_skype, "Skype",isSaveInDB("Skype")));
            } else if (packageName.contains("telegram")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_telegram, "Telegram",isSaveInDB("Telegram")));
            } else if (packageName.contains("line")) {
                chatListItems.add(new ChatListItem(packageInfo.packageName, R.drawable.ic_line, "Liine",isSaveInDB("Liine")));
            }
        }


        if (chatListItems.size() <= 0) {
            llNotFound.setVisibility(View.VISIBLE);
            rvChatList.setVisibility(View.GONE);
            pbLoad.setVisibility(View.GONE);
        } else {
            llNotFound.setVisibility(View.GONE);
            rvChatList.setVisibility(View.VISIBLE);
            pbLoad.setVisibility(View.GONE);
            rvChatList.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rvChatList.setHasFixedSize(true);
            rvChatList.setLayoutAnimation(appInstanse.getAnimationUtil().getAnnimateForReyclerView(rvChatList.getHeight()));
            rvChatList.setAdapter(new ChatListAdapter(getContext(), chatListItems, new ChatListAdapter.onClickItem() {
                @Override
                public void onItemClick(String packageName) {
                    new Utility().unInstallApp(packageName,getContext());

                }
            }));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void refresh(EventMessage em){
        afterView();
        showToast("dgsdfgdfg","e");
    }

    public boolean isSaveInDB(String appName){
        if (dbContext.getAppDao().getApp(appName)==null){
            return false;
        }else {
            return true;
        }
    }


    @Click({R.id.llNotFound,R.id.ivNotFound,R.id.tvNotFound})
    public void reLoad(){
        afterView();
    }


}
