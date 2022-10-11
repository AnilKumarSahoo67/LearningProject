package com.iserveu.learningproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.iserveu.learningproject.BookingActivity.Companion.seatGaping
import com.iserveu.learningproject.BookingActivity.Companion.seatSize
import com.iserveu.learningproject.BookingActivity.Companion.seats
import com.iserveu.learningproject.databinding.ActivityTestBinding
import kotlin.math.max


class TestActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBinding
    private var layout: ViewGroup? = null
    private var slasCount = 0
    private var underScoreCount = 0
    private var maxUnderScore = 0
//    private val seats =
//        ("/AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/"
//                + "AAAAAAAA_________________AAAAAAAA/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        layout = binding.layoutSeat

        val parentLayout = RelativeLayout(this)
        parentLayout.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentLayout.setPadding(
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping
        )
        (layout as HorizontalScrollView).addView(parentLayout)

        val layoutSeat = LinearLayout(this)
        layoutSeat.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutSeat.orientation = LinearLayout.VERTICAL
        layoutSeat.setPadding(
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping,
            8 * seatGaping
        )
        parentLayout.addView(layoutSeat)


        var linearLayout: LinearLayout? = null
        var count = 0

        for (element in seats) {
            when (element) {
                '/' -> {
                    if (underScoreCount == maxUnderScore || maxUnderScore == 33) {
                        slasCount++
                        maxUnderScore = 0
                    }
                    linearLayout = LinearLayout(this)
                    linearLayout.orientation = LinearLayout.HORIZONTAL
                    layoutSeat.addView(linearLayout)
                }
                'A' -> {
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(
                        seatSize,
                        seatSize
                    )
                    layoutParams.setMargins(
                        seatGaping,
                        seatGaping,
                        seatGaping,
                        seatGaping
                    )
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.background = ContextCompat.getDrawable(this, R.drawable.ic_seat_available)
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    view.setTextColor(Color.BLACK)
                    view.tag = BookingActivity.Status.AVAILABLE
                    linearLayout?.addView(view)
                }
                'U' -> {
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.setTextColor(Color.BLACK)
                    view.background = ContextCompat.getDrawable(this, R.drawable.ic_seats_booked)
                    view.tag = BookingActivity.Status.BOOKED
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    linearLayout?.addView(view)
                }
                'R' -> {
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.background = ContextCompat.getDrawable(this, R.drawable.ic_seat_reserved)
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    view.setTextColor(Color.BLACK)
                    view.tag = BookingActivity.Status.RESERVED
                    linearLayout?.addView(view)
                }
                '_' -> {
                    maxUnderScore++
                    if (slasCount == 1) {
                        underScoreCount++
                    }
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(
                        seatSize,
                        seatSize
                    )
                    layoutParams.setMargins(
                        seatGaping,
                        seatGaping,
                        seatGaping,
                        seatGaping
                    )
                    view.layoutParams = layoutParams
                    view.setBackgroundColor(Color.TRANSPARENT)
                    view.text = ""
                    linearLayout?.addView(view)
                }
            }
        }


        Log.e("count", "/ count: $slasCount" )
        Log.e("count", "_ count: $underScoreCount" )
        val relativeLayout = RelativeLayout(this)
        val relativeParam = RelativeLayout.LayoutParams(underScoreCount * seatSize, (slasCount+2) * seatSize)
        relativeParam.setMargins(12 * seatSize, seatSize, 0, 0)
        relativeLayout.layoutParams = relativeParam
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.setPadding(seatGaping, seatGaping, seatGaping, seatGaping)
        relativeLayout.setBackgroundColor(Color.parseColor("#405467"))


        parentLayout.addView(relativeLayout)


        val imageView = ImageView(this)
        val layoutParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = layoutParam
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setBackgroundResource(R.drawable.dp)

        relativeLayout.addView(imageView)



    }
}