package com.tmdbcodlab.android.ui.moviedetails

import com.tikalk.mobileevent.mobileevent.BasePresenter
import com.tikalk.mobileevent.mobileevent.BaseView

interface MovieDetailsContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}