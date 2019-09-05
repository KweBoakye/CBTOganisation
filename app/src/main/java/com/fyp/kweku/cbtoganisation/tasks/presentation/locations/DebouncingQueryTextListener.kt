package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

class DebouncingQueryTextListener( lifecycle: Lifecycle, private val  onDebouncingQueryTextChange: (String?) -> Unit):
SearchView.OnQueryTextListener, LifecycleObserver{

    init {
        lifecycle.addObserver(this)
    }

    var debounceTime: Long = 250

    private var searchJob: Job? = null

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let { 
                delay(debounceTime)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
    
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        searchJob?.cancel()
    }
    
}