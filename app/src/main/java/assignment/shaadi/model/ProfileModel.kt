package assignment.shaadi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileModel(
        @Expose
        @SerializedName("results")
        val listProfiles: List<ProfileData>
)
data class ProfileData(
        val gender: String,
        val name: Name,
        val location: Location,
        val dob: DOB,
        val picture: Picture
)

data class Name(
        val title: String,
        val first: String,
        val last: String
)

data class Location(
        val city: String,
        val state: String,
        val country: String,
        val postCode: Int
)

data class DOB(
        val age: Int
)

data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
)