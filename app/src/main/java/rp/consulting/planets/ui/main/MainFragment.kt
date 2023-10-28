package rp.consulting.planets.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rp.consulting.planets.R

class MainFragment : Fragment() {

    private lateinit var viewModel: PlanetListViewModel
    private val adapter = PlanetsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlanetListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.list)
        val loading = view.findViewById<ProgressBar>(R.id.loading)
        val error = view.findViewById<ImageView>(R.id.error)
        list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.viewState.observe(this){ state ->
            when (state) {
                is State.Content -> {
                    adapter.setData(state.list)
                    list.adapter = adapter
                    loading.isVisible = false
                    list.isVisible = true
                    error.isVisible = false
                }
                State.Error -> {
                    loading.isVisible = false
                    list.isVisible = false
                    error.isVisible = true
                }
                State.Loading -> {
                    loading.isVisible = true
                    list.isVisible = false
                    error.isVisible = false
                }
            }
        }
        viewModel.loadData()
    }
}