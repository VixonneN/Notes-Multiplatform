package com.khomichenko.network.utils

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

// на айосе вылетает экспепшен, если работать с разными инстансами
private val firestoreInstance = Firebase.firestore

internal suspend inline fun <reified T : FirestoreModel> getAll(userId: String): List<T> =
    try {
        Logger.d { "Fetching Firestore collection $userId..." }

        firestoreInstance
            .collection(userId)
            .get()
            .documents
            .map { it.data<T>().apply { id = it.id } }
            .also { Logger.d { "Successfully fetched Firestore collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't fetch Firestore collection $userId" }
        throw throwable
    }

internal suspend inline fun <reified T : FirestoreModel> get(userId: String, id: String): T =
    try {
        Logger.d { "Fetching Firestore document $id in collection $userId..." }

        firestoreInstance
            .collection(userId)
            .document(id)
            .get()
            .data<T>()
            .also { Logger.d { "Successfully fetched Firestore document $id in collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't fetch Firestore document $id in collection $userId" }
        throw throwable
    }

/**
 * Создание ноды (к примеру создание пользователя)
 */
internal suspend inline fun create(userId: String, id: String, data: Map<String, Any?>) {
    try {
        Logger.d { "Creating Firestore document $id in collection $userId..." }

        firestoreInstance
            .collection(userId)
            .document(id)
            .set(data)
            .also { Logger.d { "Successfully created Firestore document $id in collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't create Firestore document $id in collection $userId" }
        throw throwable
    }
}

internal suspend inline fun add(userId: String, data: Map<String, Any?>) {
    try {
        Logger.d { "Adding Firestore document in collection $userId..." }

        firestoreInstance
            .collection(userId)
            .add(data)
            .also { Logger.d { "Successfully added Firestore document in collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't add Firestore document in collection $userId" }
        throw throwable
    }
}

internal suspend inline fun update(userId: String, id: String, data: Map<String, Any?>) {
    try {
        Logger.d { "Updating Firestore document $id in collection $userId..." }

        firestoreInstance
            .collection(userId)
            .document(id)
            .update(data)
            .also { Logger.d { "Successfully updated Firestore document $id in collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't update Firestore document $id in collection $userId" }
        throw throwable
    }
}

internal suspend fun delete(userId: String, id: String) {
    try {
        Logger.d { "Deleting Firestore document $id in collection $userId..." }

        firestoreInstance
            .collection(userId)
            .document(id)
            .delete()
            .also { Logger.d { "Successfully deleted Firestore document $id in collection $userId!" } }
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "Couldn't delete Firestore document $id in collection $userId" }
        throw throwable
    }
}
