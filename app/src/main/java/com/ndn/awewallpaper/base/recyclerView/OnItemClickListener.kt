package com.ndn.awewallpaper.base.recyclerView

import com.ndn.awewallpaper.utils.Constants.POSITION_DEFAULT

/**
 * OnItemClickListener
 *
 * @param <T> Data from item click
</T> */

interface OnItemClickListener<T> {
  fun onItemViewClick(item: T, position: Int = POSITION_DEFAULT)
}