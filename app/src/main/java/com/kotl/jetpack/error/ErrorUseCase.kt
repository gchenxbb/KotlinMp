package com.kotl.jetpack.error

/**
 * Error interface
 */
interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
