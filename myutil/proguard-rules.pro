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
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }

-keep class androidx.core.** { *; }
-keep class androidx.appcompat.** { *;  }
-keep class androidx.loader.** { *;  }
-keep class androidx.vectordrawable.** { *;  }
-keep class androidx.multidex.** { *; }

-dontwarn androidx.core.app.CoreComponentFactory

-dontwarn android.clients.google.com
-keep class android.clients.** { *; }

-keep class com.example.bugly.** { *; }

-keep class com.android.support.** { *; }



