package com.trunnghieu.tplogistic.ui.screens.job_detail.pickup_location

import android.location.Location
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.SphericalUtil
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.databinding.FragmentPickupLocationBinding
import com.trunnghieu.tplogistic.ui.base.dialog.AppAlertDialog
import com.trunnghieu.tplogistic.ui.screens.job.JobVM
import com.trunnghieu.tplogistic.ui.screens.job_detail.maps.MapsForJobFragment

class PickupLocationFragment :
    MapsForJobFragment<FragmentPickupLocationBinding, PickupLocationVM>(), PickupLocationUV {

    override fun layoutRes() = R.layout.fragment_pickup_location

    override fun viewModelClass(): Class<PickupLocationVM> {
        return PickupLocationVM::class.java
    }

    private val jobVM by activityViewModels<JobVM>()

    override fun initViewModel(viewModel: PickupLocationVM) {
        viewModel.init(this)
        binding.apply {
            vm = viewModel
            locationVM = this@PickupLocationFragment.locationVM
            jobVM = this@PickupLocationFragment.jobVM
        }
    }

    private lateinit var mapsBottomSheet: BottomSheetBehavior<View>
    private var executedApi = false

    override fun initMapsView() = binding.viewMaps.mapView

    override fun initView() {
        mapsBottomSheet = BottomSheetBehavior.from(binding.viewMaps.root)
        calculatePeekHeightForBottomSheet(binding.viewJobData) { peekHeight ->
            mapsBottomSheet.peekHeight = peekHeight
        }
    }

    override fun initAction() {
        super.initAction()
        binding.viewMaps.run {
            setExpandCollapseMapsOnClick {
                if (mapsBottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
                    mapExpanded = false
                    mapsBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                } else {
                    mapExpanded = true
                    mapsBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    override fun onLocationPermissionGranted() {
        jobVM.run {
            // Observer connectivity changes
//            isLocationOrInternetConnected.observe(this@PickupLocationFragment) { isConnected ->
//                viewModel.isConnected = isConnected
//
//                if (!isConnected) {
//                    viewModel.showLocalJob()
//                    return@observe
//                }
//
//                if (!executedApi) {
//                    executedApi = true
//                    viewModel.getJobAtPickupArrive()
//                }
//            }

            // Observer latest job
            latestJob.observe(this@PickupLocationFragment) { latestJob ->
                locationVM.showDataOnMap(latestJob)
            }
        }
    }

    override fun loadMapData() {
        locationVM.showDataOnMap(jobVM.latestJob.value)
    }

    override fun locationChanged(location: Location) {
        super.locationChanged(location)
        // Update user marker
        val lastKnownLatLng = LatLng(location.latitude, location.longitude)
        userMarker?.position = lastKnownLatLng
        val latestJob = jobVM.latestJob.value ?: return
        val pickupLatLng = LatLng(latestJob.getPickUpLatitude(), latestJob.getPickUpLongitude())
        val markerBearing = SphericalUtil.computeHeading(lastKnownLatLng, pickupLatLng).toFloat()
        zoomCameraBetweenTwoLocations(markerBearing, lastKnownLatLng, pickupLatLng)

        // Calculate last known location with pickup location
        viewModel.calculateLocationWithRadius(
            location,
            pickupLatLng.latitude,
            pickupLatLng.longitude,
            5.0
        )
    }

    override fun updateLatestJob(latestJob: Job) {
        jobVM.latestJob.value = latestJob
    }

    override fun confirmArriveAtPickup() {
        showAlert(
            fragmentContext.getString(R.string.job_detail_msg_not_arrive_at_pickup),
            fragmentContext.getString(R.string.job_detail_btn_arrived),
            fragmentContext.getString(R.string.job_detail_btn_continue),
            object : AppAlertDialog.AlertDialogOnClickListener {
                override fun onPositiveClick() {
                    viewModel.submitPickupArrive()
                }

                override fun onNegativeClick() {

                }
            }
        )
    }

    override fun pickupArriveDone(jobStatus: ApiConst.JobStatus) {
        jobVM.run {
            changeJobStatus(jobStatus)

            // [GOT-450] Disable highlight job when change other job screen
            showHighlightDataChanged(show = false, disableImmediately = true)
        }
    }
}
