package com.iserveu.learningproject

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.iserveu.learningproject.databinding.ActivityBookingBinding


class BookingActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding : ActivityBookingBinding
    private var layout : ViewGroup?=null
    private var seatViewList = arrayListOf<TextView>()
    companion object{
        const val seatGaping = 7
        const val seatSize = 70
        var selectedIds = ""

        const val seats1 =
                ("/_UUUUUUAAAAARRRR_/"
                + "_________________/"
                + "UU__AAAARRRRR__RR/"
                + "UU__UUUAAAAAA__AA/"
                + "AA__AAAAAAAAA__AA/"
                + "AA__AARUUUURR__AA/"
                + "UU__UUUA_RRRR__AA/"
                + "AA__AAAA_RRAA__UU/"
                + "AA__AARR_UUUU__RR/"
                + "AA__UUAA_UURR__RR/"
                + "_________________/"
                + "UU_AAAAAAAUUUU_RR/"
                + "RR_AAAAAAAAAAA_AA/"
                + "AA_UUAAAAAUUUU_AA/"
                + "AA_AAAAAAUUUUU_AA/"
                + "_________________/")

        const val seats =
                    ("/AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAARRRA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAUUAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAUUUAAA_________________AAUUUAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"_________________________________/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AAAAAAAA/"
                    +"AAAAAAAA_________________AUAAAAAA/"
                    +"AAAAAAAA_________________AUAAAAAA/"
                    +"AUUUUUUA_________________AUAAAAAA/"
                    +"AAAAUAAA_________________AUAAAAAA/"
                    +"AAAAUAAA_________________AUUAAAAA/"
                    +"AAAAUAAA_________________AAUAAAAA/"
                    +"AAAAUAAA_________________AAUAAAAA/"
                    +"AAAAAAAA_________________AAUUAAAA/"
                    +"AAAAAUAA_________________AAAUAAAA/"
                    +"AAAAAUAA_________________AAAUAAAA/"
                    +"AAAAAUAA_________________AAAUAAAA/"
                    +"AAAAAUAA_________________AAAUAAAA/"
                    +"AAAAAUAA_________________AAAAAAAA/"
                    +"AAAAAAA_UUUUUUUU_UUUUUUUU_AAAAAAA/"
                    +"AAAAAAA_RRRRRRRR_RRRRRRRR_UUUUUUU/"
                    +"AAAAAA_AAAAAAAAA_AAAAAAAAA_AAAAAA/"
                    +"AAAAAA_AAAAAAAAA_AAAAAAAAA_AAAAAA/"
                    +"AAAAA_AAAAAAAAAA_AAAAAAAAAA_AAAAA/"
                    +"AAAAA_AAAAAAAAAA_AAAAAAAAAA_AAAAA/"
                    +"AAAA_AAAAAAAAAAA_AAAAAAAAAAA_AAAA/"
                    +"AAAA_AAAAAAAAAAA_AAAAAAAAAAA_AAAA/"
                    +"AAA_AAAAAAAAAAAA_AAAAAAAAAAAA_AAA/"
                    +"AAA_AAAAAAAAAAAA_AAAAAAAAAAAA_AAA/"
                    +"AA_AAAAAAAAAAAAA_AAAAAAAAAAAAA_AA/"
                    +"AA_AAAAAAAAAAAAA_AAAAAAAAAAAAA_AA/"
                    +"A_AAAAAAAAAAAAAA_AAAAAAAAAAAAAA_A/"
                    +"A_AAAAAAAAAAAAAA_AAAAAAAAAAAAAA_A/"
                    +"AAAAAAAAAAAAAAAA_AAAAAAAAAAAAAAAA/"
                    +"AAAAAAAAAAAAAAAA_AAAAAAAAAAAAAAAA/"
                    )

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_booking)

        layout = binding.layoutSeat

        val layoutSeat = LinearLayout(this)
        layoutSeat.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutSeat.orientation = LinearLayout.VERTICAL
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping)
        (layout as HorizontalScrollView).addView(layoutSeat)

        var linearLayout:LinearLayout ?= null
        var count = 0

        for (element in seats){
            when(element){
                '/'->{
                    linearLayout = LinearLayout(this)
                    linearLayout.orientation = LinearLayout.HORIZONTAL
                    layoutSeat.addView(linearLayout)
                }
                'U'->{
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.setTextColor(Color.BLACK)
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seats_booked)
                    view.tag = Status.BOOKED
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                    view.setOnClickListener(this)
                }
                'A'->{
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_available)
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    view.setTextColor(Color.BLACK)
                    view.tag = Status.AVAILABLE
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                    view.setOnClickListener (this)
                }
                'R'->{
                    count++
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setPadding(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.id = count
                    view.gravity = Gravity.CENTER
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = count.toString()
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                    view.setTextColor(Color.BLACK)
                    view.tag = Status.RESERVED
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                    view.setOnClickListener(this)
                }
                '_'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.setBackgroundColor(Color.TRANSPARENT)
                    view.text = ""
                    linearLayout?.addView(view)
                }
                'S'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "S"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
                'T'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "T"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
                'a'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "A"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
                'G'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "G"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
                'E'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "E"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
                '3'->{
                    val view = TextView(this)
                    val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                    view.layoutParams = layoutParams
                    view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_reserved)
                    view.text = "3"
                    view.gravity = Gravity.CENTER
                    linearLayout?.addView(view)
                    seatViewList.add(view)
                }
            }
        }

    }

    override fun onClick(view: View?) {
        if (view!!.tag == Status.AVAILABLE) {
            if (selectedIds.contains(view.id.toString() + ",")) {
                selectedIds = selectedIds.replace(view.id.toString() + ",", "")
                view.background = ContextCompat.getDrawable(this,R.drawable.ic_seat_available)
            } else {
                selectedIds = selectedIds + view.id.toString() + ","
                view.setBackgroundResource(R.drawable.ic_seat_book)
            }
        } else if (view.tag == Status.BOOKED) {
            Toast.makeText(this, "Seat " + view.id.toString() + " is Booked", Toast.LENGTH_SHORT)
                .show()
        } else if (view.tag == Status.RESERVED) {
            Toast.makeText(
                this,
                "Seat " + view.id.toString() + " is Reserved",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    enum class Status{
        AVAILABLE,
        BOOKED,
        RESERVED
    }
}
