package com.example.fiveletters.domain.preferences

import java.lang.reflect.Type

interface Preferences {
    fun <T> getItem(key: String, type: Type): T?
    fun <T> setItem(key: String, item: T)
}