package developer.mohammedalbosifi.ly.newchattimer.UI.About;

import android.support.v4.app.Fragment;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import developer.mohammedalbosifi.ly.newchattimer.R;

@EFragment(R.layout.fragment_about)
public class AboutFragment extends Fragment {

    @ViewById
    ExpandableLayout expandable_layout2,expandable_layout;

    @Click
    public void txt(){
        expandable_layout.toggle(true);
        expandable_layout2.toggle(true);
    }

    @Click
    public void txt2(){
        expandable_layout.toggle(true);
        expandable_layout2.toggle(true);
    }

    @AfterViews
    public void afterView(){

    }


}
