package com.khomichenko.database.utils

import app.cash.sqldelight.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.JvmOverloads


@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToOne(
    context: CoroutineContext = Dispatchers.Default,
): Flow<T> = map {
    it.executeAsOne()
}.flowOn(context)

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToOneOrDefault(
    defaultValue: T,
    context: CoroutineContext = Dispatchers.Default,
): Flow<T> = map {
    it.executeAsOneOrNull() ?: defaultValue
}.flowOn(context)

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToOneOrNull(
    context: CoroutineContext = Dispatchers.Default,
): Flow<T?> = map {
    it.executeAsOneOrNull()
}.flowOn(context)

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToOneNotNull(
    context: CoroutineContext = Dispatchers.Default,
): Flow<T> = mapNotNull {
    it.executeAsOneOrNull()
}.flowOn(context)

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = Dispatchers.Default,
): Flow<List<T>> = map {
    it.executeAsList()
}.flowOn(context)

fun LocalDateTime.dateTimeToString(): String {
    return this.toString()
}

fun LocalDate.dateToString(): String {
    return this.toString()
}