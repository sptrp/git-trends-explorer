package com.ivanponomarev.presentation.info

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.ivanponomarev.gittrends.startpage.databinding.InfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var infoFragmentBinding: InfoFragmentBinding

    private val emailMessage: Spanned by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(EMAIL, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        infoFragmentBinding = InfoFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return infoFragmentBinding.root
    }

    companion object {
        private const val EMAIL = "<a href=\"vankap0n@gmail.com\"></a>"
    }

}