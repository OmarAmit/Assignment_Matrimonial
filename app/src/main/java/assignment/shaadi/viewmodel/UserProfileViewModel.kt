package assignment.shaadi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betaout.GOQii.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import assignment.shaadi.repository.UserProfileRepository
import assignment.shaadi.database.AppDatabase
import assignment.shaadi.database.entity.ProfileEntity

class UserProfileViewModel : ViewModel() {
    private val mDataStateProfiles: MutableLiveData<DataState<List<ProfileEntity>>> =
            MutableLiveData()

    val dataStateProfiles: LiveData<DataState<List<ProfileEntity>>>
        get() = mDataStateProfiles

    @ExperimentalCoroutinesApi
    internal fun fetchProfiles(appDatabase: AppDatabase) {
        viewModelScope.launch {
            UserProfileRepository.getProfile(appDatabase)
                    .onEach { dataStateProfiles ->
                        mDataStateProfiles.value = dataStateProfiles
                    }
                    .launchIn(viewModelScope)
        }
    }
}