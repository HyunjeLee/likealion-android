pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "likealion-android"
include(":app")
include(":myapplication")
include(":ch2")
include(":ch3")
include(":ch4")
include(":ch5")
include(":ch6")
include(":chat_lhj")
include(":ch7")
include(":ch7_outer")
include(":ch8")
