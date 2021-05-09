package com.keller.yourpet.shared.common.usecase

import com.keller.yourpet.shared.CFlow
import com.keller.yourpet.shared.wrap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowableUseCase<in TParam, out TResult>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    sealed class Result<out TResultModel> {
        data class Success<out TResultModel>(val result: TResultModel) : Result<TResultModel>()

        data class Failure<out TResultModel>(val error: Throwable? = null) : Result<TResultModel>()
    }

    suspend operator fun invoke(param: TParam): CFlow<Result<TResult>> =
        performAction(param)
            .map { Result.Success(it) as Result<TResult> }
            .catch { emit(Result.Failure(it)) }
            .flowOn(dispatcher)
            .wrap()

    protected abstract suspend fun performAction(param: TParam): Flow<TResult>
}

suspend operator fun <R> FlowableUseCase<Unit, R>.invoke() = this(Unit)
