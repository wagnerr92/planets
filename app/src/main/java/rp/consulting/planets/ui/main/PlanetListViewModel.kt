package rp.consulting.planets.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rp.consulting.planets.data.PlanetRepository
import javax.inject.Inject
@HiltViewModel
class PlanetListViewModel @Inject constructor(private val repository: PlanetRepository) : ViewModel() {

    private val state = MutableLiveData<State>()
    val viewState: LiveData<State>
        get() = state

    fun loadData() {
        viewModelScope.launch {
            state.value = State.Loading
            val planets = repository.getPlanetList()
            state.value = State.Content(planets)
        }
    }
}

sealed class State {
    data class Content(val list: List<PlanetData>) : State()
    object Loading : State()

    object Error : State()
}