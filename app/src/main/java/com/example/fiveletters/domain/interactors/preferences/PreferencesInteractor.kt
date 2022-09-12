package com.example.fiveletters.domain.interactors.preferences

import java.lang.reflect.Type

interface PreferencesInteractor {
    suspend fun <T : Any> saveItem(key: String, item: T)

    suspend fun <T : Any> getItem(key: String, type: Type): T?
}