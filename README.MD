# Deploy the project for test

`git clone https://github.com/PlanchaudCorentin/research-project.git`

## deploy the socket server 

Node is required if you don't have it install it.

Run in the socket server directory:
```
npm install
node index.js
```

## Build the ionic app

Before hand you have to specify the socket server ip in the app.module.ts

```javascript
const config: SocketIoConfig = { url: 'http://your.server.ip.address:3002', options: {} };
```

run in the IMUPhone directory:

```
npm install
ionic state restore --plugins
ionic cordova build android
```

You need to have Android Sdk installed
You might have to set those environment variables:

```
set PATH $PATH:$HOME/Android/Sdk/tools
set ANDROID_HOME $HOME/Android/Sdk
export CORDOVA_ANDROID_GRADLE_DISTRIBUTION_URL=https://services.gradle.org/distributions/gradle-4.4-all.zip
```

Then install it on any android 8+ phone

## run the java client

run in the sock directory:
`mvn install`

then run the project

if the java client run on the same host than the socket server you don't need to change anything, else you need to change the ip address in :
`/home/pcorentin/research-project/sock/src/main/java/Model/SocketConnector/SocketConnector.java`

```java
this.socket = IO.socket("http://your.server.ip.address:3002");
```





