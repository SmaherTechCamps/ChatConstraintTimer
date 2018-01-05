package developer.mohammedalbosifi.ly.newchattimer.UI.ChatList;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import developer.mohammedalbosifi.ly.newchattimer.R;
 import developer.mohammedalbosifi.ly.newchattimer.UI.ChatConstraintSetting.ChatConstraintActivity_;



public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.mViewHolder> {

    List<ChatListItem> chatListItems;
    Context context;
    onClickItem listener;

    public ChatListAdapter( Context context,List<ChatListItem> chatListItems,onClickItem listener) {
        this.context = context;
        this.chatListItems=chatListItems;
        this.listener=listener;
     }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_items,parent,false ));
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
             holder.bind(chatListItems.get(position));


    }

    @Override
    public int getItemCount() {
        return chatListItems.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName,tvPacckageName;
        ImageView iv,ivMenu,ivSetting;
        View v;
        boolean isSetting;
        public mViewHolder(View itemView) {
            super(itemView);
            v=itemView;
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPacckageName = (TextView) itemView.findViewById(R.id.tvPacckageName);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            ivMenu=(ImageView)itemView.findViewById(R.id.ivMenu);
            ivSetting=(ImageView)itemView.findViewById(R.id.ivSetting);
         }

        @SuppressLint("ResourceAsColor")
        public void  bind(ChatListItem appName){
            tvName.setText(appName.getAppPackage());
            tvPacckageName.setText(appName.getAppName());
            iv.setImageResource(appName.getImageResource());

            if (appName.isSetting()){
                ivSetting.setVisibility(View.VISIBLE);
                isSetting=true;
            }else {
                ivSetting.setVisibility(View.GONE);
                isSetting=false;
            }

            ivMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.unInstal:
                                    listener.onUnInstallApp(tvPacckageName.getText().toString().trim());
                                    break;
                                case R.id.timeSetting:
                                    startActivity();
                                    break;
                                case R.id.CancelTimeSetting:
                                    listener.onCancelTimeSetting(tvName.getText().toString().trim());
                                    break;

                            }
                            return false;
                        }
                    });
                    popupMenu.inflate(R.menu.chat_menu);
                    if (isSetting){
                        popupMenu.getMenu().getItem(0).setVisible(false);
                        popupMenu.getMenu().getItem(1).setVisible(true);
                    }else {
                        popupMenu.getMenu().getItem(1).setVisible(false);
                        popupMenu.getMenu().getItem(0).setVisible(true);                    }
                    popupMenu.show();
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   startActivity();
                }
            });
        }

        public void startActivity(){
//            Intent intent=new Intent(context, ChatConstraintActivity_.class);
//            intent.putExtra("appName",tvName.getText().toString().trim());
//            intent.putExtra("appPackage",tvPacckageName.getText().toString().trim());
//            intent.putExtra("isSetting",(isSetting) ? "true":"false" );
//
//            context.startActivity(intent);

            Intent myIntent = new Intent(context, ChatConstraintActivity_.class);
            myIntent.putExtra("appName",tvName.getText().toString().trim());
            myIntent.putExtra("appPackage",tvPacckageName.getText().toString().trim());
            myIntent.putExtra("isSetting",(isSetting) ? "true":"false" );
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
            context.startActivity(myIntent, options.toBundle());
        }
    }

    public interface onClickItem{
        void onUnInstallApp(String packageName);
        void onCancelTimeSetting(String appName);
    }
}
