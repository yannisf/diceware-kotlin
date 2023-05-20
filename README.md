### Build

### Development Run

    gradle bootRun

    http://localhost:8080/actuator/health

#### After converting to multi-module

    ./gradlew :web:bootRun

## Gradle

* `./gradlew -q projects`: All gradle projects
* Project Layout: https://docs.gradle.org/current/userguide/fine_tuning_project_layout.html

### Gradle components in single project

- `/.gradle/`: Internal gradle directory. Not user manageable. Should not be version controlled
- `/gradle`:  Stores the wrapper and possible other gradle supporting files. Should be under version control.
- `gradlew` and `gradlew.bat`: wrapper scripts
- `build.gradle`: build configuration file
- `settings.gradle`: Executed before any `build.gradle`. It is also a script like `build.gradle`. Define all included submodules.
- `gradle.properties`: Options for running gradle itself