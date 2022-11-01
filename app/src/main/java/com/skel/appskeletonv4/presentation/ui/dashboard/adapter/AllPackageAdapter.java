package com.skel.appskeletonv4.presentation.ui.dashboard.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageDTO;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceItem;
import com.skel.appskeletonv4.domain.common.Constants;
import com.skel.appskeletonv4.domain.common.utils.list_utils.DataBoundListAdapter;
import com.skel.appskeletonv4.domain.common.utils.list_utils.DataBoundViewHolder;

import java.util.List;


public class AllPackageAdapter extends DataBoundListAdapter<PackageDTO, AllPackageAdapter.PackageViewHolder> {

    private OnPackageSelectionListener listener;
    private int packageListType;

    public AllPackageAdapter(int _packageListType,OnPackageSelectionListener _listener){
        this.packageListType = _packageListType;
        this.listener = _listener;
    }

    @Override
    public int createLayoutView() {
        return R.layout.view_package_item;
    }

    @Override
    public PackageViewHolder wrapViewHolder(View view) {
        return new PackageViewHolder(view);
    }

    @Override
    public void onBind(@NonNull PackageViewHolder holder, int position, PackageDTO item) {

        holder.rv_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onSelectPackage(item);
                }
            }
        });

        holder.cv_packageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onSelectPackage(item);
                }
            }
        });

        holder.btn_viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onSelectPackage(item);
                }
            }
        });


        holder.tv_packageName.setText(item.getPackageName());
        List<ServiceItem> serviceItems = item.getServiceDetails();

        if(packageListType == Constants.PACKAGE_SELECTION_TYPE){

            holder.ll_package_select_view.setVisibility(View.VISIBLE);
            holder.rb_selectionPack.setChecked(false);

            holder.rb_selectionPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{

                        if(listener!=null){
                            listener.onSelectPackage(item);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }else {
            holder.ll_package_select_view.setVisibility(View.GONE);
        }

    }

    @Override
    protected boolean areItemsTheSame(PackageDTO oldItem, PackageDTO newItem) {
        return false;
    }

    @Override
    protected boolean areContentsTheSame(PackageDTO oldItem, PackageDTO newItem) {
        return false;
    }

    @Override
    protected void dispatched() {

    }

    public class PackageViewHolder extends DataBoundViewHolder{

        public RecyclerView rv_services;
        public RadioButton rb_selectionPack;
        public LinearLayout ll_package_select_view;
        public AppCompatButton btn_viewDetails;
        public AppCompatTextView tv_packageName;
        public CardView cv_packageItem;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindView(View view) {

            rv_services = view.findViewById(R.id.rv_pack_services);
            ll_package_select_view = view.findViewById(R.id.ll_package_selection);

            btn_viewDetails = view.findViewById(R.id.btn_view);
            tv_packageName = view.findViewById(R.id.tv_packageName);
            cv_packageItem = view.findViewById(R.id.cv_package_item);

            if(packageListType == Constants.PACKAGE_SELECTION_TYPE){
                rb_selectionPack = view.findViewById(R.id.rb_select_package);
            }else {

            }

        }
    }

    public interface OnPackageSelectionListener{
        void onSelectPackage(PackageDTO selectedPackage);
    }
}
