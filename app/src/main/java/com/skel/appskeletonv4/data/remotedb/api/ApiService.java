package com.skel.appskeletonv4.data.remotedb.api;

import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.GetBranchProfileDataResponse;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageResponseDTO;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<BranchLoginDTO> branchLogin(@Field("email") String email, @Field("password") String password);

    @GET("package/list")
    Call<PackageResponseDTO> getAllPackages();


    @GET("service/list")
    Call<ServiceListResponse> getAllServices();

    @FormUrlEncoded
    @POST("branch/details")
    Call<GetBranchProfileDataResponse> getBranchProfileData(@Field("branch_id") String branchId);

}
