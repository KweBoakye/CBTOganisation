package com.fyp.kweku.cbtoganisation.common.presentation

interface BasePresenter<T> {

    fun start()

    var view: T
}