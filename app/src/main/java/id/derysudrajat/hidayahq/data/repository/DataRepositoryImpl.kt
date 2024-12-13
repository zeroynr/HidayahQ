package id.derysudrajat.hidayahq.data.repository

import id.derysudrajat.hidayahq.data.model.Ayah
import id.derysudrajat.hidayahq.data.model.PrayerReminder
import id.derysudrajat.hidayahq.data.model.ProgressTask
import id.derysudrajat.hidayahq.data.model.Schedule
import id.derysudrajat.hidayahq.repo.States
import kotlinx.coroutines.flow.Flow

interface DataRepositoryImpl {
    suspend fun getSchedule(lat: Double, long: Double, month: Int, year: Int): Flow<States<List<Schedule>>>

    suspend fun getAllReminder(): Flow<List<PrayerReminder>>
    suspend fun addAllReminders(listOfReminder: List<PrayerReminder>)
    suspend fun updateReminder(prayerReminder: PrayerReminder)
    suspend fun deleteAllReminder()

    suspend fun getProgressTask(date: String): Flow<List<ProgressTask>>
    suspend fun addProgressTask(task: ProgressTask)
    suspend fun deleteProgressTask(task: ProgressTask)

    suspend fun updateCheckedTask(task: ProgressTask, onFinish: () -> Unit)

    suspend fun getAyahQuran(noSurah: Int): Flow<States<List<Ayah>>>
}