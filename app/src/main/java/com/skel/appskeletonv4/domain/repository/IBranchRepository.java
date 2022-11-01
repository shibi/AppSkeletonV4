package com.skel.appskeletonv4.domain.repository;

import androidx.lifecycle.LiveData;
import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.BranchProfileData;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import java.util.List;

public interface IBranchRepository {

    LiveData<Resource<BranchLoginDTO>> branchLogin(String email, String password);

    LiveData<List<Branch>> getSavedBranchLogin();

    LiveData<Resource<Boolean>> deleteBranchLogin();

    LiveData<Resource<BranchProfileData>> getBranchProfileData(String branchId);
}
