package com.skel.appskeletonv4.domain.repository;

import androidx.lifecycle.LiveData;

import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageDTO;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceItem;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;

import java.util.List;

public interface IPackageRepository {

    LiveData<Resource<List<PackageDTO>>> getAllPackages();

    LiveData<Resource<List<ServiceItem>>> getAllServices();

}
