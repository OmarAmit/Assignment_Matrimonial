package assignment.shaadi.repository

import com.betaout.GOQii.network.ApiService
import com.betaout.GOQii.network.NetworkConstants
import com.betaout.GOQii.network.RetrofitService
import com.betaout.GOQii.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import assignment.shaadi.database.AppDatabase
import assignment.shaadi.database.entity.ProfileEntity
import assignment.shaadi.util.Utility

object UserProfileRepository {
    private var apiService: ApiService = RetrofitService.createService(ApiService::class.java)

    private const val NO_INTERNET_TOAST = "No Internet Connection..."

    internal suspend fun getProfile(appDatabase: AppDatabase): Flow<DataState<List<ProfileEntity>>> = flow {
        emit(DataState.Loading)
        try {
            val profilesDao = appDatabase.getProfileDao()
            var profileList = profilesDao.getAllProfiles()

            if (profileList.isNullOrEmpty() && !Utility.isNetworkConnected) {
                emit(DataState.Failure(NO_INTERNET_TOAST))
            } else if ( Utility.isNetworkConnected) {
                val profileResponse = apiService.fetchProfiles()
                val listProfiles = profileResponse.listProfiles
                if (!listProfiles.isNullOrEmpty()) {
                    profileList = Utility.modelToEntityMapper(listProfiles)
                    saveProfilesOffline(appDatabase, profileList)
                    profileList = profilesDao.getAllProfiles()
                    emit(DataState.Success(profileList))
                } else {
                    emit(DataState.Failure(NetworkConstants.FAILURE_REASON))
                }
            } else {
                emit(DataState.Success(profileList))
            }
            emit(DataState.NoState)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            e.printStackTrace()
        }
    }

    private suspend fun saveProfilesOffline(
            appDatabase: AppDatabase,
            listProfileEntities: List<ProfileEntity>
    ) {
        val profilesDao = appDatabase.getProfileDao()
        profilesDao.insertAllProfiles(listProfileEntities)
    }
}