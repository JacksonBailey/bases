# Gradle base configuration (with Groovy buildscripts)

There is already a Gradle base configuration but after fooling around with Kotlin buildscripts for Gradle it seems they aren't quite mature enough for my liking. There's enough weirdness with the `buildSrc` and enough documentation from other places still only having the Groovy that I think this is useful.

## `gradle init`

***Warning:*** Running this will overwrite your `.gitignore` and `.gitattribute`

For reference, this is how the gradle project was generated. It was ran in this base, not from the
repo's root.

```shell
% jenv local 17

% pwd
[...]/bases/gradle-base-groovy

% gradle --version

------------------------------------------------------------
Gradle 7.4.2
------------------------------------------------------------

Build time:   2022-03-31 15:25:29 UTC
Revision:     540473b8118064efcc264694cbcaa4b677f61041

Kotlin:       1.5.31
Groovy:       3.0.9
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          17.0.3 (Eclipse Adoptium 17.0.3+7)
OS:           Mac OS X 12.4 x86_64

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
Enter selection (default: Groovy) [1..2] 1

Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no
Project name (default: gradle-base-groovy): greeter
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
