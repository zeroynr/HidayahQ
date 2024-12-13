package id.derysudrajat.hidayahq.compose.state

import id.derysudrajat.hidayahq.data.model.Juz
import id.derysudrajat.hidayahq.data.model.Surah

data class QuranUiState(
    var listSurah: List<Surah> = emptyList(),
    var listJuz: List<Juz> = emptyList(),
    var onBack: () -> Unit = {}
)