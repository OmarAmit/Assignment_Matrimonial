package assignment.shaadi.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import assignment.shaadi.database.RoomConstants

@Entity(tableName = RoomConstants.ENTITY_PROFILE)
data class ProfileEntity(
        @ColumnInfo(name = RoomConstants.COL_GENDER)
        var gender: String = "",

        @ColumnInfo(name = RoomConstants.COL_NAME_TITLE)
        var nameTitle: String = "",

        @ColumnInfo(name = RoomConstants.COL_FIRST_NAME)
        var firstName: String = "",

        @ColumnInfo(name = RoomConstants.COL_LAST_NAME)
        var lastName: String = "",

        @ColumnInfo(name = RoomConstants.COL_CITY)
        var city: String = "",

        @ColumnInfo(name = RoomConstants.COL_STATE)
        var state: String = "",

        @ColumnInfo(name = RoomConstants.COL_COUNTRY)
        var country: String = "",

        @ColumnInfo(name = RoomConstants.COL_POSTAL_CODE)
        var postalCode: Int = 0,

        @ColumnInfo(name = RoomConstants.COL_AGE)
        var age: Int = 0,

        @ColumnInfo(name = RoomConstants.COL_THUMBNAIL)
        var thumbnail: String = "",

        @ColumnInfo(name = RoomConstants.COL_IMAGE_MED)
        var imageMedium: String = "",

        @ColumnInfo(name = RoomConstants.COL_IMAGE_LARGE)
        var imageLarge: String = "",

        @ColumnInfo(name = RoomConstants.COL_SELECTION_STATUS)
        var selectionStatus: Int = 0,

        @ColumnInfo(name = RoomConstants.COL_CREATED_TIME)
        var createdTime: Long = 0L
) {
    @ColumnInfo(name = RoomConstants.COL_ID)
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}