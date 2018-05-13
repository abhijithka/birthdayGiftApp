package com.keralastones.birthdaygiftapp

import android.app.Fragment
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_clues.clueText
import kotlinx.android.synthetic.main.fragment_clues.homeBtn
import kotlinx.android.synthetic.main.fragment_clues.nextClueBtn

/**
 * Created by aappukuttan on 27/04/18.
 */
class ClueFragment : Fragment() {

    var clue: String = ""
    var backgroundUrl: String = ""
    var clueNo = 0
    var isFinalClue = false
    var owner = "abhi"

    companion object {

        fun newInstance(clueTxt: String, urlForBackground: String = "", clueNo: Int, isFinal: Boolean = false, owner:
        String):
                ClueFragment {
            var argumentBundle = Bundle()
            var clueFragment = ClueFragment()
            argumentBundle.putString("CLUE", clueTxt)
            argumentBundle.putString("URL", urlForBackground)
            argumentBundle.putInt("CLUENO", clueNo)
            argumentBundle.putBoolean("ISFINALCLUE", isFinal)
            argumentBundle.putString("OWNER", owner)
            clueFragment.arguments = argumentBundle
            return clueFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_clues, container, false)
        clue = arguments.getString("CLUE")
        backgroundUrl = arguments.getString("URL")
        clueNo = arguments.getInt("CLUENO")
        isFinalClue = arguments.getBoolean("ISFINALCLUE")
        owner = arguments.getString("OWNER")
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clueText.text = clue
        nextClueBtn.setOnClickListener({
            FragmentDialog.newInstance(++clueNo, owner).show(fragmentManager, "")
        })
        homeBtn.setOnClickListener({
            (activity as MainActivity).navigateToFragment(HomeFragment())
        })
        if (isFinalClue) {
            nextClueBtn.visibility = View.GONE
        }
    }
}