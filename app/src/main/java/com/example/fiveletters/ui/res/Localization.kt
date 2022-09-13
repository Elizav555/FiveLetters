import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.example.fiveletters.domain.utils.generateUID
import java.util.Locale

internal val defaultLocalization: Localization = Localization(Locale.ENGLISH)

private val _supportedLocales: MutableSet<Locale> = mutableSetOf()
internal val supportedLocales: Set<Locale> get()= _supportedLocales + Locale.ENGLISH

internal val localizationMap = hashMapOf<Locale, Localization>()

data class Localization(
    val locale: Locale,
    internal val strings: MutableMap<Int, String> = mutableMapOf(),
)

fun registerSupportedLocales(vararg locales: Locale): Set<Locale> {
    locales.filter { it != Locale.ENGLISH }
        .forEach {
            if (_supportedLocales.add(it)) {
                registerLocalizationForLocale(it)
            }
        }
    return _supportedLocales + Locale.ENGLISH
}

private fun registerLocalizationForLocale(locale: Locale) {
    localizationMap[locale] = Localization(locale)
}

fun Translatable(
    defaultValue: String,
    localeToValue: Map<Locale, String>,
    id: Int = generateUID()
): Localization.() -> String {
    defaultLocalization.strings[id] = defaultValue
    for ((locale, value) in localeToValue.entries) {
        val localization =
            localizationMap[locale] ?: throw RuntimeException("There is no locale $locale")
        localization.strings[id] = value
    }
    return fun Localization.(): String {
        return this.strings[id] ?: defaultLocalization.strings[id]
        ?: error("There is no string called $id in localization $this")
    }
}

fun NonTranslatable(defaultValue: String, id: Int = generateUID()): Localization.() -> String {
    defaultLocalization.strings[id] = defaultValue
    return fun Localization.(): String {
        return defaultLocalization.strings[id]
            ?: error("There is no string called $id in localization default")
    }
}


object Vocabulary {
    val localization: Localization
        @Composable
        @ReadOnlyComposable
        get() = LocalLocalization.current
}

val LocalLocalization = compositionLocalOf { defaultLocalization }

@Composable
fun Localization(locale: Locale, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLocalization provides (localizationMap[locale] ?: defaultLocalization),
        content = content
    )
}