package com.example.efab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var scrollDown = false

    private val data = List(33) { i -> "$i element" }

    val adapter: CustomAdapter = CustomAdapter(data)

    private lateinit var fab: FloatingActionButton

    private lateinit var efab: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        efab = findViewById(R.id.efab)

        findViewById<RecyclerView>(R.id.recycler_view)
            .apply {
                adapter = this@MainActivity.adapter
                addOnScrollListener(
                    object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            val scrollDown = dy > 0
                            if (this@MainActivity.scrollDown != scrollDown) {
                                this@MainActivity.scrollDown = scrollDown
                                onScroll()
                            }
                        }
                    }
                )
            }
    }

    fun onScroll() {
        if (scrollDown) {
            fab.hide()
            //Workaround to return animation for EFAB
/*            val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
            efab.startAnimation(hideAnimation)*/
            efab.hide()
        } else {
            fab.show()
            //Workaround to return animation for EFAB
/*            val showAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
            efab.startAnimation(showAnimation)*/
            efab.show()
        }
    }

    fun toast(string: String) {
        Toast.makeText(this, string, LENGTH_SHORT).show()
    }


    class CustomAdapter(private val values: List<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun getItemCount() = values.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list, parent, false
            )
        )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = values[position]
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.text)
        }
    }
}