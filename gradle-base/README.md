# Gradle base configuration

## `gradle init`

***Warning:*** Running this will overwrite your `.gitignore` and `.gitattribute`

For reference, this is how the gradle project was generated. It was ran in this base, not from the
repo's root.

```shell
% pwd
[...]/bases/gradle-base

% gradle --version

------------------------------------------------------------
Gradle 7.4.2
------------------------------------------------------------

Build time:   2022-03-31 15:25:29 UTC
Revision:     540473b8118064efcc264694cbcaa4b677f61041

Kotlin:       1.5.31
Groovy:       3.0.9
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          11.0.14.1 (Eclipse Adoptium 11.0.14.1+1)
OS:           Mac OS X 12.3.1 x86_64

% gradle init

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Split functionality across multiple subprojects?:
  1: no - only one application project
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 2

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 2

Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no
Project name (default: gradle-base): greeter
Source package (default: greeter): com.example.greeter
```

## `.gitignore`

[This](https://www.toptal.com/developers/gitignore) is a great site for generating gitignore files.
It was formerly found at `gitignore.io` but luckily the domain redirectly nicely so old scripts
still work. You should be using a "global" gitignore file for editor specific files which is why
there are none here. I will say though, if you're adding the IntelliJ ignore to your global
gitignore file be sure to use `intellij+all` specifically, not `intellij`.

Below is the link to use to generate the gitignore for Java and Gradle.

    https://www.toptal.com/developers/gitignore/api/java,gradle

## Gradle tweaks

Newer versions of Gradle may be released. The "gradle approved" way of upgrading the gradle wrapper
is to use the `gradle wrapper` command itself. Using the versions HTTP API that Gradle provides we
can get the latest version and its checksum. By default the checksum is not included, I do not
know why, but I add it for good measure. It may be worth mentioning that this only checks the sha256
sum of the distribution, *not* the `gradle-wrapper.jar` file. If you want to be sure about that as
well you can check the documentation section "Verifying the integrity of the Gradle Wrapper JAR".

The below runs `gradle wrapper` a second time as the documentation explains,

> Note that running the wrapper task once will update `gradle-wrapper.properties` only, but leave
the wrapper itself in `gradle-wrapper.jar` untouched. This is usually fine as new versions of Gradle
can be run even with ancient wrapper files. If you nevertheless want all the wrapper files to be
completely up-to-date, you’ll need to run the wrapper task a second time.

[Documentation link](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

    newest_gradle_version="$(curl --silent https://services.gradle.org/versions/current | jq --raw-output '.version')"
    newest_gradle_sha256_sum="$(curl --silent --location \
        $(curl --silent https://services.gradle.org/versions/current | jq --raw-output '.checksumUrl'))"

    gradle wrapper --gradle-version "${newest_gradle_version}" \
                   --distribution-type bin \
                   --gradle-distribution-sha256-sum "${newest_gradle_sha256_sum}" \
        && \
    gradle wrapper --gradle-version "${newest_gradle_version}" \
                   --distribution-type bin \
                   --gradle-distribution-sha256-sum "${newest_gradle_sha256_sum}"

## Kotlin error note

If you see this warning do not panic, it is a [known issue](https://github.com/gradle/gradle/issues/18935). You will not
see it each build. Only when you change the `buildSrc` files.

    > Task :buildSrc:compileKotlin
    'compileJava' task (current target is 17) and 'compileKotlin' task (current target is 1.8) jvm target compatibility should be set to the same Java version.

## Java version

### `.java_version`

I am using [jenv](https://www.jenv.be/) to set the Java version. In the future I may use sdkman but
as if now I don't. You don't need to use either, I'm just explaining why this file is here. In fact,
I intend to use gradle toolchains so gradle could in theory run with an older and compile with a
newer version.

### `com.example.greeter.java-common-conventions.gradle.kts`

This is the new way to specify the Java version to use. My understanding is it also allows you to
run older versions and compile to newer (ex: run Gradle wrapper in Java 8 but target Java 17).

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

## Maven style publishing

[More documentation](https://docs.gradle.org/current/userguide/publishing_maven.html).

We add the version (which is used even for "vanilla" Gradle) into the `gradle.properties` file. The group is set in `allprojects` block from a `build.gradle.kts` at base. The artifact name is just the project name. *The project names are not always unique, just be aware.*

The Javadoc and sources should be published so these are added to the `com.example.greeter.java-common-conventions.gradle.kts` file.
