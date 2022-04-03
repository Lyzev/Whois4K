<h1 align="center">WhoIs4K</h1>

<p align="center">A library for who is requests.</p>

<div align="center">
    <a href="https://discord.gg/5UmsQP4MFH"><img src="https://img.shields.io/discord/610120595765723137?logo=discord" alt="Discord"/></a>
    <br><br>
    <img src="https://img.shields.io/github/last-commit/Lyzev/WhoIs4K" alt="GitHub last commit"/>
    <img src="https://img.shields.io/github/commit-activity/w/Lyzev/WhoIs4K" alt="GitHub commit activity"/>
    <br>
    <img src="https://img.shields.io/github/languages/code-size/Lyzev/WhoIs4K" alt="GitHub code size in bytes"/>
    <img src="https://img.shields.io/github/contributors/Lyzev/WhoIs4K" alt="GitHub contributors"/>
</div>

## Usage

[![](https://jitpack.io/v/Lyzev/WhoIs4K.svg?label=Release)](https://jitpack.io/#Lyzev/WhoIs4K)

### Import

Replace `${version}` with the current version!

<details>
        <summary>Gradle KTS</summary>

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Lyzev:WhoIs4K:${version}")
}
```

</details>

<details>
        <summary>Gradle Groovy</summary>

```
repositories {
	maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Lyzev:WhoIs4K:${version}'
}
```

</details>

<details>
        <summary>Maven</summary>

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Lyzev</groupId>
        <artifactId>WhoIs4K</artifactId>
        <version>${version}</version>
    </dependency>
</dependencies>
```

</details>

<details>
        <summary>Raw Jar</summary>

1. Go to the [release page](https://github.com/Lyzev/WhoIs4K/releases).
2. Download WhoIs4K-${version}.jar.
3. Add the jar to your classpath.

</details>

### Example

<details>
        <summary>Kotlin</summary>

```kotlin
val whoIs = WhoIs("google.com")
whoIs.doRequest().forEach(::println)
```
</details>

<details>
        <summary>Java</summary>

```java
WhoIs whoIs = new WhoIs("google.com");
whoIs.doRequest().forEach(System.out::println);
```
</details>

## Documentation

You can find the documentation [here](https://lyzev.github.io/WhoIs4K/dokka).

## Code Quality Monitoring

You can find the qodana report [here](https://lyzev.github.io/WhoIs4K/qodana).

## Bugs and Suggestions

Bug reports and suggestions should be made in this repo's [issue tracker](https://github.com/Lyzev/WhoIs4K/issues)
using the templates provided. Please provide as much information as you can to best help us understand your issue and
give a better chance of it being resolved.

## What is a WHOIS?
WHOIS (pronounced as the phrase "who is") is a query and response protocol that is widely used for querying databases that store the registered users or assignees of an Internet resource, such as a domain name, an IP address block or an autonomous system, but is also used for a wider range of other information. The protocol stores and delivers database content in a human-readable format. The current iteration of the WHOIS protocol was drafted by the Internet Society, and is documented in RFC 3912.

Source: [Wikipedia](https://en.wikipedia.org/wiki/WHOIS)  
See [Wikipedia](https://en.wikipedia.org/wiki/WHOIS) for more information.