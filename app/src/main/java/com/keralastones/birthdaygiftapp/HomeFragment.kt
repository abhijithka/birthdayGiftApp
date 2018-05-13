package com.keralastones.birthdaygiftapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.abhiGiftBtn
import kotlinx.android.synthetic.main.fragment_home.bdayWishImageView
import kotlinx.android.synthetic.main.fragment_home.daviGiftBtn

/**
 * Created by aappukuttan on 05/05/18.
 */

class HomeFragment: Fragment() {

    val url = "http://bestanimations.com/Holidays/Birthday/else-frozen-makes-magic-happy-birthday-animated-greeting-card-gif.gif#.Wt4bI0rlv3U.link"

    companion object {

        fun newInstance() : HomeFragment{
            var argumentBundle = Bundle()
            var homeFragment = HomeFragment()
            homeFragment.arguments = argumentBundle
            return homeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        abhiGiftBtn.setOnClickListener({
            FragmentDialog.newInstance(0, "abhi").show(fragmentManager, "")
        })
        daviGiftBtn.setOnClickListener({
            FragmentDialog.newInstance(0, "davi").show(fragmentManager, "")
        })
        Glide.with(context).load(url).into(bdayWishImageView)
    }
}