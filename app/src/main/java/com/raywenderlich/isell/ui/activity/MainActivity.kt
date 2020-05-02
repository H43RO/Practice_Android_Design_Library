/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.isell.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.raywenderlich.isell.R
import com.raywenderlich.isell.adapter.ItemsAdapter
import com.raywenderlich.isell.data.Category
import com.raywenderlich.isell.data.Item
import com.raywenderlich.isell.util.DataProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener , BottomNavigationView.OnNavigationItemSelectedListener{

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_laptops -> populateItemList(Category.LAPTOP)
            R.id.nav_monitores -> populateItemList(Category.MONITOR)
            R.id.nav_headphones -> populateItemList(Category.HEADPHONE)
            else -> return false
        }
        return true
    }

    override fun onItemClick(item: Item, itemView: View) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra(getString(R.string.bundle_extra_item), item)
        startActivity(detailsIntent)
    }

    private fun populateItemList(category: Category) {
        val items = when (category) {
            Category.LAPTOP -> DataProvider.laptopList
            Category.MONITOR -> DataProvider.monitorList
            Category.HEADPHONE -> DataProvider.headphoneList
        }

        if (items.isNotEmpty()) {
            item_recycler_view.adapter = ItemsAdapter(items, this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateItemList(Category.LAPTOP)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }
}
