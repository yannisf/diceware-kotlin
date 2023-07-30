### Build

### Development Run

    gradle bootRun

    curl http://localhost:8080/api/hello

## Gradle

### Gradle components in single project

- `/.gradle/`: Internal gradle directory. Not user manageable. Should not be version controlled
- `/gradle`:  Stores the wrapper and possible other gradle supporting files. Should be under version control.
- `gradlew` and `gradlew.bat`: wrapper scripts
- `build.gradle`: build configuration file
- `settings.gradle`: Executed before any `build.gradle`. It is also a script like `build.gradle`. Define all included submodules.
- `gradle.properties`: Options for running gradle itself

## cdk8s

After generating the charts apply them.


### Checklist

* Kube deployment
* cdk8s
* kube secrets
* log in config map
* 