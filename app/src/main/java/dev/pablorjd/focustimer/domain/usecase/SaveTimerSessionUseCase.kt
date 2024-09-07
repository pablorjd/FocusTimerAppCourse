package dev.pablorjd.focustimer.domain.usecase

import dev.pablorjd.focustimer.domain.model.Resource
import dev.pablorjd.focustimer.domain.model.TimerSessionModel
import dev.pablorjd.focustimer.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveTimerSessionUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {
    operator fun invoke(timerSessionModel: TimerSessionModel): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(
                data = repository.saveTimerSession(timerSessionModel)
            ))
        }catch (e: Exception) {
            emit(Resource.Error(e.message?: "Unknown Error!"))
        }
    }
}