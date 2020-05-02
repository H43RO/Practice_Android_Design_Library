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
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.raywenderlich.isell.R
import com.raywenderlich.isell.data.Category
import com.raywenderlich.isell.data.Item
import com.raywenderlich.isell.util.DataProvider
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity(),
    TextWatcher, AdapterView.OnItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_item)

    categorySpinner.adapter = ArrayAdapter<Category>(
        this,
        android.R.layout.simple_spinner_item,
        Category.values()
    )
    categorySpinner.onItemSelectedListener = this

    titleEditText.addTextChangedListener(this)
    priceEditText.addTextChangedListener(this)
    detailsEditText.addTextChangedListener(this)
  }

  /**
   * Handles Back button press, relaunch MainActivity for reload data
   */
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        navigateBackToItemList()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun afterTextChanged(s: Editable?) {}

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

  override fun onNothingSelected(parent: AdapterView<*>?) {}

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    imageButton.setImageResource(imageFromCategory(categorySpinner.selectedItem as Category))
  }

  fun onClickAddItem(view: View) {
    val selectedCategory = categorySpinner.selectedItem as Category
    DataProvider.addItem(Item(
        imageId = imageFromCategory(selectedCategory),
        title = titleEditText.text.toString(),
        details = detailsEditText.text.toString(),
        price = priceEditText.text.toString().toDouble(),
        category = selectedCategory,
        postedOn = System.currentTimeMillis())
    )
  }

  /**
   * Returns image of an item Category
   */
  private fun imageFromCategory(category: Category): Int {
    return R.drawable.laptop_1
  }

  /**
   * Navigates to MainActivity and reloads data
   */
  private fun navigateBackToItemList() {
    val mainIntent = Intent(this, MainActivity::class.java)
    mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(mainIntent)
    finish()
  }

}
