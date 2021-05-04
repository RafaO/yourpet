package com.keller.yourpet.shared.common.usecase

import com.keller.yourpet.shared.CFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowableUseCase<in TParam, out TResult>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    sealed class Result<out TResultModel> {
        data class Success<out TResultModel>(val result: TResultModel) : Result<TResultModel>()

        data class Failure(val error: Throwable? = null) : Result<Nothing>()
    }

    suspend operator fun invoke(param: TParam) =
        performAction(param)
            .catch { exception ->
                emit(Result.Failure(exception))
            }
            .flowOn(dispatcher)

    protected abstract suspend fun performAction(param: TParam): CFlow<Result<TResult>>
}

suspend operator fun <R> FlowableUseCase<Unit, R>.invoke() = this(Unit)
