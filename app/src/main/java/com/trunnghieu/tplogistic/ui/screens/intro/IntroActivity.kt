package com.trunnghieu.tplogistic.ui.screens.intro

import android.app.Dialog
import androidx.core.animation.doOnEnd
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.databinding.ActivityIntroBinding
import com.trunnghieu.tplogistic.extensions.navigateTo
import com.trunnghieu.tplogistic.ui.base.activity.BaseActivity
import com.trunnghieu.tplogistic.ui.screens.login.LoginActivity

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroVM>(), IntroUV {
    override fun layoutRes() = R.layout.activity_intro

    override fun viewModelClass(): Class<IntroVM> {
        return IntroVM::class.java
    }

    override fun initViewModel(viewModel: IntroVM) {
        viewModel.run {
            init(this@IntroActivity)
            initLifeCycle(this@IntroActivity)
        }
    }

    private var errorDialog: Dialog? = null

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initAction() {

    }

    override fun checkPlayServices() {
        startIntro()
    }

    override fun stopIntro() {
        binding.animationView.removeAllUpdateListeners()
    }

    private fun startIntro() {
        binding.animationView.run {
            playAnimation()
            addAnimatorUpdateListener { animator ->
                stopIntro()
                animator.doOnEnd {
                    navigateTo(LoginActivity::class.java, true)
                }
            }
        }
    }
}
