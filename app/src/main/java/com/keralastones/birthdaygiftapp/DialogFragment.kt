package com.keralastones.birthdaygiftapp

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_dialog.dialogImage
import kotlinx.android.synthetic.main.fragment_dialog.passwordInput
import kotlinx.android.synthetic.main.fragment_dialog.passwordSubmitBtn
import java.util.*
import com.bumptech.glide.request.RequestOptions




class FragmentDialog : DialogFragment() {

    var clueNo: Int = 0
    var failedCount = 0
    var owner = "Abhi"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        clueNo = arguments.getInt("CLUENO")
        owner = arguments.getString("OWNER")
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        failedCount = 0
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (owner.equals("abhi", ignoreCase = true)) {
            handleAbhis(view)
        } else {
            handleDavis(view)
        }
    }

    private fun handleAbhis(view: View?) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.bdayplaceholder)
        requestOptions.error(R.drawable.bdayplaceholder)
        Glide.with(context).apply { requestOptions }.load(images[clueNo]).into(dialogImage)
        passwordSubmitBtn?.setOnClickListener({
            val entered = passwordInput.text.toString()
            if (!TextUtils.isEmpty(entered) && entered.contains(passwords[clueNo], true)) {
                dismiss()
                (activity as MainActivity).navigateToFragment(
                        ClueFragment.newInstance(clues[clueNo], images[clueNo], clueNo, clueNo == cluesMax - 1, owner))
            } else {
                passwordInput.text.clear()
                if (failedCount < passwordHintsMax[clueNo]) {
                    failedCount++
                } else {
                    failedCount = passwordHintsMax[clueNo]
                }
                passwordInput.hint = passwordsHint[clueNo][failedCount - 1]
            }
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })
    }

    private fun handleDavis(view: View?) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.bdayplaceholder)
        requestOptions.error(R.drawable.bdayplaceholder)
        Glide.with(context).apply{requestOptions}.load(dImages[clueNo]).into(dialogImage)
        passwordSubmitBtn?.setOnClickListener({
            val entered = passwordInput.text.toString()
            if (!TextUtils.isEmpty(entered) && entered.contains(dPasswords[clueNo], true)) {
                dismiss()
                (activity as MainActivity).navigateToFragment(
                        ClueFragment.newInstance(dClues[clueNo], dImages[clueNo], clueNo, clueNo == dCluesMax - 1, owner))
            } else {
                passwordInput.text.clear()
                if (failedCount < dPasswordHintsMax[clueNo]) {
                    failedCount++
                } else {
                    failedCount = dPasswordHintsMax[clueNo]
                }
                passwordInput.hint = dPasswordsHint[clueNo][failedCount - 1]
            }
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })
    }

    companion object {

        //Abhi's

        val clues = Arrays.asList("Its in your house", "Watch the watch", "Check the can", "Ask me")

        val passwords = Arrays.asList("dona", "ajitha", "sreevidya", "mehak")

        val images = Arrays.asList("https://dl.dropboxusercontent.com/s/nfsszsb3pmvhugs/keerunimmz.jpg",
                "http://www.scrapsplanet.com/content/birthday/birthday-18.gif",
                "https://dl.dropboxusercontent.com/s/6c0rt2bcjn4lsgu/keerufriends.jpg",
                "http://bestanimations.com/Holidays/Birthday/happy-birthday-animated-gift-flowers-illustration-greeting-card-hipster-cute-gif.gif")

        val passwordsHint = arrayOf(arrayOf("Your usual one", "JNV", "Your best friend", "Ask abhi"),
                arrayOf("A woman", "First name", "My blood", "Ask me"),
                arrayOf("Follow the pattern", "Amma ", "Your blood", "Ask Davi"),
                arrayOf("Friend's first name", "Bangalore ", "Go North", "Ask Mehak"))

        val passwordHintsMax = intArrayOf(4, 4, 4, 4)
        val cluesMax = 4


        //Davi's

        val dClues = Arrays.asList("Inside a white thing", "Outside house", "Back of a moving thing", "Ask amma")

        val dPasswords = Arrays.asList("volleyball", "gec", "andaman", "abhi")

        val dImages = Arrays.asList("https://dl.dropboxusercontent.com/s/kgfkwwh9xmfoylv/davi.jpg",
                "https://dl.dropboxusercontent.com/s/gokddbxiqhn10gu/pk.jpg",
                "https://dl.dropboxusercontent.com/s/djrwnlasjkuz0xp/kannada.jpg",
                "http://bestanimations.com/Holidays/Birthday/beautiful-disney-happy-birthday-cake-wishes-pink-animated-gif.gif")

        val dPasswordsHint = arrayOf(arrayOf("Ur fav game", "Clusters", "Ball game", "Ask abhi"),
                arrayOf("A college", "U studied here", "3 letters", "Ask davi"),
                arrayOf("An island", "U went there ", "7 letters", "Ask Abhi"),
                arrayOf("Ur best friend", "Now in Bangalore", "He made this", "Ask davi"))

        val dPasswordHintsMax = intArrayOf(4, 4, 4, 4)
        val dCluesMax = 4

        fun newInstance(clueNo: Int, owner: String): FragmentDialog {
            var fragmentDialog = FragmentDialog()
            var argumentBundle = Bundle()
            argumentBundle.putInt("CLUENO", clueNo)
            argumentBundle.putString("OWNER", owner)
            fragmentDialog.arguments = argumentBundle
            return fragmentDialog
        }
    }

}