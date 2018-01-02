# Jenkins-To-Particle-Cloud
A Java command line utility that will query for job statuses on a Jenkins server, and then publish that information to a Particle Cloud subscription.

This project serves a *very* specific use case. That is, to query for the status of all jobs on a Jenkins server, and relay that information to the Particle Cloud API. It exists so that a Particle IoT device can receive such events through Particle's subscription service. Chances are, you have no need for such a specific project. If, by chance, you do... well... here you go!

# Format of the Data Sent to the Particle Cloud API

There is a specific format for the data published to a subscription on the Particle Cloud. It is an intentionally small payload, and simply contains a zero padded, colon delimited set of counts for each possible build status. 

The format of the data is simply: `"%03d:%03d:%03d:%03d:%03d:%03d"`. 
For example: `035:001:000:001:000:001`. 

Breaking that formatted String down, it means:

* There are `35` jobs with their last job status being: `SUCCESS`
* There is `1` job with its last job status being: `FAILURE`
* There are `0` jobs with their last job status being: `UNSTABLE`
* There is `1` job with its last job status being: `ABORTED`
* There are `0` jobs with their last job status being: `NOT_BUILT`
* There is `1` job with its last job status being: `UNKNOWN` (possibly never built, so there is no "last build")

# Building the App

From your terminal, navigate into the `jacksonsync` directory. Then simply run `./gradlew clean build`.

# Running the App

From your terminal, navigate into the `jacksonsync` directory. Then simply run `./gradlew run`. **Note: There are required parameters that you will need to specify in order to actually get the program to run successfully.** Running the previous command will tell you what the parameters are that need to be specified. They are also listed here:

```
-j (--jenkins) VAL  : URL for Jenkins server. Example: http://myjenkins.com.
-l (--ttl) VAL      : Particle event TTL for published events.
-n (--name) VAL     : Particle topic name to publish events to.
-p (--password) VAL : Jenkins password, or API access token.
-t (--token) VAL    : Particle API access token.
-u (--username) VAL : Jenkins username.
```

In order to pass the arguments into the app, you will need to use the following syntax (for example):

```
./gradlew run \
  -PappArgs="['-j=http://your.jenkins.server.com:8080', \
  '-u=yourJenkinsUsername', \
  '-p=yourJenkinsPasswordOrToken', \
  '-n=yourParticleSubscriptionName', \
  '-l=60', \
  '-t=yourParticleApiAccessToken']"
```
