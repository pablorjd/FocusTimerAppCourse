package dev.pablorjd.focustimer.domain.usecase

import dev.pablorjd.focustimer.domain.model.Resource
import dev.pablorjd.focustimer.domain.model.TimerSessionModel
import dev.pablorjd.focustimer.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTimerSessionByDateUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {

    operator fun invoke(date: String): Flow<Resource<TimerSessionModel>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    data = repository.getTimerSessionByDate(date)
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unknown Error!"))
        }
    }

}