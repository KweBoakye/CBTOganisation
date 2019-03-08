package com.fyp.kweku.cbtoganisation.common.presentation

interface BaseView<out T : BasePresenter<*>> {

    val presenter: T

}