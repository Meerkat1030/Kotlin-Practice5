pluginManagement {
    repositories {
        google()
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

rootProject.name = "class20240213"
include(":app")
include(":tablayoutex")
include(":navigationviewex")
include(":materialex")
include(":coroutineex")
include(":ch13_activity")
include(":ch14_receiver")
include(":contactsex")
include(":gallaryex")
include(":ch16_provider")
