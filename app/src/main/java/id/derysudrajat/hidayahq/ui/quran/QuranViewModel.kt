package id.derysudrajat.hidayahq.ui.quran

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import id.derysudrajat.hidayahq.R
import id.derysudrajat.hidayahq.compose.state.QuranUiState
import id.derysudrajat.hidayahq.repo.PrayerRepository
import id.derysudrajat.hidayahq.repo.remote.response.ListJuzResponse
import id.derysudrajat.hidayahq.repo.remote.response.ListSurahResponse
import id.derysudrajat.hidayahq.repo.remote.response.toListJuz
import id.derysudrajat.hidayahq.repo.remote.response.toListSurah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val repository: PrayerRepository
) : ViewModel() {

    private val currentUiState = QuranUiState()
    private val _uiState = MutableStateFlow(currentUiState)
    val uiState = _uiState

    fun setBack(onBack: () -> Unit) {
        currentUiState.onBack = onBack
        _uiState.value = currentUiState
    }

    fun getListSurah(activity: Activity) {
        viewModelScope.launch {
            val json = InputStreamReader(activity.resources.openRawResource(R.raw.surah))
            val response = Gson().fromJson(json, ListSurahResponse::class.java)
            response.listSurahResponse?.let {
                currentUiState.listSurah = it.toListSurah()
                _uiState.value = currentUiState
            }
        }
    }

    fun getListJuz(activity: Activity) {
        viewModelScope.launch {
            val json = InputStreamReader(activity.resources.openRawResource(R.raw.juz))
            val response = Gson().fromJson(json, ListJuzResponse::class.java)
            response.data?.let {
                currentUiState.listJuz = it.toListJuz()
                _uiState.value = currentUiState
            }
        }
    }
}