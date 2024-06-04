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

# Dagger Hilt
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn javax.inject.*
-dontwarn javax.annotation.*
-keep @javax.annotation.* class * {*;}
-keep @javax.inject.* class * {*;}
-keep class dagger.hilt.*
-keep class dagger.hilt.android.*
-keep class dagger.hilt.internal.*
-keep class dagger.hilt.migration.*
-keep class dagger.assisted.*
-keep class javax.inject.*
-keep class javax.inject.*
-keep class javax.annotation.*
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}
