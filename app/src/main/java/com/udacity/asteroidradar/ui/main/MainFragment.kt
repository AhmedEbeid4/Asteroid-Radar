package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.models.Asteroid


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, Factory(activity.application)).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

//        viewModel.pictureOfDay.observe(viewLifecycleOwner, Observer {image->
//            if(image != null){
//                println("url : "+image.url)
//                val imgUrl=image.url
//                imgUrl.let {
//                    Glide.with(activity?.applicationContext!!)
//                        .load(imgUrl)
//                        .apply(
//                            RequestOptions()
//                                .placeholder(R.drawable.loading_animation)
//                                .error(R.drawable.ic_broken_image))
//                        .into(binding.activityMainImageOfTheDay)
//                }
//            }
//        })

        binding.asteroidRecycler.adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener { index->
            goToDetailFragment(viewModel.asteroidList.value!![index])
        })

        setHasOptionsMenu(true)

        return binding.root
    }
    private fun goToDetailFragment(asteroid: Asteroid){
        val action=MainFragmentDirections.actionShowDetail(asteroid)
        findNavController().navigate(action)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.week -> viewModel.weekList()
            R.id.today -> viewModel.todayList()
            R.id.all -> viewModel.savedList()
        }
        return true
    }
}