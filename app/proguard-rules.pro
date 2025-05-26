# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Hilt
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keepclassmembers,allowobfuscation class * {
    @dagger.hilt.android.lifecycle.HiltViewModel <init>(...);
}

# Keep generated Hilt components
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }

# Keep the Application, Activity, ViewModel if annotated
-keep class **_HiltModules_* { *; }

# Hilt Navigation Compose
-keep class androidx.hilt.navigation.** { *; }

# Lifecycle ViewModel Compose
-keep class androidx.lifecycle.viewmodel.compose.** { *; }

# Lifecycle Runtime Compose
-keep class androidx.lifecycle.runtime.compose.** { *; }

# Room
-keep class androidx.room.RoomDatabase { *; }
-keep class androidx.room.Room { *; }

# Keep annotated entities and DAOs
-keep @androidx.room.* class * { *; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
}

# Needed for Kotlin metadata used by kapt
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod