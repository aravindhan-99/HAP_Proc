package com.hap.checkinproc.MVP;

import com.hap.checkinproc.Model_Class.Approval;
import com.hap.checkinproc.Model_Class.Route_Master;

import java.util.ArrayList;

public interface  Main_Model {

    interface presenter {
        void onDestroy();
        void onRefreshButtonClick();
        void requestDataFromServer();

    }

    interface MasterSyncView {

        void showProgress();

        void hideProgress();
        void setDataToRoute(ArrayList<Route_Master> noticeArrayList);
        void setDataToRouteObject(Object noticeArrayList,int position);
        void onResponseFailure(Throwable throwable);

    }


    interface GetRoutemastersyncResult {

        interface OnFinishedListenerroute {
            void onFinishedroute(ArrayList<Route_Master> noticeArrayList);
            void onFinishedrouteObject(Object noticeArrayList,int position);
            void onFailure(Throwable t);
        }

        void GetRouteResult(GetRoutemastersyncResult.OnFinishedListenerroute onFinishedListener);

    }
}