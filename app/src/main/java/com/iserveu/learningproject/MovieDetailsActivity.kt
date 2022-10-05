package com.iserveu.learningproject

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.iserveu.learningproject.databinding.ActivityMovieDetailsBinding
import java.lang.StrictMath.abs


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private var dialog: AlertDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_details)

//        binding.txtDescription.viewTreeObserver.addOnGlobalLayoutListener(
//            OnGlobalLayoutListener {
//                if (expandable) {
//                    expandable = false
//                    if (binding.txtDescription.lineCount > 4) {
//                        binding.btShowmore.visibility = View.VISIBLE
//                        val animation = ObjectAnimator.ofInt(binding.txtDescription, "maxLines", 4)
//                        animation.setDuration(0).start()
//                    }
//                }
//            })

        val castAdapter = CastProfileAdapter()
        binding.recyclerCast.adapter = castAdapter

        val crewAdapter = CastProfileAdapter()
        binding.recyclerCrew.adapter = crewAdapter

        val movieAdapter = MovieAdapter()
        binding.recyclerAlsoLikePic.adapter = movieAdapter

        val viewPagerAdapter = ViewPagerAdapter()
        binding.viewPagerReviews.adapter = viewPagerAdapter
        binding.viewPagerReviews.setCurrentItem(1,true)

        val offerAdapter = OfferAdapter()
        binding.offerRecyclerView.adapter = offerAdapter

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.offerRecyclerView)


        val nextItemVisiblePx = 80
        val currentItemHorizontalMarginPx = 50
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        binding.viewPagerReviews.setPageTransformer { page, position ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
//            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }

        val itemDecoration = HorizontalMarginItemDecoration()
        binding.viewPagerReviews.addItemDecoration(itemDecoration)


        binding.btnRateNow.setOnClickListener {

            if(dialog!=null){
                if (dialog?.isShowing == true){
                    dialog?.dismiss()
                }
            }
            val popUp = MaterialAlertDialogBuilder(this,R.style.TransparentDialog)
            val view  = layoutInflater.inflate(R.layout.rate_now_dialog,null)
            popUp.setView(view)
            dialog = popUp.create()
            dialog?.setCancelable(false)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.show()

            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerHashTag)
            val adapter = HashTagAdapter()
            recyclerView.adapter = adapter
            val btnCancel = view.findViewById<Button>(R.id.btnCancel)
            btnCancel.setOnClickListener {
                dialog?.dismiss()
            }

        }

    }
}
class HorizontalMarginItemDecoration() :
    RecyclerView.ItemDecoration() {
    private val horizontalMarginInPx: Int = 50

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }
}