package com.example.todoapp.util

/**
 * Created by Yasuhiro Suzuki on 2017/08/22.
 */

sealed class ValidateResult

data class Error(val throwable: Throwable) : ValidateResult()
object OK : ValidateResult()