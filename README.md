Hawk Android Client
===================

Hawk is an experimental prototype for an HTML based publishing service. The server creates the content for publication and the client side is able to fetch it.

The intention is to make both the server side and client side host independent - however at the moment (being a prototype) is very much coupled with Drupal 8 and Android.


Adding the client AAR to your project
-------------------------------------

1. Copy the aar file into your project's lib folder

2. Add this entry to your app gradle file:

```gradle
allprojects {
    repositories {
        jcenter()
        flatDir {
            dirs 'libs'
        }
    }
}
```

3. Add this entry to your app gradle file dependencies section:

```gradle
compile(name:'hawkandroidclient-debug', ext:'aar')
```
