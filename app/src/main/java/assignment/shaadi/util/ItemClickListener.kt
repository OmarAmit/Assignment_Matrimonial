package assignment.shaadi.util

import assignment.shaadi.database.entity.ProfileEntity

interface ItemClickListener {
    fun onItemClick(position: Int, item: ProfileEntity)
}