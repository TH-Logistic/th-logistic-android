package com.trunnghieu.tplogistic.ui.screens.login

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.databinding.ActivityLoginBinding
import com.trunnghieu.tplogistic.databinding.DialogLocationDisclosureBinding
import com.trunnghieu.tplogistic.extensions.navigateTo
import com.trunnghieu.tplogistic.ui.base.activity.BaseActivity
import com.trunnghieu.tplogistic.ui.base.dialog.AppFunctionDialog
import com.trunnghieu.tplogistic.ui.base.dialog.AppLoadingDialog
import com.trunnghieu.tplogistic.ui.screens.job.JobActivity
import com.trunnghieu.tplogistic.ui.screens.login.language.LanguagePopupMenu
import com.trunnghieu.tplogistic.ui.screens.reset_password.ResetPasswordActivity
import com.trunnghieu.tplogistic.utils.constants.TPLogisticsConst
import com.trunnghieu.tplogistic.utils.helper.AppPreferences
import com.trunnghieu.tplogistic.utils.helper.LocaleHelper

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>(), LoginUV {

    override fun layoutRes() = R.layout.activity_login

    override fun viewModelClass(): Class<LoginVM> {
        return LoginVM::class.java
    }

    override fun initViewModel(viewModel: LoginVM) {
        viewModel.run {
            init(this@LoginActivity)
            initLifeCycle(this@LoginActivity)
            binding.vm = this
        }
    }

    // Color state list
    private lateinit var normalStrokeColor: ColorStateList
    private lateinit var errorStrokeColor: ColorStateList

    override fun initView() {
        binding.inputPassword.edittext.transformationMethod = PasswordTransformationMethod()
    }

    override fun initData() {
        normalStrokeColor = ContextCompat.getColorStateList(context, R.color.til_stroke_color)!!
        errorStrokeColor =
            ContextCompat.getColorStateList(context, R.color.til_error_stroke_color)!!
    }

    override fun initAction() {
        viewModel.run {
            phoneNumber.observe(this@LoginActivity) {
                if (it.isNotEmpty()) {
                    binding.inputPhone.textInputLayout.setBoxStrokeColorStateList(normalStrokeColor)
                }
            }

            password.observe(this@LoginActivity) {
                if (it.isNotEmpty()) {
                    binding.inputPassword.textInputLayout.setBoxStrokeColorStateList(
                        normalStrokeColor
                    )
                }
            }

            checkAutoLogin()
        }
    }

    override fun askPermissions(permissions: List<String>) {
        requestPermission(
            getString(R.string.permission_msg),
            permissions,
            object : RequestPermissionCallback {
                override fun onPermissionGranted() {
                    viewModel.requestLocationPermission()
                }

                override fun onPermissionDenied() {
                    finish()
                }
            }
        )
    }

    override fun askLocationPermissions(locationPermissions: List<String>) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.continueLoginFlow()
            return
        }

        val locationDisclosureBinding = DialogLocationDisclosureBinding.inflate(
            LayoutInflater.from(context), null, true
        )
        val disclosureDialog = AppFunctionDialog.get().showCustomLayout(
            context, locationDisclosureBinding, 0.9
        )

        locationDisclosureBinding.setNextOnClick {
            disclosureDialog.dismiss()

            requestPermission(
                getString(R.string.permission_msg),
                locationPermissions,
                object : RequestPermissionCallback {
                    override fun onPermissionGranted() {
                        viewModel.continueLoginFlow()
                    }

                    override fun onPermissionDenied() {
                        finish()
                    }
                }
            )
        }
    }

    override fun goToResetPassword() {
        navigateTo(ResetPasswordActivity::class.java)
    }

    override fun goToMain() {
        navigateTo(JobActivity::class.java, true)
    }

    override fun resetValidate() {
        viewModel.errorMessage.value = ""
        binding.apply {
            inputPhone.textInputLayout.setBoxStrokeColorStateList(normalStrokeColor)
            inputPassword.textInputLayout.setBoxStrokeColorStateList(normalStrokeColor)
        }
    }

    override fun phoneNumberAndPasswordIsEmpty() {
        viewModel.errorMessage.value =
            getString(R.string.login_err_phone_number_password_is_empty)
        binding.apply {
            inputPhone.textInputLayout.setBoxStrokeColorStateList(errorStrokeColor)
            inputPassword.textInputLayout.setBoxStrokeColorStateList(errorStrokeColor)
            inputPhone.edittext.requestFocus()
        }
    }

    override fun phoneNumberIsEmpty() {
        viewModel.errorMessage.value =
            getString(R.string.login_err_phone_number_password_is_empty)
        binding.apply {
            inputPhone.textInputLayout.setBoxStrokeColorStateList(errorStrokeColor)
            inputPhone.edittext.requestFocus()
        }
    }

    override fun passwordIsEmpty() {
        viewModel.errorMessage.value =
            getString(R.string.login_err_phone_number_password_is_empty)
        binding.apply {
            inputPassword.textInputLayout.setBoxStrokeColorStateList(errorStrokeColor)
            inputPassword.edittext.requestFocus()
        }
    }

    override fun invalidLoginInfo() {
        showMessage(context.getString(R.string.login_err_invalid_login_info))
    }

    override fun connectionError() {
        showMessage(context.getString(R.string.alert_msg_connection_error))
    }

    override fun loadDefaultAppLanguage(language: TPLogisticsConst.AppLanguage) {
        val imgLanguage = ContextCompat.getDrawable(
            context,
            when (language) {
                TPLogisticsConst.AppLanguage.ENGLISH -> R.drawable.img_lang_en
                TPLogisticsConst.AppLanguage.VIETNAMESE -> R.drawable.img_lang_vn
            }
        )
        binding.imgLanguage.setImageDrawable(imgLanguage)
    }

    override fun showLanguagePopup() {
        binding.imgSelectLanguageArrow.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_arrow_down_sel
            )
        )
        LanguagePopupMenu(context, binding.viewSelectLanguage).show(
            currentLanguage = AppPreferences.get().getString(
                LocaleHelper.SELECTED_LANGUAGE,
                TPLogisticsConst.AppLanguage.ENGLISH.code
            ),
            popupDismissCallback = {
                binding.imgSelectLanguageArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_arrow_down_nor
                    )
                )
            },
            languageSelectedCallback = {
                LocaleHelper.setLocale(context, it.languageEnum.code)
                recreate()
            }
        )
    }

    override fun onDestroy() {
        AppLoadingDialog.get().dismiss()
        super.onDestroy()
    }
}
